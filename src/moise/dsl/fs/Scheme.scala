package moise.dsl.fs

object Scheme {
  def with_goal(s: SchemeElement) = new Scheme(s)
}

class Scheme(val goal: SchemeElement) {
  var monitoringScheme: Option[Scheme] = None
  var name: Option[String] = None
  var missions: List[Mission] = List()


  def named(n: String) = {
    name = Some(n)
    this
  }

  def monitored_by(s: Scheme) = {
    monitoringScheme = Some(s)
    this
  }

  def with_missions(m: Mission*) = {
    missions = m.toList
    this
  }
}
