package moise.dsltests.ss

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss._
import moise.dsl.helper.Implicits._

class LinkTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_create_empty_link = {
    // arrange

    // act
    val l = LinkClass()

    // assert
    assertEquals(LinkClass(), l)
    assertEquals(None, l.fromRole)
    assertEquals(None, l.toRole)
    assertEquals(None, l.scope)
    assertEquals(None, l.biDir)
    assertEquals(None, l.extendsToSubGroups)
    assertEquals(None, l.relType)
  }


  @Test
  def can_create_empty_link_with_from = {
    // arrange
    val r = Role named "r"

    // act
    val l = Link from r

    // assert
    assertEquals(Some(r), l.fromRole)
  }


  @Test
  def can_create_empty_link_with_to = {
    // arrange
    val r = Role named "r"

    // act
    val l = Link to r

    // assert
    assertEquals(Some(r), l.toRole)
  }


  @Test
  def can_create_empty_link_with_between = {
    // arrange

    // act
    val l = Link between groups

    // assert
    assertEquals(Some(InterGroup), l.scope)
  }

  @Test
  def can_create_empty_link_with_inside = {
    // arrange

    // act
    val l = Link inside group

    // assert
    assertEquals(Some(IntraGroup), l.scope)
  }

  @Test
  def can_create_empty_link_with_direction = {
    // arrange

    // act
    val l = Link in one.direction

    // assert
    assertEquals(Some(false), l.biDir)
  }

  @Test
  def can_create_empty_link_with_subgroups = {
    // arrange

    // act
    val l = Link is_valid_for subgroups

    // assert
    assertEquals(Some(true), l.extendsToSubGroups)
  }

  @Test
  def can_create_empty_link_without_subgroups = {
    // arrange

    // act
    val l = Link is_not_valid_for subgroups

    // assert
    assertEquals(Some(false), l.extendsToSubGroups)
  }


  @Test
  def can_create_authority_link = {
    // arrange

    // act
    val l = Link expresses authority

    // assert
    assertEquals(Some(authority), l.relType)
  }


  @Test
  def can_create_communication_link = {
    // arrange

    // act
    val l = Link expresses communication

    // assert
    assertEquals(Some(communication), l.relType)
  }


  @Test
  def can_create_acquaintance_link = {
    // arrange

    // act
    val l = Link expresses acquaintance

    // assert
    assertEquals(Some(acquaintance), l.relType)
  }

  @Test
  def can_create_custom_link = {
    // arrange
    val linktype = Linktype named "my linktype"

    // act
    val l = Link expresses linktype

    // assert
    assertEquals(Some(CustomLinkType("my linktype")), l.relType)
  }

}
