package moise.dsl.ss

import moise.dsl.fs.Monitorable;
import scala.collection.mutable.ListBuffer

object Group {
  def named(s: String) = Group(s)
}

case class Group(val name: String) extends Monitorable {
  var roles: ListBuffer[GroupRole] = ListBuffer()
  var subGroups: ListBuffer[SubGroup] = ListBuffer()
  var links: List[LinkClass] = List()
  var compatibilities: List[CompatibilityClass] = List()

  def with_links(l: LinkClass*) = {
    links = l.toList
    this
  }

  def with_compatibilities(c: CompatibilityClass*) = {
    compatibilities = c.toList
    this
  }

  def including(s: SubGroup) = {
    subGroups += s
    this
  }

  def and(s: SubGroup) = including(s)

  def consists_of(r: GroupRole) = {
    roles += r
    this
  }

  def and(r: GroupRole) = consists_of(r)

}
