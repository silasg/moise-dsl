package moise.dsltests.ns

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ns._
import moise.dsl.ss.Role
import moise.dsl.fs.{Mission, Goal}
import moise.dsl.helper.Implicits._
import moise.dsl.helper.{TimeSpan, never}

class NormTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_empty_norm = {
    // arrange
    val expected = EmptyNorm("empty")

    // act
    val en = Norm named "empty"

    // assert
    assertEquals(expected, en)
  }
  
  @Test
  def can_create_norm_without_mission = {
    // arrange
    val en = Norm named "empty"
    val r = Role named "role"
    val expected = NormWithoutMission("empty", Permission, r)

    // act
    val nwm = en permits r

    // assert
    assertEquals(expected, nwm)
  }

  @Test
  def can_create_minimal_norm = {
    // arrange
    val r = Role named "role"
    val m = Mission named "my mission" to_reach (Goal named "test goal")

    // act
    val norm = Norm named "min norm" obligates r participation_in m 

    // assert
    assertEquals("min norm", norm.name)
    assertEquals(Obligation, norm.normType)
    assertEquals(r, norm.role)
    assertEquals(m, norm.mission)
    assertEquals(None, norm.timeConstraint)
    assertEquals(None, norm.condition)
  }


  @Test
  def can_create_maximal_norm = {
    // arrange
    val r = Role named "role"
    val m = Mission named "test mission" to_reach (Goal named "test goal")

    // act
    val norm = Norm named "max norm" permits r participation_in m valid_for 1.hour with_the_condition_that "I am cool"

    // assert
    assertEquals("max norm", norm.name)
    assertEquals(Permission, norm.normType)
    assertEquals(r, norm.role)
    assertEquals(m, norm.mission)
    assertEquals(Some(TimeSpan(1.hour)), norm.timeConstraint)
    assertEquals(Some("I am cool"), norm.condition)
  }


  @Test
  def can_create_norm_with_not_numeric_timeterm = {
    // arrange
    val r = Role named "role"
    val m = Mission named "test mission" to_reach (Goal named "test goal")

    // act
    val norm = Norm named "max norm" permits r participation_in m valid never

    // assert
    assertEquals(Some(never), norm.timeConstraint)
  }

}
