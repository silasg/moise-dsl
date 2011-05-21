package moise.dsl.fs

object Scheme {
  def with_goal(s: SchemeElement) = new Scheme(s)
}

class Scheme(val goal: SchemeElement) extends Monitorable {
  var name: Option[String] = None
  var missions: List[Mission] = List()

  def named(n: String) = {
    name = Some(n)
    this
  }

  def with_missions(m: Mission*) = {
    missions = m.toList
    this
  }
}
