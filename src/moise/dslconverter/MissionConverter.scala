package moise.dslconverter

import moise.dsl.fs.Mission
import oml.{Preferred => PreferredMissionXb, Mission => MissionXb}


object MissionConverter {
  def convert(m: Mission) = MissionXb(
    properties = None,
    goal = m.goals map { SchemeElementConverter convertToGoalXb(_)},
    preferred = m.preferredMissions map { m => PreferredMissionXb(m.name) },
    id = m.name,
    min = m.min,
    max = m.max)
}
