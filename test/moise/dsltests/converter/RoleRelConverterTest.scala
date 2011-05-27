package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss._
import moise.{Compatibility => CompatibilityXb, Link => LinkXb,
              Intergroup => IntergroupXb, Intragroup => IntragroupXb}
import moise.dslconverter.RoleRelConverter

class RoleRelConverterTest {

  @Before
  def setUp() = {}

  @After
  def tearDown() {}

  @Test
  def can_convert_compatibility_intragroup_bidir_subgroups = {
    // arrange
    val r1 = Role named "r1"
    val r2 = Role named "r2"
    val comp = Compatibility from r1 to r2 inside group is_valid_for subgroups in both.directions
    val expected = CompatibilityXb(from = "r1",
                                    to = "r2",
                                    typeValue = Some("compatibility"),
                                    scope = IntragroupXb,
                                    extendssubgroups = true,
                                    bidir = true)

    // act
    val is = RoleRelConverter.convertCompatibility(comp)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_authority_link_intergroup_unidir_no_subgroups = {
    // arrange
    val r1 = Role named "r1"
    val link = Link from r1 expresses authority between groups is_not_valid_for subgroups in one.direction
    val expected = LinkXb(from = Some("r1"),
                          to = None,
                          typeValue = Some("authority"),
                          scope = IntergroupXb,
                          extendssubgroups = false,
                          bidir = false)

    // act
    val is = RoleRelConverter.convertLink(link)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_communication_link = {
    // arrange
    val r2 = Role named "r2"
    val link = Link to r2 expresses communication
    val expected = LinkXb(from = None,
                          to = Some("r2"),
                          typeValue = Some("communication"),
                          scope = IntragroupXb,
                          extendssubgroups = false,
                          bidir = false)

    // act
    val is = RoleRelConverter.convertLink(link)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_custom_link = {
    // arrange
    val t = Linktype named "hyperlink"
    val link = Link expresses t
    val expected = LinkXb(from = None,
                          to = None,
                          typeValue = Some("hyperlink"),
                          scope = IntragroupXb,
                          extendssubgroups = false,
                          bidir = false)

    // act
    val is = RoleRelConverter.convertLink(link)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_Empty_link = {
    // arrange
    val link = LinkClass()
    val expected = LinkXb(from = None,
                          to = None,
                          typeValue = None,
                          scope = IntragroupXb,
                          extendssubgroups = false,
                          bidir = false)

    // act
    val is = RoleRelConverter.convertLink(link)

    // assert
    assertEquals(expected, is)
  }

}
