package com.dinotech.elastic4s.http.search.queries.span

import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.span.SpanMultiTermQuery

object SpanMultiTermQueryBodyFn {
  def apply(q: SpanMultiTermQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("span_multi")
    builder.rawField("match", QueryBuilderFn(q.query))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
  }
}
