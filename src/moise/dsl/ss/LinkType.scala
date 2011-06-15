package moise.dsl.ss

trait LinkType extends RelType
case object authority extends LinkType
case object communication extends LinkType
case object acquaintance extends LinkType

object Linktype {
  def named(s: String) = CustomLinkType(s)
}

case class CustomLinkType(val name: String) extends LinkType
