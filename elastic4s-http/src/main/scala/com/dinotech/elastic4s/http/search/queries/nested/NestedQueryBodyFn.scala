package com.dinotech.elastic4s.http.search.queries.nested

import com.dinotech.elastic4s.http.EnumConversions
import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.NestedQuery

object NestedQueryBodyFn {
  def apply(q: NestedQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("nested")
    builder.field("path", q.path)
    q.scoreMode.map(EnumConversions.scoreMode).foreach(builder.field("score_mode", _))
    builder.rawField("query", QueryBuilderFn(q.query))
    q.ignoreUnmapped.foreach(builder.field("ignore_unmapped", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    q.inner.foreach(inner => builder.rawField("inner_hits", InnerHitQueryBodyFn(inner)))
    builder.endObject()
    builder.endObject()
  }
}
