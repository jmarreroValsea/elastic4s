package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.http.ScriptBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.StatsAggregation

object StatsAggregationBuilder {
  def apply(agg: StatsAggregation): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder().startObject("stats")
    agg.field.foreach(builder.field("field", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.format.foreach(builder.field("format", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }
    builder
  }
}
