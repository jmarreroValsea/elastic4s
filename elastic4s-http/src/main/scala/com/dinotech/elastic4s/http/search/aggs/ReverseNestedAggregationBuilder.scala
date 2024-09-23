package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.XContentBuilder
import com.dinotech.elastic4s.json.XContentFactory
import com.dinotech.elastic4s.searches.aggs.ReverseNestedAggregation

object ReverseNestedAggregationBuilder {
  def apply(agg: ReverseNestedAggregation): XContentBuilder = {
    val builder = XContentFactory.obj().startObject("reverse_nested")
    agg.path.foreach(builder.field("path", _))
    builder.endObject()
    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
