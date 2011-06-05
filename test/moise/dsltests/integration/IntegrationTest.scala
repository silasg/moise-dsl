package moise.dsl.tests.integration

import moise.dsl.fs._
import moise.dsl.ss._
import moise.dsl.ns._
import moise.dsl.os._
import moise.dsl.helper.Implicits._
import moise.dslconverter.XMLConverter

import com.google.xmldiff._

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class IntegrationTest {
    
    @Before
    def setUp() = {}

    @After
    def tearDown() {}

    @Test
    def can_formulate_house_building_example = {
      def arrange_house_building_os = {
        // SS
        val house_owner = Role named "house_owner"
        val building_company = Role named "building_company"
        val site_prep_contractor = Role named "site_prep_contractor" complements building_company
        val bricklayer = Role named "bricklayer" complements building_company
        val roofer = Role named "roofer" complements building_company
        val window_fitter = Role named "window_fitter" complements building_company
        val door_fitter = Role named "door_fitter" complements building_company
        val plumber = Role named "plumber" complements building_company
        val electrician = Role named "electrician" complements building_company
        val painter = Role named "painter" complements building_company

        val auth_link = Link from house_owner to building_company is_not_valid_for subgroups in one.direction expresses authority inside group
        val com_link = Link from building_company to house_owner is_not_valid_for subgroups in one.direction expresses communication inside group
        val company_compa = Compatibility from building_company to building_company in one.direction inside group
        val house_group = Group named "house_group" with_links (auth_link, com_link) with_compatibilities (company_compa) consists_of (house_owner exactly 1.times) and (site_prep_contractor exactly 1.times) and (bricklayer exactly 1.times) and (roofer exactly 1.times) and (window_fitter exactly 1.times) and (door_fitter exactly 1.times) and (plumber exactly 1.times) and (electrician exactly 1.times) and (painter exactly 1.times)

        // FS
        val site_prepared = Goal named "site_prepared" described_as "prepare the site for the next tasks" to reach in 20.minutes
        val floors_laid = Goal named "floors_laid" to reach in 25.minutes
        val walls_built = Goal named "walls_built" to reach in 40.minutes
        val roof_built = Goal named "roof_built" to reach in 30.minutes
        val windows_fitted = Goal named "windows_fitted" to reach in 10.minutes
        val doors_fitted = Goal named "doors_fitted" to reach in 10.minutes
        val plumbing_installed = Goal named "plumbing_installed" to reach in 20.minutes
        val electrical_system_installed = Goal named "electrical_system_installed" to reach in 20.minutes
        val exterior_painted = Goal named "exterior_painted" to reach in 20.minutes
        val interior_painted = Goal named "interior_painted" to reach in 30.minutes

        val house_built = site_prepared then floors_laid then walls_built then (windows_fitted parallel_with roof_built parallel_with doors_fitted) then (plumbing_installed parallel_with electrical_system_installed parallel_with exterior_painted) then interior_painted named "house_built"

        val management_of_house_building = Mission named "management_of_house_building" to_reach house_built by_exactly 1.agent
        val prepare_site = Mission named "prepare_site" to_reach site_prepared by_exactly 1.agent
        val lay_floors = Mission named "lay_floors" to_reach floors_laid by_exactly 1.agent
        val build_walls = Mission named "build_walls" to_reach walls_built by_exactly 1.agent
        val build_roof = Mission named "build_roof" to_reach roof_built by_exactly 1.agent
        val fit_windows = Mission named "fit_windows" to_reach windows_fitted by_exactly 1.agent
        val fit_doors = Mission named "fit_doors" to_reach doors_fitted by_exactly 1.agent
        val install_plumbing = Mission named "install_plumbing" to_reach plumbing_installed by_exactly 1.agent
        val install_electrical_system = Mission named "install_electrical_system" to_reach electrical_system_installed by_exactly 1.agent
        val paint_house = Mission named "paint_house" to_reach (exterior_painted, interior_painted) by_exactly 1.agent

        val build_house_sch = Scheme with_goal house_built named "build_house_sch" with_missions (management_of_house_building, prepare_site, lay_floors, build_walls, build_roof, fit_windows, fit_doors, install_plumbing, install_electrical_system, paint_house)

        // NS
        val n1 = Norm named "n1" obligates house_owner participation_in management_of_house_building valid_for 2.minutes
        val n2 = Norm named "n2" obligates site_prep_contractor participation_in prepare_site
        val n3 = Norm named "n3" obligates bricklayer participation_in lay_floors
        val n4 = Norm named "n4" obligates bricklayer participation_in build_walls
        val n5 = Norm named "n5" obligates roofer participation_in build_roof
        val n6 = Norm named "n6" obligates window_fitter participation_in fit_windows
        val n7 = Norm named "n7" obligates door_fitter participation_in fit_doors
        val n8 = Norm named "n8" obligates plumber participation_in install_plumbing
        val n9 = Norm named "n9" obligates electrician participation_in install_electrical_system
        val n10 = Norm named "n10" obligates painter participation_in paint_house

        // OS
        OS with_ss { SS with_group (house_group) with_roles (house_owner, building_company, site_prep_contractor, bricklayer, roofer, window_fitter, door_fitter, plumber, electrician, painter) } with_ns { NS with_norms (n1, n2, n3, n4, n5, n6, n7, n8, n9, n10) } with_fs { FS with_schemes build_house_sch } named "house_construction"
      }

      def arrange_expected_xml =
        <organisational-specification
          os-version="0.8"
          id="house_construction"
          xmlns="http://moise.sourceforge.net/os"
          xmlns:moise="http://moise.sourceforge.net/os"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
          <structural-specification>
            <role-definitions>
              <role id="house_owner"></role>
              <role id="building_company"></role>
              <role id="site_prep_contractor"><extends role="building_company"></extends></role>
              <role id="bricklayer"><extends role="building_company"></extends></role>
              <role id="roofer"><extends role="building_company"></extends></role>
              <role id="window_fitter"><extends role="building_company"></extends></role>
              <role id="door_fitter"><extends role="building_company"></extends></role>
              <role id="plumber"><extends role="building_company"></extends></role>
              <role id="electrician"><extends role="building_company"></extends></role>
              <role id="painter"><extends role="building_company"></extends></role>
            </role-definitions>
            <group-specification id="house_group">
              <roles>
                <role max="1" min="1" id="house_owner"></role>
                <role max="1" min="1" id="site_prep_contractor"></role>
                <role max="1" min="1" id="bricklayer"></role>
                <role max="1" min="1" id="roofer"></role>
                <role max="1" min="1" id="window_fitter"></role>
                <role max="1" min="1" id="door_fitter"></role>
                <role max="1" min="1" id="plumber"></role>
                <role max="1" min="1" id="electrician"></role>
                <role max="1" min="1" id="painter"></role>
              </roles>
              <links>
                <link type="authority" to="building_company" from="house_owner"></link>
                <link type="communication" to="house_owner" from="building_company"></link>
              </links>
              <formation-constraints>
                <compatibility type="compatibility" to="building_company" from="building_company"></compatibility>
              </formation-constraints>
            </group-specification>
          </structural-specification>
          <functional-specification>
            <scheme id="build_house_sch">
              <goal id="house_built">
                <plan operator="sequence">
                  <goal ttf="20 minutes" ds="prepare the site for the next tasks" id="site_prepared"></goal>
                  <goal ttf="25 minutes" id="floors_laid"></goal>
                  <goal ttf="40 minutes" id="walls_built"></goal>
                  <goal id="windows_fitted_roof_built_doors_fitted_">
                    <plan operator="parallel">
                      <goal ttf="30 minutes" id="roof_built"></goal>
                      <goal ttf="10 minutes" id="windows_fitted"></goal>
                      <goal ttf="10 minutes" id="doors_fitted"></goal>
                    </plan>
                  </goal>
                  <goal id="plumbing_installed_electrical_system_installed_exterior_painted_">
                    <plan operator="parallel">
                      <goal ttf="20 minutes" id="plumbing_installed"></goal>
                      <goal ttf="20 minutes" id="electrical_system_installed"></goal>
                      <goal ttf="20 minutes" id="exterior_painted"></goal>
                    </plan>
                  </goal>
                  <goal ttf="30 minutes" id="interior_painted"></goal>
                </plan>
              </goal>
              <mission max="1" min="1" id="management_of_house_building"><goal id="house_built"></goal></mission>
              <mission max="1" min="1" id="prepare_site"><goal id="site_prepared"></goal></mission>
              <mission max="1" min="1" id="lay_floors"><goal id="floors_laid"></goal></mission>
              <mission max="1" min="1" id="build_walls"><goal id="walls_built"></goal></mission>
              <mission max="1" min="1" id="build_roof"><goal id="roof_built"></goal></mission>
              <mission max="1" min="1" id="fit_windows"><goal id="windows_fitted"></goal></mission>
              <mission max="1" min="1" id="fit_doors"><goal id="doors_fitted"></goal></mission>
              <mission max="1" min="1" id="install_plumbing"><goal id="plumbing_installed"></goal></mission>
              <mission max="1" min="1" id="install_electrical_system"><goal id="electrical_system_installed"></goal></mission>
              <mission max="1" min="1" id="paint_house">
                <goal id="exterior_painted"></goal>
                <goal id="interior_painted"></goal>
              </mission>
            </scheme>
          </functional-specification>
          <normative-specification>
            <norm time-constraint="2 minutes" mission="management_of_house_building" type="obligation" role="house_owner" id="n1"></norm>
            <norm mission="prepare_site" type="obligation" role="site_prep_contractor" id="n2"></norm>
            <norm mission="lay_floors" type="obligation" role="bricklayer" id="n3"></norm>
            <norm mission="build_walls" type="obligation" role="bricklayer" id="n4"></norm>
            <norm mission="build_roof" type="obligation" role="roofer" id="n5"></norm>
            <norm mission="fit_windows" type="obligation" role="window_fitter" id="n6"></norm>
            <norm mission="fit_doors" type="obligation" role="door_fitter" id="n7"></norm>
            <norm mission="install_plumbing" type="obligation" role="plumber" id="n8"></norm>
            <norm mission="install_electrical_system" type="obligation" role="electrician" id="n9"></norm>
            <norm mission="paint_house" type="obligation" role="painter" id="n10"></norm>
          </normative-specification>
        </organisational-specification>

      // act
      val osXml = XMLConverter toXml arrange_house_building_os
      val expectedXml = arrange_expected_xml

     assert(compare._1, compare._2)

      def compare = new Comparison()(expectedXml, osXml) match {
        case NoDiff => true -> ""
        case Diff(position, msg) => false -> msg
      }
    }
}
