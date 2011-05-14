package moise.dsl.helper

import moise.dsl.fs._

object Implicits {
  implicit def intToTimeSpan(i: Int) = TimeSpan(i)
  implicit def intToAgentCount(i: Int) = AgentCount(i)
}
