package moise.dsltests.ss

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import scala.collection.mutable.ListBuffer

import moise.dsl.ss._
import moise.dsl.helper.Implicits._

class GroupTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_minimal_group = {
    // arrange

    // act
    val g = Group named "I am empty"

    // assert
    assertEquals("I am empty", g.name)
    assertEquals(ListBuffer[GroupRole](),g.roles)
    assertEquals(ListBuffer[SubGroup](), g.subGroups)
    assertEquals(ListBuffer[Cardinality](), g.cardinalities)
    assertEquals(List[LinkClass](), g.links)
    assertEquals(List[CompatibilityClass](), g.compatibilities)
    assertEquals(None, g.monitoringScheme)
    assertEquals(None, g.min)
    assertEquals(None, g.max)
  }


  @Test
  def can_create_group_with_links = {
    // arrange
    val l1 = Link from (Role named "r1") to (Role named "r2")
    val l2 = Link from (Role named "r3") to (Role named "r4")

    // act
    val g = Group named "g" with_links (l1, l2)

    // assert
    assertEquals(List(l1, l2), g.links)
  }


  @Test
  def can_create_group_with_compatibilities = {
    // arrange
    val c1 = Compatibility from (Role named "r1") to (Role named "r2")
    val c2 = Compatibility from (Role named "r3") to (Role named "r4")

    // act
    val g = Group named "g" with_compatibilities (c1, c2)

    // assert
    assertEquals(List(c1, c2), g.compatibilities)
  }

  @Test
  def can_create_group_with_cardinalities = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"

    // act
    val g = Group named "g" with_cardinality r1 and_cardinality r2

    // assert
    assertEquals(ListBuffer(Cardinality(r1), Cardinality(r2)), g.cardinalities)
    
  }

  @Test
  def can_create_group_with_subgroups = {
    // arrange
    val s1 = Group named "sub1"
    val s2 = Group named "sub2" at_least 2.times and_at_most 3.times // define cardinality directly to make it's assertion possible

    // act
    val g = Group named "g" including s1 and s2

    // assert
    assertEquals(ListBuffer(SubGroup(s1), s2), g.subGroups) // s2 is already subgroup because of cardinality
    assertEquals(Some(2), s2.min)
    assertEquals(Some(3), s2.max)
  }
  
  @Test
  def can_create_group_with_roles = {
    // arrange
    val r1 = Role named "r1" exactly 1.times  // define cardinality directly to make it's assertion possible
    val r2 = Role named "r2" at_most 1.times  // define cardinality directly to make it's assertion possible

    // act
    val g = Group named "g" consists_of r1 and r2

    // assert
    assertEquals(ListBuffer(r1, r2), g.roles)
    assertEquals(Some(1), r1.min)
    assertEquals(Some(1), r1.max)
    assertEquals(Some(1), r2.max)
  }

   @Test
  def can_create_group_any_roles = {
    // arrange

    // act
    val g = Group named "g" consists_of any_role

    // assert
    assertEquals(ListBuffer(GroupRole(None)), g.roles)
  }
 
}
