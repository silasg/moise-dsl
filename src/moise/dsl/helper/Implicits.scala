package moise.dsl.helper

import moise.dsl.fs._
import moise.dsl.ss._

object Implicits {
  implicit def intToTimeSpan(i: Int) = TimeSpan(i)
  implicit def intToAgentCount(i: Int) = AgentCount(i)
  implicit def intToNumber(i: Int) = Number(i)
  implicit def groupToSubGroup(g: Group) = SubGroup(g)
}
