package moise.dsltests.helper

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import moise.dsl.helper._

class TimeTermTest {
  @Before
  def setUp = {}

  @After
  def tearDown = {}

  @Test
  def can_use_milliseconds = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.milliseconds

    // assert
    assertEquals(1, is)
    assertEquals(is, ts.millisecond)
  }


  @Test
  def can_use_seconds = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.seconds

    // assert
    assertEquals(1 * 1000, is)
    assertEquals(is, ts.second)
  }

  @Test
  def can_use_minutes = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.minutes

    // assert
    assertEquals(1 * 1000 * 60, is)
    assertEquals(is, ts.minute)
  }

  @Test
  def can_use_hours = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.hours

    // assert
    assertEquals(1 * 1000 * 60 * 60, is)
    assertEquals(is, ts.hour)
  }

  @Test
  def can_use_days = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.days

    // assert
    assertEquals(1 * 1000 * 60 * 60 * 24, is)
    assertEquals(is, ts.day)
  }

  @Test
  def can_use_years = {
    // arrange
    var ts = TimeSpan(1)

    // act
    var is = ts.years

    // assert
    assertEquals(1 * 1000 * 60 * 60 * 24 * 365, is)
    assertEquals(is, ts.year)
  }

  @Test
  def can_add_timespans = {
    // arrange
    var a = TimeSpan(1)
    var b = TimeSpan(1000)

    // act
    var c = a and b
    var d = a + b

    // assert
    assertEquals(TimeSpan(1001), c)
    assertEquals(TimeSpan(1001), d)
  }

}
