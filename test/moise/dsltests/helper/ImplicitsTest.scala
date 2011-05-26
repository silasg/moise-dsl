package moise.dsltests.helper

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs._
import moise.dsl.ss._
import moise.dsl.helper.Implicits._
import moise.dsl.helper._

class ImplicitsTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_use_inttotimespan = {
    // arrange
    val expected = TimeSpan(1)

    // act
    val is = intToTimeSpan(1)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_use_inttoagentcount = {
    // arrange
    val expected = AgentCount(1)

    // act
    val is = 1.agent

    // assert
    assertEquals(expected, is)
  }
  
  @Test
  def can_use_inttonumber = {
    // arrange
    val expected = Number(1)

    // act
    val is = 1.times

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_grouptosubgroup = {
    // arrange
    val g = Group named "group"
    val expected = SubGroup(g)

    // act
    val is = groupToSubGroup(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_use_roletogrouprole = {
    // arrange
    val r = Role named "r"
    val expected = GroupRole(Some(r))

    // act
    val is = roleToGroupRole(r)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_cardinalitytypetocardinality = {
    // arrange
    val r = Role named "r"
    val expected = Cardinality(r)

    // act
    val is = cardinalityTypeToCardinality(r)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_grouproletocardinality = {
    // arrange
    val r = Role named "r"
    val gr = GroupRole(Some(r))
    val expected = Cardinality(r)

    // act
    val is = groupRoleToCardinality(gr)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_subgrouptocardinality = {
    // arrange
    val g = Group named "g"
    val sg = SubGroup(g)
    val expected = Cardinality(g)

    // act
    val is = subGroupToCardinality(sg)

    // assert
    assertEquals(expected, is)
  }
}
