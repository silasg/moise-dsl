package moise.dsl.fs

object Scheme {
  def with_goal(s: SchemeElement) = new Scheme(s)
}

case class Scheme(val goal: SchemeElement,
                  var name: Option[String] = None,
                  var missions: List[Mission] = List()) extends Monitorable[Scheme] {

  def named(n: String) = {
    name = Some(n)
    this
  }

  def with_missions(m: Mission*) = {
    missions = m.toList
    this
  }
}
