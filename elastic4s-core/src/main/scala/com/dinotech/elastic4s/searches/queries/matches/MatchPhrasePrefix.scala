package com.dinotech.elastic4s.searches.queries.matches

import com.dinotech.elastic4s.analyzers.Analyzer
import com.dinotech.elastic4s.searches.queries.Query
import com.sksamuel.exts.OptionImplicits._

case class MatchPhrasePrefix(field: String,
                             value: Any,
                             analyzer: Option[String] = None,
                             queryName: Option[String] = None,
                             boost: Option[Double] = None,
                             maxExpansions: Option[Int] = None,
                             slop: Option[Int] = None)
    extends Query {

  def analyzer(a: Analyzer): MatchPhrasePrefix        = analyzer(a.name)
  def analyzer(name: String): MatchPhrasePrefix       = copy(analyzer = name.some)
  def queryName(queryName: String): MatchPhrasePrefix = copy(queryName = queryName.some)
  def boost(boost: Double): MatchPhrasePrefix         = copy(boost = boost.some)
  def maxExpansions(max: Int): MatchPhrasePrefix      = copy(maxExpansions = max.some)
  def slop(slop: Int): MatchPhrasePrefix              = copy(slop = slop.some)
}
