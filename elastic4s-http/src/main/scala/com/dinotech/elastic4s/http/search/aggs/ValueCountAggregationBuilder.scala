package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.http.ScriptBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.ValueCountAggregation

object ValueCountAggregationBuilder {
  def apply(agg: ValueCountAggregation): XContentBuilder = {
    val builder = XContentFactory.obj()
    builder.startObject("value_count")
    agg.field.foreach(builder.field("field", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }
    builder.endObject().endObject()
  }
}
