package moise.dslconverter

import moise.dsl.fs.{Goal, Plan, SchemeElement}
import moise.{Goal => GoalXb, GoalDefType => GoalDefXb, PlanType => PlanXb}

import scala.collection.mutable.HashMap


object SchemeElementConverter {
  val usedPlanNames = new HashMap[Plan, String]

  def toGoalXb(s: SchemeElement) =
    s match {
      case g: Goal => GoalXb(g.name)
      case p: Plan => GoalXb(getNameForPlan(p))
    }

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
                                          ttf = g.ttf map { TimeTermConverter.convertToAttributeString(_) })

 // Diese Methode erstellt das Goal um den Plan als Wrapper
 private def convertToPlanXb(p: Plan) = {
   val planXb = PlanXb(properties = None,
                        goal = p.children map { toGoalDefXb _},
                        operator = p.operator,
                        successrate = p.successRate)
   GoalDefXb(argument = Seq(),
              plan = Some(planXb),
              id = getNameForPlan(p),
              min = p.min,
              ds = p.description,
              typeValue = p.goalType,
              ttf = p.ttf map { TimeTermConverter.convertToAttributeString(_) })
  }

  private def getNameForPlan(p: Plan): String = {
    def generatePlanName(p: Plan) = {
      var name = ""
      for (c <- p.children) c match {
        case g: Goal =>  name = name + "_" + g.name
        case pl: Plan => name = name + "_" + getNameForPlan(pl)
      }

      def nameAlreadyUsed = usedPlanNames.exists(_._2 == name)
      while (nameAlreadyUsed) name = name + "_"
      usedPlanNames += ((p, name))
      name
    }
    usedPlanNames.getOrElse(p, generatePlanName(p))
  }
}
