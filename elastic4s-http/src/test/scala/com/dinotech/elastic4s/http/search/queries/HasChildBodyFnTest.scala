package com.dinotech.elastic4s.http.search.queries

import com.dinotech.elastic4s.http.search.queries.nested.HasChildBodyFn
import com.dinotech.elastic4s.searches.ScoreMode
import com.dinotech.elastic4s.searches.queries.{HasChildQuery, InnerHit}
import com.dinotech.elastic4s.searches.queries.matches.MatchQuery
import org.scalatest.{FunSuite, Matchers}

class HasChildBodyFnTest extends FunSuite with Matchers {

  test("has child should generate expected json") {
    val q = HasChildQuery("blog_tag", MatchQuery("tag", "something"), ScoreMode.Min)
      .boost(1.2)
      .minMaxChildren(2, 10)
      .ignoreUnmapped(true)
      .queryName("myquery")
      .innerHit(InnerHit("inners"))
    HasChildBodyFn(q).string() shouldBe
      """{"has_child":{"type":"blog_tag","min_children":2,"max_children":10,"score_mode":"min","query":{"match":{"tag":{"query":"something"}}},"ignore_unmapped":true,"boost":1.2,"_name":"myquery","inner_hits":{"name":"inners"}}}"""
  }
}
