package moise.dsl.helper

import moise.dsl.helper.Implicits.intToTimeSpan

trait TimeTerm

case class TimeSpan(duration: Int) extends TimeTerm {
  def milliseconds = duration
  def seconds = milliseconds * 1000
  def minutes = seconds * 60
  def hours = minutes * 60
  def days = hours * 24
  def years = days * 365

  def millisecond = milliseconds
  def second = seconds
  def minute = minutes
  def hour = hours
  def day = days
  def year = years

  def and (ts: TimeSpan) = TimeSpan(milliseconds + ts.milliseconds)
  def +(ts: TimeSpan) = and(ts)
}

trait NotNumericTimeTerm extends TimeTerm
case object now extends NotNumericTimeTerm 
case object never extends NotNumericTimeTerm

