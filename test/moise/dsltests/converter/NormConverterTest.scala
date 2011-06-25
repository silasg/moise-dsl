package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss.Role
import moise.dsl.fs.{Mission, Goal}
import moise.dsl.ns.Norm
import moise.dsl.helper.now
import moise.dslconverter.NormConverter
import oml.{Norm => NormXb}
import moise.dslconverter._
import moise.dsl.helper.Implicits._

class NormConverterTest {
  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_convert_minmal_obligation = {
    // arrange
    val r = Role named "r"
    val m = Mission named "m" to_reach (Goal named "goal")
    val norm = Norm named "norm" obligates r participation_in m
    val expected = NormXb(id = "norm",
                          condition = None,
                          role = "r",
                          typeValue = "obligation",
                          mission = "m",
                          timeconstraint = None)

    // act
    val is = NormConverter.convertNorm(norm)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_minmal_permission = {
    // arrange
    val r = Role named "r"
    val m = Mission named "m" to_reach (Goal named "goal")
    val norm = Norm named "norm" permits r participation_in m
    val expected = NormXb(id = "norm",
                          condition = None,
                          role = "r",
                          typeValue = "permission",
                          mission = "m",
                          timeconstraint = None)

    // act
    val is = NormConverter.convertNorm(norm)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_maximal_norm = {
    // arrange
    val r = Role named "r"
    val m = Mission named "m" to_reach (Goal named "goal")
    val norm = Norm named "norm" obligates r participation_in m valid now with_the_condition_that "42"
    val expected = NormXb(id = "norm",
                          condition = Some("42"),
                          role = "r",
                          typeValue = "obligation",
                          mission = "m",
                          timeconstraint = Some("now"))

    // act
    val is = NormConverter.convertNorm(norm)

    // assert
    assertEquals(expected, is)
  }
}
