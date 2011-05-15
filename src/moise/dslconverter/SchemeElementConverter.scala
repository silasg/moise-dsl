package moise.dslconverter

import moise.dsl.fs.{Goal, Plan, SchemeElement}
import moise.{Goal => GoalXb, GoalDefType => GoalDefXb, PlanType => PlanXb}

import scala.collection.mutable.ListBuffer


object SchemeElementConverter {
  val usedPlanNames = new ListBuffer[String]

  def goalToGoalXb(g: Goal) = GoalXb(g.name)

  def toGoalDefXb(s: SchemeElement): GoalDefXb = {
    s match {
      case g: Goal => goalToGoalDefXb(g)
      case p: Plan => convertToPlanXb(p)
    }
  }

  // Goals sind hier immer Blätter des Schema-Baums, daher keine Rekursion
  private def goalToGoalDefXb(g: Goal) = GoalDefXb(argument = Seq(),
                                          plan = None,
                                          id = g.name,
                                          min = g.min,
                                          ds = g.description,
                                          typeValue = g.goalType,
                                          ttf = g.ttf map { _.toAttributeString })

 // Diese Methode erstellt das Goal um den Plan als Wrapper
 private def convertToPlanXb(p: Plan) = {
   val planXb = PlanXb(properties = None,
                        goal = p.children map { toGoalDefXb _},
                        operator = p.operator,
                        successrate = p.successRate)
   GoalDefXb(argument = Seq(),
              plan = Some(planXb),
              id = generatePlanName(p),
              min = p.min,
              ds = p.description,
              typeValue = p.goalType,
              ttf = p.ttf map { _.toAttributeString })
  }

  private def generatePlanName(p: Plan): String = {
    var name = ""
    for (c <- p.children) c match {
      case g: Goal =>  name = name + "_" + g.name
      case pl: Plan => name = name + "_" + generatePlanName(pl)
    }
    while (usedPlanNames.contains(name))
      name = name + "_"
    usedPlanNames += name
    name
  }
}
