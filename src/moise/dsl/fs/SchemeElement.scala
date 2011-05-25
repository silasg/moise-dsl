package moise.dsl.fs

import moise.dsl.helper.{TimeTerm, TimeSpan, now, never}

trait GoalTypeVerb
case object achieve extends GoalTypeVerb
case object maintain extends GoalTypeVerb
case object reach extends GoalTypeVerb // reach macht keine Aussage über den Goaltype

// SchemeElement implementiert bis auf den Namen schon alles von Goal, weil ein Plan,
// der ja auch von SchemeElement erbt, implizit immer auch ein Goal ist (bzw. wird)
abstract case class SchemeElement(var ttf: Option[TimeTerm] = None,
                                  var description: Option[String] = None,
                                  var goalType: Option[GoalTypeVerb] = None,
                                  var min: Option[BigInt] = None) {
  def parallel_with (s: SchemeElement) = Parallel(this :: s :: Nil)
  def or (s: SchemeElement) = Choice(this :: s :: Nil)
  def then (s: SchemeElement) = Sequence(this :: s :: Nil)

  def described_as(s: String): this.type = {
    description = Some(s)
    this
  }

  def to (g: GoalTypeVerb): this.type = {
    g match {
      case `reach` => goalType = None
      case _ => goalType = Some(g)
    }
    this
  }

  def now: this.type = {
    ttf = Some(moise.dsl.helper.now)
    this
  }

  def never: this.type = {
    ttf = Some(moise.dsl.helper.never)
    this
  }

  def in(t: TimeSpan): this.type = {
    ttf = Some(t)
    this
  }

  def by_at_least (a: AgentCount): this.type = {
    min = Some(a.count)
    this
  }
}
