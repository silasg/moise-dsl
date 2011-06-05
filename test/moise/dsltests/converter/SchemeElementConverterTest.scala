package moise.dsltests.converter


import moise.dsl.fs.{Goal, Plan, SchemeElement, PlanOperator, Choice, Sequence,
                     Parallel, GoalTypeVerb, achieve, maintain, reach}
import moise.{Goal => GoalXb, GoalDefType => GoalDefXb, PlanType => PlanXb,
                Parallel => ParallelXb, Sequence => SequenceXb, Choice => ChoiceXb,
                Achievement => AchievementXb, Maintenance => MaintenanceXb, GoalType => GoalTypeXb}
import moise.dslconverter.SchemeElementConverter
import moise.dsl.helper.Implicits._
import scala.collection.mutable.HashMap

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class SchemeElementConverterTest {
  @Before
  def setUp() = SchemeElementConverter.resetUsedPlanNames

  @After
  def tearDown() {}

  @Test
  def can_convert_goal_to_goalxb = {
    // arrange
    val g = Goal named "goal"
    val expected = GoalXb("goal")

    // act
    val is = SchemeElementConverter.convertToGoalXb(g)

    // assert
    assertEquals(expected, is)
  }

  private def getDef(name: String) = GoalDefXb(argument = Seq(),
                                                plan = None,
                                                id = name,
                                                min = None,
                                                ds = None,
                                                typeValue = None,
                                                ttf = None)

  @Test
  def can_convert_plan_to_goalxb = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val expected = GoalXb("goal_2_")

    // act
    val is = SchemeElementConverter.convertToGoalXb(g1 parallel_with g2)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_minimal_goal = {
    // arrange
    val g = Goal named "goal"
    val expected = getDef("goal")

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_maximal_goal = {
    // arrange
    val g = Goal named "goal" described_as "desc" to reach by_at_least 1.agent now
    val expected = GoalDefXb(argument = Seq(),
                              plan = None,
                              id = "goal",
                              min = Some(1),
                              ds = Some("desc"),
                              typeValue = None,
                              ttf = Some("now"))

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_achievment_goal = {
    // arrange
    val g = Goal named "goal" to achieve
    val expected = GoalDefXb(argument = Seq(),
                              plan = None,
                              id = "goal",
                              min = None,
                              ds = None,
                              typeValue = Some(AchievementXb),
                              ttf = None)

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_maintenance_goal = {
    // arrange
    val g = Goal named "goal" to maintain
    val expected = GoalDefXb(argument = Seq(),
                              plan = None,
                              id = "goal",
                              min = None,
                              ds = None,
                              typeValue = Some(MaintenanceXb),
                              ttf = None)

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_minimal_plan_parallel = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val expected = GoalDefXb(argument = Seq(),
              plan = Some(PlanXb(properties = None,
                          goal = Seq(getDef("goal"), getDef("2")),
                          operator = ParallelXb,
                          successrate = None)),
              id = "goal_2_",
              min = None,
              ds = None,
              typeValue = None,
              ttf = None)

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g1 parallel_with g2)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_plan_choice = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val expected = GoalDefXb(argument = Seq(),
              plan = Some(PlanXb(properties = None,
                          goal = Seq(getDef("goal"), getDef("2")),
                          operator = ChoiceXb,
                          successrate = None)),
              id = "goal_2_",
              min = None,
              ds = None,
              typeValue = None,
              ttf = None)

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g1 or g2)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_plan_sequence = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val expected = GoalDefXb(argument = Seq(),
              plan = Some(PlanXb(properties = None,
                          goal = Seq(getDef("goal"), getDef("2")),
                          operator = SequenceXb,
                          successrate = None)),
              id = "goal_2_",
              min = None,
              ds = None,
              typeValue = None,
              ttf = None)

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(g1 then g2)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_maximal_plan = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val expected = GoalDefXb(argument = Seq(),
              plan = Some(PlanXb(properties = None,
                          goal = Seq(getDef("goal"), getDef("2")),
                          operator = ParallelXb,
                          successrate = Some(0.5))),
              id = "goal_2_",
              min = Some(1),
              ds = Some("desc"),
              typeValue = Some(AchievementXb),
              ttf = Some("never"))
    val plan = (g1 parallel_with g2) with_a_success_rate_of 0.5 described_as "desc" to achieve by_at_least 1.agent never

    // act
    val is = SchemeElementConverter.convertToGoalDefXb(plan)

    // assert
    assertEquals(expected, is)
  }



  @Test
  def can_generate_unique_plan_names = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val plan1 = g1 then g2
    val plan2 = g1 or g2
    val plan3 = g1 parallel_with g2
    val exp1 = "goal_2_"
    val exp2 = "goal_2__"
    val exp3 = "goal_2___"
    val exp4 = exp1

    // act
    val is1 = SchemeElementConverter.convertToGoalDefXb(plan1).id
    val is2 = SchemeElementConverter.convertToGoalDefXb(plan2).id
    val is3 = SchemeElementConverter.convertToGoalDefXb(plan3).id
    val is4 = SchemeElementConverter.convertToGoalDefXb(plan1).id

    // assert
    assertEquals(exp1, is1)
    assertEquals(exp2, is2)
    assertEquals(exp3, is3)
    assertEquals(exp4, is4)
  }


  @Test
  def can_generate_solve_plan_name_conflict_with_goal = {
    // arrange
    val g1 = Goal named "goal"
    val g2 = Goal named "2"
    val g3 = Goal named "goal_2_"
    val plan = g1 then g2
    val expected = "goal_2__"

    // act
    SchemeElementConverter.convertToGoalDefXb(g3)
    val is = SchemeElementConverter.convertToGoalDefXb(plan).id

    // assert
    assertEquals(expected, is)
  }
}
