package com.dinotech.elastic4s.searches.queries.matches

import com.dinotech.elastic4s.analyzers.Analyzer
import com.dinotech.elastic4s.searches.queries.Query

case class MatchPhrase(field: String,
                       value: Any,
                       boost: Option[Double] = None,
                       analyzer: Option[String] = None,
                       slop: Option[Int] = None,
                       queryName: Option[String] = None)
    extends Query {

  def analyzer(a: Analyzer): MatchPhrase        = copy(analyzer = Some(a.name))
  def boost(boost: Double): MatchPhrase         = copy(boost = Some(boost))
  def slop(slop: Int): MatchPhrase              = copy(slop = Some(slop))
  def queryName(queryName: String): MatchPhrase = copy(queryName = Some(queryName))
}
