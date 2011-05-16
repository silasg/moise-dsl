package moise.dslconverter

import moise.dsl.fs.Mission
import moise.{Preferred => PreferredMissionXb, Mission => MissionXb}


object MissionConverter {
  def toMissionXb(m: Mission) = MissionXb(
    properties = None,
    goal = m.goals map { SchemeElementConverter toGoalXb(_)},
    preferred = m.preferredMissions map { m => PreferredMissionXb(m.name) },
    id = m.name,
    min = m.min,
    max = m.max)
}
