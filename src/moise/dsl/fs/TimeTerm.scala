package moise.dsl.fs

trait TimeTerm {
  def toAttributeString: String
}

case class TimeSpan(duration: Int) extends TimeTerm {
  implicit def intToTimeSpan(i: Int) = TimeSpan(i)

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

  def toAttributeString = {
    if (milliseconds % 1.year == 0) milliseconds / 1.year + " years"
    else if (milliseconds % 1.day == 0) milliseconds / 1.day + " days"
    else if (milliseconds % 1.hour == 0) milliseconds / 1.hour + " hours"
    else if (milliseconds % 1.minute == 0) milliseconds / 1.minute + " minutes"
    else if (milliseconds % 1.second == 0) milliseconds / 1.second + " seconds"
    else milliseconds + " milliseconds"
  }
}

object Now extends TimeTerm {
  def toAttributeString = "now"
}

object Never extends TimeTerm {
  def toAttributeString = "never"
}

