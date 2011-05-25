package moise.dsltests.fs

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs._
import moise.dsl.helper.Implicits._
import moise.dsl.helper.{never, now, TimeSpan}

class GoalTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_minimal_goal = {
    // arrange
    val expected = Goal("goalname")

    // act
    val goal = Goal named "goalname"

    // assert
    assertEquals(expected, goal)
    assertEquals("goalname", goal.name)
    assertEquals(None, goal.ttf)
    assertEquals(None, goal.description)
    assertEquals(None, goal.goalType)
    assertEquals(None, goal.min)
  }

  @Test
  def can_create_maximal_goal = {
    // arrange

    // act
    val goal = Goal named "goalname" described_as "a maximal goal" to reach in 5.minutes by_at_least 1.agent

    // assert
    assertEquals("goalname", goal.name)
    assertEquals(Some(TimeSpan(5.minutes)), goal.ttf)
    assertEquals(Some("a maximal goal"), goal.description)
    assertEquals(None, goal.goalType)
    assertEquals(Some(1), goal.min)
  }


  @Test
  def can_create_achievment_goal = {
    // arrange

    // act
    val goal = Goal named "goalname" to achieve

    // assert
    assertEquals(Some(achieve), goal.goalType)
  }

  @Test
  def can_create_maintenance_goal = {
    // arrange

    // act
    val goal = Goal named "goalname" to maintain

    // assert
    assertEquals(Some(maintain), goal.goalType)
  }


  @Test
  def can_create_goal_to_reach_now = {
    // arrange

    // act
    val goal = Goal named "goalname" to reach now

    // assert
    assertEquals(Some(now), goal.ttf)
  }

  @Test
  def can_create_goal_to_reach_never = {
    // arrange

    // act
    val goal = Goal named "goalname" to reach never

    // assert
    assertEquals(Some(never), goal.ttf)
  }

}
