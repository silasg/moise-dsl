package moise.dsl.ss

case class GroupRole(val role: Option[Role] = None, val min: Option[BigInt] = None, val max: Option[BigInt] = None)