package moise.dsltests.fs

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs._
import moise.dsl.helper.Implicits._


class SchemeTest {

  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_create_minimal_scheme = {
    // arrange
    val goal = Goal named "test goal"

    // act
    val sch = Scheme with_goal goal

    // assert
    assertEquals(goal, sch.goal)
    assertEquals(None, sch.name)
    assertEquals(None, sch.monitoringScheme)
    assertEquals(List[Mission](), sch.missions)
  }

  @Test
  def can_create_maximal_scheme = {
    // arrange
    val goal = Goal named "test goal"
    val mon = Scheme with_goal goal named "mon"
    val m1 = Mission named "m1" to_reach goal
    val m2 = Mission named "m2" to_reach goal

    // act
    val sch = Scheme with_goal goal named "test-scheme" monitored_by mon with_missions (m1, m2)

    // assert
    assertEquals(goal, sch.goal)
    assertEquals(Some("test-scheme"), sch.name)
    assertEquals(Some(mon), sch.monitoringScheme)
    assertEquals(List(m1, m2), sch.missions)
  }
}
