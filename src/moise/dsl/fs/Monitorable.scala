package moise.dsl.fs

trait Monitorable {
  var monitoringScheme: Option[Scheme] = None
  def monitored_by(s: Scheme) = {
    monitoringScheme = Some(s)
    this
  }
}
