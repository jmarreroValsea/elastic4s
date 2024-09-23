package com.dinotech.elastic4s.http.index

import com.dinotech.elastic4s.indexes.{CreateIndexRequest, IndexAliasRequest}
import com.dinotech.elastic4s.searches.queries.PrefixQuery
import org.scalatest.{FunSuite, Matchers}

class CreateIndexContentBuilderTest extends FunSuite with Matchers {

  test("create index should include aliases when set") {
    val create = CreateIndexRequest("myindex", aliases = Set(IndexAliasRequest("alias1", None), IndexAliasRequest("alias2", Option(PrefixQuery("myfield", "pre")))))
    CreateIndexContentBuilder(create).string shouldBe
      """{"aliases":{"alias1":{},"alias2":{"filter":{"prefix":{"myfield":{"value":"pre"}}}}}}"""
  }
}
