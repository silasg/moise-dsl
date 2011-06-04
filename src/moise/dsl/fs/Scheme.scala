package moise.dsl.fs

object Scheme {
  def with_goal(s: SchemeElement) = new Scheme(s)
}

case class Scheme(val goal: SchemeElement,
                  var name: Option[String] = None,
                  var missions: List[Mission] = List()) extends Monitorable {

  def named(n: String) = new MonitoringScheme(goal, n, missions)

  def with_missions(m: Mission*): this.type = {
    missions = m.toList
    this
  }
}

class MonitoringScheme(goal: SchemeElement,
                        name: String,
                        missions: List[Mission] = List()) extends Scheme(goal, Some(name), missions)

