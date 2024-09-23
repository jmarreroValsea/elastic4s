package com.dinotech.elastic4s.snapshots

import com.sksamuel.exts.OptionImplicits._

case class GetSnapshotsRequest(snapshotNames: Seq[String],
                               repositoryName: String,
                               ignoreUnavailable: Option[Boolean] = None,
                               verbose: Option[Boolean] = None) {
  def ignoreUnavailable(ignore: Boolean): GetSnapshotsRequest = copy(ignoreUnavailable = ignore.some)
  def verbose(v: Boolean): GetSnapshotsRequest                = copy(verbose = v.some)
}
