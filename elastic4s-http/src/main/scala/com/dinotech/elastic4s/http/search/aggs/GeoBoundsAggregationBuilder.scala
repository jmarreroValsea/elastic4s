package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.http.ScriptBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.GeoBoundsAggregation

object GeoBoundsAggregationBuilder {
  def apply(agg: GeoBoundsAggregation): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("geo_bounds")

    agg.field.foreach(builder.field("field", _))
    agg.format.foreach(builder.field("format", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.wrapLongitude.foreach(builder.field("size", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }

    builder.endObject()

    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
