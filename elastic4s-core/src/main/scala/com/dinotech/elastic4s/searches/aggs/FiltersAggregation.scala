package com.dinotech.elastic4s.searches.aggs

import com.dinotech.elastic4s.searches.aggs.pipeline.PipelineAgg
import com.dinotech.elastic4s.searches.queries.Query

case class FiltersAggregation(name: String,
                              filters: Iterable[Query],
                              pipelines: Seq[PipelineAgg] = Nil,
                              subaggs: Seq[AbstractAggregation] = Nil,
                              metadata: Map[String, AnyRef] = Map.empty)
    extends Aggregation {

  type T = FiltersAggregation

  override def subAggregations(aggs: Iterable[AbstractAggregation]): T = copy(subaggs = aggs.toSeq)
  override def metadata(map: Map[String, AnyRef]): T                   = copy(metadata = map)
}
