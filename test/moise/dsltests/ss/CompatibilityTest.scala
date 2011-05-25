package moise.dsltests.ss

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss._
import moise.dsl.helper.Implicits._

class CompatibilityTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_empty_compatibility = {
    // arrange
    val r1 = Role named "r1"

    // act
    val em = Compatibility from r1

    // assert
    assertEquals(EmptyCompatiblity(r1), em)

  }

  @Test
  def can_create_minimal_compatibility = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2

    // assert
    assertEquals(r1, c.fromRole)
    assertEquals(r2, c.toRole)
    assertEquals(Some(compatibility), c.relType)
    assertEquals(None, c.scope)
    assertEquals(None, c.biDir)
    assertEquals(None, c.extendsToSubGroups)
  }

  @Test
  def can_create_maximal_compatibility = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2 between groups in both.directions is_valid_for subgroups

    // assert
    assertEquals(Some(InterGroup), c.scope)
    assertEquals(Some(true), c.biDir)
    assertEquals(Some(true), c.extendsToSubGroups)
  }

  @Test
  def can_create_compatibility_with_two_directions = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2 in two.directions

    // assert
    assertEquals(Some(true), c.biDir)
  }

  @Test
  def can_create_compatibility_with_one_direction = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2 in one.direction

    // assert
    assertEquals(Some(false), c.biDir)
  }

  @Test
  def can_create_intra_group_compatibility = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2 inside group

    // assert
    assertEquals(Some(IntraGroup), c.scope)
  }


  @Test
  def can_create_compatibility_that_is_not_valid_for_subgroups = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val c = Compatibility from r1 to r2 is_not_valid_for subgroups

    // assert
    assertEquals(Some(false), c.extendsToSubGroups)
  }
}
