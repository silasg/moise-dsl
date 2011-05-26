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
    var expected = TimeSpan(1)

    // act
    var is = intToTimeSpan(1)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_use_inttoagentcount = {
    // arrange
    var expected = AgentCount(1)

    // act
    var is = 1.agent

    // assert
    assertEquals(expected, is)
  }
  
  @Test
  def can_use_inttonumber = {
    // arrange
    var expected = Number(1)

    // act
    var is = 1.times

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_grouptosubgroup = {
    // arrange
    var g = Group named "group"
    var expected = SubGroup(g)

    // act
    var is = groupToSubGroup(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_use_roletogrouprole = {
    // arrange
    var r = Role named "r"
    var expected = GroupRole(Some(r))

    // act
    var is = roleToGroupRole(r)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_cardinalitytypetocardinality = {
    // arrange
    var r = Role named "r"
    var expected = Cardinality(r)

    // act
    var is = cardinalityTypeToCardinality(r)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_grouproletocardinality = {
    // arrange
    var r = Role named "r"
    var gr = GroupRole(Some(r))
    var expected = Cardinality(r)

    // act
    var is = groupRoleToCardinality(gr)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_use_subgrouptocardinality = {
    // arrange
    var g = Group named "g"
    var sg = SubGroup(g)
    var expected = Cardinality(g)

    // act
    var is = subGroupToCardinality(sg)

    // assert
    assertEquals(expected, is)
  }
}
