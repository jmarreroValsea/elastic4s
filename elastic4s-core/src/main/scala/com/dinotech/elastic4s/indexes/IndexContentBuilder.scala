package com.dinotech.elastic4s.indexes

import com.dinotech.elastic4s.XContentFieldValueWriter
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}

object IndexContentBuilder {
  def apply(request: IndexRequest): XContentBuilder =
    request.source match {
      case Some(json) => XContentFactory.parse(json)
      case None =>
        val source = XContentFactory.jsonBuilder()
        request.fields.foreach(XContentFieldValueWriter(source, _))
        source.endObject()
        source
    }
}
