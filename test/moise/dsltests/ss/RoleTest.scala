package moise.dsltests.ss

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import scala.collection.mutable.ListBuffer

import moise.dsl.ss._
import moise.dsl.helper.Implicits._

class RoleTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_minimal_role = {
    // arrange
    val expected = Role("min")

    // act
    val r = Role named "min"

    // assert
    assertEquals(expected, r)
    assertEquals(ListBuffer[Role](), r.extendedRoles)
  }


  @Test
  def can_create_derived_role = {
    // arrange
    val father = Role("I am your father, Luke")

    // act
    val r = Role named "Luke" complements father

    // assert
    assertEquals(ListBuffer(father), r.extendedRoles)
  }
}
