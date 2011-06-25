package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.ss.{Cardinality, CardinalityType, Role, GroupRole, Group, SubGroup}
import oml.{Cardinality => CardinalityXb,
            RoleValue => CTypeRoleXb, Group => CTypeGroupXb}
import moise.dslconverter._
import moise.dsl.helper.Implicits._

class CardinalityConverterTest {
    @Before
    def setUp() = {}

    @After
    def tearDown() {}

    @Test
    def can_convert_role_cardinality = {
      // arrange
      val r = Role named "test"
      val gr = GroupRole(Some(r)) at_least 1.times and_at_most 2.times
      val expected = CardinalityXb(min = Some(1),
                                    max = Some(2),
                                    objectValue = CTypeRoleXb,
                                    id = "test")

      // act
      val is = CardinalityConverter.convertCardinality(gr)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_subgroup_cardinality = {
      // arrange
      val g = Group named "test"
      val sg = SubGroup(g) at_least 1.times and_at_most 2.times
      val expected = CardinalityXb(min = Some(1),
                                    max = Some(2),
                                    objectValue = CTypeGroupXb,
                                    id = "test")

      // act
      val is = CardinalityConverter.convertCardinality(sg)

      // assert
      assertEquals(expected, is)
    }
}
