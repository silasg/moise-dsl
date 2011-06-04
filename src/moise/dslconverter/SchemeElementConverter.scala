package moise.dslconverter

import moise.dsl.fs.{Goal, Plan, SchemeElement, PlanOperator, Choice, Sequence, 
                     Parallel, GoalTypeVerb, achieve, maintain}
import moise.{Goal => GoalXb, GoalDefType => GoalDefXb, PlanType => PlanXb,
                Parallel => ParallelXb, Sequence => SequenceXb, Choice => ChoiceXb,
                Achievement => AchievementXb, Maintenance => MaintenanceXb, GoalType => GoalTypeXb}

import scala.collection.mutable.HashMap


object SchemeElementConverter {
  val usedPlanNames = new HashMap[SchemeElement, String]

  def convertToGoalXb(s: SchemeElement) =
    s match {
      case g: Goal => rememberGoalName(g); GoalXb(g.name)
      case p: Plan => GoalXb(getNameForPlan(p))
    }

  def convertToGoalDefXb(s: SchemeElement): GoalDefXb = {
    s match {
      case g: Goal => rememberGoalName(g); goalToGoalDefXb(g)
      case p: Plan => convertToPlanXb(p)
    }
  }

  def resetUsedPlanNames = usedPlanNames.clear

  // Goals sind hier immer Blätter des Schema-Baums, daher keine Rekursion
  private def goalToGoalDefXb(g: Goal) = GoalDefXb(argument = Seq(),
                                          plan = None,
                                          id = g.name,
                                          min = g.min,
                                          ds = g.description,
                                          typeValue = g.goalType.map{ convertGoalType(_) },
                                          ttf = g.ttf map { TimeTermConverter.convertToAttributeString(_) })

 // Diese Methode erstellt das Goal um den Plan als Wrapper
 private def convertToPlanXb(p: Plan) = {
   val planXb = PlanXb(properties = None,
                        goal = p.children map { convertToGoalDefXb _},
                        operator = convertOperator(p.operator),
                        successrate = p.successRate)
   GoalDefXb(argument = Seq(),
              plan = Some(planXb),
              id = getNameForPlan(p),
              min = p.min,
              ds = p.description,
              typeValue = p.goalType.map{ convertGoalType(_) },
              ttf = p.ttf map { TimeTermConverter.convertToAttributeString(_) })
  }

  private def convertOperator(o: PlanOperator) = o match {
                                                  case Choice => ChoiceXb
                                                  case Sequence => SequenceXb
                                                  case Parallel => ParallelXb
                                                }
  private def convertGoalType(t: GoalTypeVerb) = t match {
                                                  case `achieve` => AchievementXb
                                                  case `maintain` => MaintenanceXb
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

  private def rememberGoalName(g: Goal) = usedPlanNames += ((g, g.name))
}
