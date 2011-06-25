package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs.{Goal, Mission}
import moise.dsl.helper.Implicits._
import moise.dslconverter.MissionConverter
import oml.{Preferred => PreferredXb, Mission => MissionXb, Goal => GoalXb}

class MissionConverterTest {
  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_convert_minimal_mission = {
    // arrange
    val expected = MissionXb(
                      properties = None,
                      goal = Seq(GoalXb("goal")),
                      preferred = Seq(),
                      id = "mission",
                      min = None,
                      max = None)
    val mission = Mission named "mission" to_reach (Goal named "goal")
    
    // act
    val is = MissionConverter.convert(mission)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_maximal_mission = {
    // arrange
    val pref1 = PreferredXb("pref1")
    val pref2 = PreferredXb("pref2")
    val expected = MissionXb(
                      properties = None,
                      goal = Seq(GoalXb("goal1"), GoalXb("goal2")),
                      preferred = Seq(pref1, pref2),
                      id = "mission",
                      min = Some(1),
                      max = Some(10))
    val g1 = Goal named "goal1"
    val g2 = Goal named "goal2"
    val m1 = Mission named "pref1" to_reach g1
    val m2 = Mission named "pref2" to_reach g2
    val mission = Mission named "mission" to_reach (g1, g2) with_preferred_missions (m1, m2) by_at_least 1.agent and_at_most 10.agents

    // act
    val is = MissionConverter.convert(mission)

    // assert
    assertEquals(expected, is)
  }
  
}
