package moise.dsltests.fs


import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs._
import moise.dsl.helper.Implicits._
import moise.dsl.helper.TimeSpan


class PlanTest {

  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_use_choice_operator = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 or g2

    // assert
    assertEquals(Choice(List(g1, g2)), plan)
  }

  @Test
  def can_use_sequence_operator = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 then g2

    // assert
    assertEquals(Sequence(List(g1, g2)), plan)
  }


  @Test
  def can_use_parallel_operator = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 parallel_with g2

    // assert
    assertEquals(Parallel(List(g1, g2)), plan)
  }


  @Test
  def can_define_success_rate = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 then g2 with_a_success_rate_of 0.5

    // assert
    assertEquals(Some(0.5), plan.successRate)
  }

  @Test
  def can_omit_success_rate = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 then g2

    // assert
    assertEquals(None, plan.successRate)
  }


  @Test
  def can_use_scheme_element_members = {
    // arrange
    val g1 = Goal named "1"
    val g2 = Goal named "2"

    // act
    val plan = g1 then g2 described_as "a plan with advanced goal properties" to achieve in 5.minutes by_at_least 1.agent

    // assert
    assertEquals(Some(TimeSpan(5.minutes)), plan.ttf)
    assertEquals(Some("a plan with advanced goal properties"), plan.description)
    assertEquals(Some(achieve), plan.goalType)
    assertEquals(Some(1), plan.min)
  }


}
