package com.dinotech.elastic4s.http.search.queries.span

import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.span.SpanWithinQuery

object SpanWithinQueryBodyFn {
  def apply(q: SpanWithinQuery): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()
    builder.startObject("span_within")

    builder.rawField("little", QueryBuilderFn(q.little))
    builder.rawField("big", QueryBuilderFn(q.big))

    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder.endObject()
    builder.endObject()
  }
}
