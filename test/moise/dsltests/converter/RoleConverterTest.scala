package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss.{Role, GroupRole}
import moise.dsl.helper.Implicits._
import moise.dslconverter.RoleConverter
import oml.{RoleDefType => RoleDefXb, Role => RoleXb, Extends => ExtendsXb}



class RoleConverterTest {
  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_convert_roledef = {
    // arrange
    val e1 = Role named "e1"
    val e2 = Role named "e2"
    val r = Role named "r" complements (e1, e2)
    val expected = RoleDefXb(properties = None,
                             extendsValue = Seq(ExtendsXb(Some("e1")), ExtendsXb(Some("e2"))) ,
                             id = "r")

    // act
    val is = RoleConverter.convertRoleToRoleDefXb(r)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_grouprole = {
    // arrange
    val r = Role named "r" at_least 1.times and_at_most 2.times
    val expected = RoleXb(id = Some("r"),
                          min = Some(1),
                          max = Some(2))

    // act
    val is = RoleConverter.convertGroupRoleToRoleXb(r)

    // assert
    assertEquals(expected, is)
  }
}
