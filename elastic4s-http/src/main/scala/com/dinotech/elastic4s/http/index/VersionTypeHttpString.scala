package com.dinotech.elastic4s.http.index

import com.dinotech.elastic4s.VersionType

object VersionTypeHttpString {
  def apply(versionType: VersionType): String = versionType match {
    case VersionType.External    => "external"
    case VersionType.Internal    => "internal"
    case VersionType.ExternalGte => "external_gte"
    case VersionType.Force       => "force"
  }
}
