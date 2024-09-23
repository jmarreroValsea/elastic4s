package com.dinotech.elastic4s.http

import com.dinotech.elastic4s.ElasticsearchClientUri
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.Duration

class ElasticClientTest extends FlatSpec with Matchers with ElasticDsl {

  "HttpClient" should "throw an error when it cannot connect" in {
    intercept[JavaClientExceptionWrapper] {
      val client = ElasticClient(ElasticsearchClientUri("123", 1))
      val f = client.execute {
        indexInto("a-index" / "a-type") id "a-id" fields Map("wibble" -> "foo")
      }
      f.await(Duration.Inf)
    }
  }
}
