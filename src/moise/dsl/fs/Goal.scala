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

class Goal(val name: String) extends SchemeElement {
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

  // TODO: irgendwie noch einen Parameter ergänzen, damit Method Chaining klappt
  def now = {
    ttf = Some(Now)
    this
  }

  // TODO: irgendwie noch einen Parameter ergänzen, damit Method Chaining klappt
  def never = {
    ttf = Some(Never)
    this
  }

  def in(t: TimeTerm) = {
    ttf = Some(t)
    this
  }

  // TODO: Klären warum ohne Angabe des Typs hier BoxedUnit zurückgegeben wird
  def by_at_least (a: AgentCount): Goal = {
    min = Some(a.count)
    this
  }

}
