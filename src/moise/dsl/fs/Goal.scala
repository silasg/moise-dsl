package moise.dsl.fs

object Goal {
  def named(s: String) = {
    new Goal(s)
  }
}


case class Goal(val name: String) extends SchemeElement
