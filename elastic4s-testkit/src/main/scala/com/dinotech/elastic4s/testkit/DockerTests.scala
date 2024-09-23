package com.dinotech.elastic4s.testkit

import com.dinotech.elastic4s.ElasticsearchClientUri
import com.dinotech.elastic4s.http.{ElasticClient, ElasticDsl, ElasticProperties}

import scala.util.Try

trait DockerTests extends com.dinotech.elastic4s.http.ElasticDsl with ClientProvider {

  val client = ElasticClient(ElasticProperties("http://localhost:9200"))

  protected def deleteIdx(indexName: String): Unit = {
    Try {
      client.execute {
        ElasticDsl.deleteIndex(indexName)
      }.await
    }
  }

  protected def createIdx(name: String) = Try {
    client.execute {
      createIndex(name)
    }.await
  }

  protected def cleanIndex(indexName: String): Unit = {
    deleteIdx(indexName)
    Try {
      client.execute {
        ElasticDsl.createIndex(indexName)
      }.await
    }
  }
}
