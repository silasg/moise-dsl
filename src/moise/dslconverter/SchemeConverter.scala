package moise.dslconverter

import moise.dsl.fs.Scheme
import moise.{Scheme => SchemeXb}

object SchemeConverter {
  def convert(s: Scheme) = SchemeXb(properties = None,
                                       goal = SchemeElementConverter.toGoalDefXb(s.goal),
                                       mission = s.missions map { MissionConverter.toMissionXb(_)},
                                       id = s.name,
                                       monitoringscheme =  s.monitoringScheme.map(_.name.getOrElse("")))
}
