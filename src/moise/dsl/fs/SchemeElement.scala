package moise.dsl.fs

// TODO: scalaxb importe entfernen
import moise.{Achievement => AchievementXb}
import moise.{Maintenance => MaintenanceXb}
import moise.{GoalType => GoalTypeXb}
import moise.dsl.helper.{TimeTerm, TimeSpan, now, never}

trait goalTypeVerb {
  val typeXb: GoalTypeXb
}

object achieve extends goalTypeVerb {
  val typeXb = AchievementXb
}
object maintain extends goalTypeVerb {
  val typeXb = MaintenanceXb
}

// SchemeElement implementiert bis auf den Namen schon alles von Goal, weil ein Plan,
// der ja auch von SchemeElement erbt, implizit immer auch ein Goal ist (bzw. wird)
abstract case class SchemeElement(var ttf: Option[TimeTerm] = None,
                                  var description: Option[String] = None,
                                  var goalType: Option[GoalTypeXb] = None,
                                  var min: Option[BigInt] = None) {
  def parallel_with (s: SchemeElement) = Parallel(this :: s :: Nil)
  def or (s: SchemeElement) = Choice(this :: s :: Nil)
  def then (s: SchemeElement) = Sequence(this :: s :: Nil)

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
    ttf = Some(moise.dsl.helper.now)
    this
  }

  // TODO: irgendwie noch einen Parameter ergänzen, damit Method Chaining klappt
  def never = {
    ttf = Some(moise.dsl.helper.never)
    this
  }

  def in(t: TimeSpan) = {
    ttf = Some(t)
    this
  }

  // TODO: Klären warum ohne Angabe des Typs hier BoxedUnit zurückgegeben wird
  def by_at_least (a: AgentCount): SchemeElement = {
    min = Some(a.count)
    this
  }
}
