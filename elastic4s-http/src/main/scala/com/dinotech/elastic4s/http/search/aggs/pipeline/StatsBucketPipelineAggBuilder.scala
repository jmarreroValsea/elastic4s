package com.dinotech.elastic4s.http.search.aggs.pipeline

import com.dinotech.elastic4s.http.search.aggs.AggMetaDataFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.pipeline.StatsBucketPipelineAgg

object StatsBucketPipelineAggBuilder {
  def apply(agg: StatsBucketPipelineAgg): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("stats_bucket")
    builder.field("buckets_path", agg.bucketsPath)
    agg.gapPolicy.foreach(policy => builder.field("gap_policy", policy.toString.toLowerCase))
    agg.format.foreach(f => builder.field("format", f))
    builder.endObject()
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
