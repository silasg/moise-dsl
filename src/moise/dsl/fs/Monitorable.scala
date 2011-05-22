package moise.dsl.fs

// TODO: ggf. muss hier noch ein Typ-Parameter hin, damit "this" die richtige Rückgabe bekommt
trait Monitorable {
  var monitoringScheme: Option[Scheme] = None
  def monitored_by(s: Scheme) = {
    monitoringScheme = Some(s)
    this
  }
}
