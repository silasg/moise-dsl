package moise.dslconverter

import moise.dsl.ss.{RelType, authority, communication, compatibility}

object RoleRelConverter {
  def toTypeString(t: RelType) = t match {
    case `compatibility` => "compatibility"
    case `authority` => "authority"
    case `communication` => "communication"
  }
}
