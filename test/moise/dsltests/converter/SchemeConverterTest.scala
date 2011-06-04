package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs.{Scheme, Goal, Mission}
import moise.dslconverter.SchemeConverter
import moise.{Scheme => SchemeXb, GoalDefType => GoalDefXb, Mission => MissionXb, Goal => GoalXb}

class SchemeConverterTest {
  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_convert_scheme = {
    // arrange
    val goalDefXb =  GoalDefXb(argument = Seq(),
                                plan = None,
                                id = "goal",
                                min = None,
                                ds = None,
                                typeValue = None,
                                ttf = None)
    val missionXb = MissionXb(
                      properties = None,
                      goal = Seq(GoalXb("goal2")),
                      preferred = Seq(),
                      id = "mission",
                      min = None,
                      max = None)
    val expected = SchemeXb(properties = None,
                             goal = goalDefXb,
                             mission = Seq(missionXb),
                             id = Some("scheme"),
                             monitoringscheme = Some("mon"))
    val goal = Goal named "goal"
    val mission = Mission named "mission" to_reach (Goal named "goal2")
    val mon = Scheme with_goal goal named "mon"
    val sch = Scheme with_goal goal named "scheme" with_missions mission monitored_by mon

    // act
    val is = SchemeConverter.convert(sch)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_empty_scheme = {
    // arrange
    val goalDefXb =  GoalDefXb(argument = Seq(),
                                plan = None,
                                id = "goal",
                                min = None,
                                ds = None,
                                typeValue = None,
                                ttf = None)
    val expected = SchemeXb(properties = None,
                             goal = goalDefXb,
                             mission = Seq(),
                             id = None,
                             monitoringscheme = None)
    val goal = Goal named "goal"
    val sch = Scheme with_goal goal

    // act
    val is = SchemeConverter.convert(sch)

    // assert
    assertEquals(expected, is)
  }

}
