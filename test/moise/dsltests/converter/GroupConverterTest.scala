package moise.dsltests.converter

import moise.dsl.ss._
import moise.dsl.fs.{Scheme, Goal}
import moise.dsl.helper.Implicits._
import oml.{GroupSpecificationType => GroupXb, Formationconstraints => ConstraintsXb,
            Roles => RolesXb, Role => RoleXb, Links => LinksXb, Link => LinkXb,
            Intragroup => IntragroupXb, Subgroups => SubGroupsXb, Compatibility => CompatibilityXb,
            Cardinality => CardinalityXb, RoleValue => CTypeRoleXb}
import moise.dslconverter.GroupConverter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class GroupConverterTest {
  @Before
  def setUp() = {}

  @After
  def tearDown() = {}

  @Test
  def can_convert_minimal_group = {
    // arrange
    val g = Group named "group"
    val expected = GroupXb(roles = None,
                            links = None,
                            subgroups = Seq(),
                            formationconstraints = None,
                            id = "group",
                            min = None,
                            max = None,
                            monitoringscheme = None)

    // act
    val is = GroupConverter.convert(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_maximal_group = {
    // arrange
    val house_owner = Role named "house_owner"
    val building_company = Role named "building_company"
    val site_prep_contractor = Role named "site_prep_contractor" complements building_company
    val auth_link = Link from house_owner to building_company is_not_valid_for subgroups in one.direction expresses authority inside group
    val company_compa = Compatibility from building_company to building_company in one.direction inside group
    val mon = Scheme with_goal (Goal named "dummy goal") named "monitoring_scheme"
    val subgroup = Group named "sub" at_least 2.times and_at_most 3.times
    val g = Group named "group" with_links (auth_link) with_compatibilities (company_compa) consists_of (house_owner exactly 1.times) and (site_prep_contractor exactly 1.times) monitored_by mon including subgroup with_cardinality (building_company at_least 1.times and_at_most 42.times)

    val expected = GroupXb(roles = Some(RolesXb(RoleXb(Some("house_owner"), Some(1), Some(1)),
                                            RoleXb(Some("site_prep_contractor"), Some(1), Some(1)))),
                            links = Some(LinksXb(LinkXb(Some("house_owner"), Some("building_company"), Some("authority"), IntragroupXb, false, false))),
                            subgroups = Seq(SubGroupsXb(Seq(), Seq(GroupXb(None, None, Seq(), None, "sub", Some(2), Some(3), None)))),
                            formationconstraints = Some(ConstraintsXb(Seq(CardinalityXb(Some(1), Some(42), CTypeRoleXb, "building_company")),
                                                                      Seq(CompatibilityXb("building_company", "building_company", IntragroupXb, false, false, Some("compatibility"))))),
                            id = "group",
                            min = None,
                            max = None,
                            monitoringscheme = Some("monitoring_scheme"))

    // act
    val is = GroupConverter.convert(g)

    // assert
    assertEquals(expected, is)
  }

  @Test
  def can_convert_group_with_cardinality_without_compatibility = {
    // arrange
    val building_company = Role named "building_company"
    val g = Group named "group" with_cardinality (building_company at_least 1.times and_at_most 42.times)

    val expected = GroupXb(roles = None,
                            links = None,
                            subgroups = Seq(),
                            formationconstraints = Some(ConstraintsXb(Seq(CardinalityXb(Some(1), Some(42), CTypeRoleXb, "building_company")),
                                                                      Seq())),
                            id = "group",
                            min = None,
                            max = None,
                            monitoringscheme = None)

    // act
    val is = GroupConverter.convert(g)

    // assert
    assertEquals(expected, is)
  }


  @Test
  def can_convert_group_without_cardinality_with_compatibility = {
    // arrange
    val building_company = Role named "building_company"
    val company_compa = Compatibility from building_company to building_company in one.direction inside group
    val g = Group named "group" with_compatibilities (company_compa)

    val expected = GroupXb(roles = None,
                            links = None,
                            subgroups = Seq(),
                            formationconstraints = Some(ConstraintsXb(Seq(),
                                                                      Seq(CompatibilityXb("building_company", "building_company", IntragroupXb, false, false, Some("compatibility"))))),
                            id = "group",
                            min = None,
                            max = None,
                            monitoringscheme = None)

    // act
    val is = GroupConverter.convert(g)

    // assert
    assertEquals(expected, is)
  }

}
