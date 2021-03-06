package moise.dsl.fs

object Mission {
  def named(s: String) = EmptyMission(s)
}

// nötig um mindestens ein Goal in der Mission zu erzwingen
case class EmptyMission(val name: String) {
  def to_reach(goal: SchemeElement, moreGoals: SchemeElement*) = Mission(name, List(goal) ::: moreGoals.toList)
}

case class Mission(val name: String, val goals: Seq[SchemeElement],
                    var preferredMissions: List[Mission] = List(),
                    var min: Option[BigInt] = None,
                    var max: Option[BigInt] = None) {
  
  def with_preferred_missions(missions: Mission*) = {
    preferredMissions = missions.toList
    this
  }

  def by_at_least(agents: AgentCount) = {
    min = Some(agents.count)
    this
  }

  def by_at_most(agents: AgentCount) = {
    max = Some(agents.count)
    this
  }

  def by_exactly(agents: AgentCount) = {
    min = Some(agents.count)
    max = Some(agents.count)
    this
  }

  def and_at_most(agents: AgentCount) = by_at_most(agents)
  def and_at_least(agents: AgentCount) = by_at_least(agents)
}
