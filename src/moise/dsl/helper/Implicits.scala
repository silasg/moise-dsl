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

  // diese beiden Methoden werden nur benötigt, da zufällig (?) SubGroups und GroupRoles
  // sowohl Numerable als auch Cardinalities sind
  implicit def groupRoleToCardinality(r: GroupRole) = Cardinality(r.role.get)
  implicit def subGroupToCardinality(g: SubGroup) = Cardinality(g.group)
  
}
