package moise.dsl.helper

import moise.dsl.fs._
import moise.dsl.ss._

object Implicits {
  implicit def intToTimeSpan(i: Int) = TimeSpan(i)
  implicit def intToAgentCount(i: Int) = AgentCount(i)
  implicit def intToNumber(i: Int) = Number(i)
  implicit def groupToSubGroup(g: Group) = SubGroup(g)
  implicit def roleToGroupRole(r: Role) = GroupRole(Some(r))
  implicit def cardinalityTypeToCardinality(c: CardinalityType) = Cardinality(c)
  implicit def groupRoleToCardinality(r: GroupRole) = {
    val c = Cardinality(r.role.get)
    c.min = r.min
    c.max = r.max
    c
  }
  implicit def subGroupToCardinality(g: SubGroup) = {
    val c = Cardinality(g.group)
    c.min = g.min
    c.max = g.max
    c
  }
  
}
