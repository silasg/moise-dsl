package moise.dsl.fs

object Goal {
  def named(s: String) = Goal(s)
}


case class Goal(val name: String) extends SchemeElement
