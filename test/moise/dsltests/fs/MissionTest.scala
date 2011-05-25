package moise.dsltests.fs

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs._
import moise.dsl.helper.Implicits._

class MissionTest {
    @Before
    def setUp = {}

    @After
    def tearDown = {}

    @Test
    def can_create_empty_mission = {
      // arrange
      val expected = EmptyMission("i am empty")

      // act
      val em = Mission named "i am empty"

      // assert
      assertEquals(expected, em)
    }

    @Test
    def can_create_mission_with_one_goal = {
      // arrange
      val em = EmptyMission("i am empty")
      val g1 = Goal("goal1")
      val expected = em.to_reach(g1)

      // act
      val is = em to_reach g1

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_create_mission_with_more_goals = {
      // arrange
      val em = EmptyMission("i am empty")
      val g1 = Goal("goal1")
      val g2 = Goal("goal1")

      // act
      val is = em to_reach (g1, g2)

      // assert
      assertEquals(Seq(g1, g2), is.goals)
    }

    @Test
    def can_create_minimal_mission = {
      // arrange
      val goal = Goal named "test goal"

      // act
      val mission = Mission named "test mission" to_reach goal

      // assert
      assertEquals("test mission", mission.name)
      assertEquals(Seq(goal), mission.goals)
      assertEquals(List[Mission](), mission.preferredMissions)
      assertEquals(None, mission.min)
      assertEquals(None, mission.max)
    }

    @Test
    def can_create_full_mission = {
      // arrange
      val goal1 = Goal named "test goal1"
      val goal2 = Goal named "test goal1"
      val pref1 = Mission named "pref1" to_reach goal1
      val pref2 = Mission named "pref2" to_reach goal2

      // act
      val mission = Mission named "test mission" to_reach (goal1, goal2) with_preferred_missions (pref1, pref2) by_at_least 1.agent and_at_most 3.agents

      // assert
      assertEquals("test mission", mission.name)
      assertEquals(List(pref1, pref2), mission.preferredMissions)
      assertEquals(Seq(goal1, goal2), mission.goals)
      assertEquals(Some(1), mission.min)
      assertEquals(Some(3), mission.max)
    }

    @Test
    def can_reverse_min_max = {
      // arrange
      val goal = Goal named "test goal"

      // act
      val mission = Mission named "test mission" to_reach goal by_at_most 2.agents and_at_least 1.agent

      // assert
      assertEquals(Some(1), mission.min)
      assertEquals(Some(2), mission.max)
    }

    @Test
    def can_use_exactly = {
      // arrange
      val goal = Goal named "test goal"

      // act
      val mission = Mission named "test mission" to_reach goal by_exactly 2.agents

      // assert
      assertEquals(Some(2), mission.min)
      assertEquals(Some(2), mission.max)
    }
}