package moise.dsl.fs

import moise.{Achievement => AchievementXb}
import moise.{Maintenance => MaintenanceXb}
import moise.{GoalType => GoalTypeXb}

case class AgentCount(val count: BigInt) {
  def agents = this
  def agent = agents
}

object Goal {
  def named(s: String) = {
    new Goal(s)
  }
}

trait goalTypeVerb {
  val typeXb: GoalTypeXb
}

object achieve extends goalTypeVerb {
  val typeXb = AchievementXb
}
object maintain extends goalTypeVerb {
  val typeXb = MaintenanceXb
}

class Goal(val name: String) {
  var ttf: Option[TimeTerm] = None
  var description: Option[String] = None
  var goalType: Option[GoalTypeXb] = None
  var min: Option[BigInt] = None

  def described_as(s: String) = {
    description = Some(s)
    this
  }

  def to (g: goalTypeVerb) = {
    goalType = Some(g.typeXb)
    this
  }

  def now = {
    ttf = Some(Now)
    this
  }

  def never = {
    ttf = Some(Never)
    this
  }

  def in(t: TimeTerm) = {
    ttf = Some(t)
    this
  }

  def by_at_least (a: AgentCount) {
    min = Some(a.count)
    this
  }

}
