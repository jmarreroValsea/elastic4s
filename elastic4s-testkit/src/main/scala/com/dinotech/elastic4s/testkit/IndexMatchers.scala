package com.dinotech.elastic4s.testkit

import com.dinotech.elastic4s.http.ElasticClient
import org.scalatest.Matchers
import org.scalatest.matchers.{MatchResult, Matcher}

trait IndexMatchers extends Matchers {

  import com.dinotech.elastic4s.http.ElasticDsl._

  import scala.concurrent.duration._

  def haveCount(expectedCount: Int)(implicit client: ElasticClient,
                                    timeout: FiniteDuration = 10.seconds): Matcher[String] =
    new Matcher[String] {

      def apply(left: String): MatchResult = {
        val count = client.execute(search(left).size(0).restTotalHitsAsInt(true)).await(timeout).result.totalHits
        MatchResult(
          count == expectedCount,
          s"Index $left had count $count but expected $expectedCount",
          s"Index $left had document count $expectedCount"
        )
      }
    }

  def containDoc(expectedId: Any)(implicit client: ElasticClient,
                                  timeout: FiniteDuration = 10.seconds): Matcher[String] =
    new Matcher[String] {

      override def apply(left: String): MatchResult = {
        val exists = client.execute(get(expectedId.toString).from(left)).await(timeout).result.exists
        MatchResult(
          exists,
          s"Index $left did not contain expected document $expectedId",
          s"Index $left contained document $expectedId"
        )
      }
    }

  def beCreated(implicit client: ElasticClient, timeout: FiniteDuration = 10.seconds): Matcher[String] =
    new Matcher[String] {

      override def apply(left: String): MatchResult = {
        val exists = client.execute(indexExists(left)).await(timeout).result.isExists
        MatchResult(
          exists,
          s"Index $left did not exist",
          s"Index $left exists"
        )
      }
    }

  def beEmpty(implicit client: ElasticClient, timeout: FiniteDuration = 10.seconds): Matcher[String] =
    new Matcher[String] {

      override def apply(left: String): MatchResult = {
        val count = client.execute(search(left).size(0).restTotalHitsAsInt(true)).await(timeout).result.totalHits
        MatchResult(
          count == 0,
          s"Index $left was not empty",
          s"Index $left was empty"
        )
      }
    }
}
