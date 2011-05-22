package moise.dslconverter

import moise.dsl.helper.{TimeTerm, TimeSpan, now, never}
import moise.dsl.helper.Implicits.intToTimeSpan

object TimeTermConverter {
  def convertToAttributeString(t: TimeTerm) = t match {
    case `now` => "now"     // Backticks sind nötig, weil kleingeschriebene Bezeichner in case-Klauseln immer
    case `never` => "never" // Variablen deklarieren, ich hier aber auf die case-Objects "now" und "never" prüfe
    case ts: TimeSpan => convertTimeSpanToAttributeString(ts)
  }

  private def convertTimeSpanToAttributeString(ts: TimeSpan) = {
    if (ts.milliseconds % 1.year == 0) ts.milliseconds / 1.year + " years"
    else if (ts.milliseconds % 1.day == 0) ts.milliseconds / 1.day + " days"
    else if (ts.milliseconds % 1.hour == 0) ts.milliseconds / 1.hour + " hours"
    else if (ts.milliseconds % 1.minute == 0) ts.milliseconds / 1.minute + " minutes"
    else if (ts.milliseconds % 1.second == 0) ts.milliseconds / 1.second + " seconds"
    else ts.milliseconds + " milliseconds"
  }
}
