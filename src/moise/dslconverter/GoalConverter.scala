package moise.dslconverter

import moise.dsl.fs.Goal
import moise.{Goal => GoalXb}
import moise.{GoalDefType => GoalDefXb}


object GoalConverter {

  def convertToGoalXb(g: Goal) = GoalXb(g.name)

  def convertToGoalDefXb(g: Goal) = GoalDefXb(Seq(), None, g.name, g.min, 
                                              g.description, g.goalType,
                                              g.ttf map { _.toAttributeString })
}
