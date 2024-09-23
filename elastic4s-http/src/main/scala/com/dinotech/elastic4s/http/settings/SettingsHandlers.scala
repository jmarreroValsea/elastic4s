package com.dinotech.elastic4s.http.settings

import com.dinotech.elastic4s.Index
import com.dinotech.elastic4s.http._
import com.dinotech.elastic4s.json.JacksonSupport
import com.dinotech.elastic4s.settings.{GetSettingsRequest, UpdateSettingsRequest}
import com.sksamuel.exts.collection.Maps

import scala.collection.JavaConverters._

case class IndexSettingsResponse(settings: Map[Index, Map[String, String]]) {
  def settingsForIndex(index: Index) = settings(index)
}

trait SettingsHandlers {

  implicit object GetSettingsHandler extends Handler[GetSettingsRequest, IndexSettingsResponse] {

    override def responseHandler = new ResponseHandler[IndexSettingsResponse] {

      override def handle(response: HttpResponse): Either[ElasticError, IndexSettingsResponse] =
        response.statusCode match {
          case 200 =>
            val root = JacksonSupport.mapper.readTree(response.entity.get.content)
            val settings = root.fields.asScala.map { entry =>
              val indexSettings = JacksonSupport.mapper.readValue[Map[String, Any]](
                JacksonSupport.mapper.writeValueAsBytes(entry.getValue)
              )
              // we don't want the 'settings.' prefix on the settings map, that's just in the json for tidyness, but the actual settings names are not settings.index.uuid but index.uuid
              Index(entry.getKey) -> Maps.flatten(indexSettings).map {
                case (key, value) => key.stripPrefix("settings.") -> value.toString
              }
            }.toMap
            Right(IndexSettingsResponse(settings))
          case _ =>
            Left(ElasticError.parse(response))
        }
    }

    override def build(request: GetSettingsRequest): ElasticRequest = {
      val endpoint = "/" + request.indexes.string + "/_settings"
      ElasticRequest("GET", endpoint)
    }
  }

  implicit object UpdateSettingsHandler extends Handler[UpdateSettingsRequest, IndexSettingsResponse] {

    override def responseHandler = new ResponseHandler[IndexSettingsResponse] {
      override def handle(response: HttpResponse): Either[ElasticError, IndexSettingsResponse] =
        response.statusCode match {
          case 200 =>
            Right(ResponseHandler.fromResponse[IndexSettingsResponse](response))
          case _ =>
            Left(ElasticError.parse(response))
        }
    }

    override def build(request: UpdateSettingsRequest): ElasticRequest = {
      val endpoint = "/" + request.indices.string + "/_settings"
      val body     = JacksonSupport.mapper.writeValueAsString(request.settings)
      ElasticRequest("PUT", endpoint, HttpEntity(body))
    }
  }
}
