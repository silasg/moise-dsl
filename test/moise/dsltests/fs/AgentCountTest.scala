package moise.dsltests.fs

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.fs.AgentCount
import moise.dsl.helper.Implicits._


class AgentCountTest {

    @Before
    def setUp() = {}

    @After
    def tearDown() {}

    @Test
    def can_convert_implicit_plural = {
      // arrange
      val expected = AgentCount(2)

      // act
      val agents = 2.agents

      // assert
      assertEquals(expected, agents)
    }


    @Test
    def can_convert_implicit_singular = {
      // arrange
      val expected = AgentCount(1)

      // act
      val agent = 1.agents

      // assert
      assertEquals(expected, agent)
    }

}
