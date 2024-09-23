package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.GeoHashGridAggregation

object GeoHashGridAggregationBuilder {
  def apply(agg: GeoHashGridAggregation): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("geohash_grid")

    agg.field.foreach(builder.field("field", _))
    agg.precision.foreach(builder.field("precision", _))
    agg.size.foreach(builder.field("size", _))
    agg.shardSize.foreach(builder.field("shard_size", _))

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
