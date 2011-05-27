package moise.dsltests.converter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.helper.{now, never}
import moise.dsl.helper.Implicits.intToTimeSpan
import moise.dslconverter.TimeTermConverter

class TimeTermConverterTest {
    @Before
    def setUp() = {}

    @After
    def tearDown() {}

    @Test
    def can_convert_now = {
      // arrange
      val expected = "now"

      // act
      val is = TimeTermConverter.convertToAttributeString(`now`)

      // assert
      assertEquals(expected, is)
    }


    @Test
    def can_convert_never = {
      // arrange
      val expected = "never"

      // act
      val is = TimeTermConverter.convertToAttributeString(`never`)

      // assert
      assertEquals(expected, is)
    }


    @Test
    def can_convert_milliseconds = {
      // arrange
      val expected = "1 milliseconds"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.millisecond)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_seconds = {
      // arrange
      val expected = "1 seconds"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.second)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_minutes = {
      // arrange
      val expected = "1 minutes"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.minute)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_hours = {
      // arrange
      val expected = "1 hours"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.hour)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_days = {
      // arrange
      val expected = "1 days"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.day)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_convert_years = {
      // arrange
      val expected = "1 years"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.year)

      // assert
      assertEquals(expected, is)
    }

    @Test
    def can_combine_different_units = {
      // arrange
      val expected = "90 seconds"

      // act
      val is = TimeTermConverter.convertToAttributeString(1.minute and 30.seconds)

      // assert
      assertEquals(expected, is)
    }
}
