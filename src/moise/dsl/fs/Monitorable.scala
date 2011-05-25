package moise.dsl.fs

trait Monitorable {
  var monitoringScheme: Option[Scheme] = None
  def monitored_by(s: Scheme): this.type = {
    monitoringScheme = Some(s)
    this
  }
}
