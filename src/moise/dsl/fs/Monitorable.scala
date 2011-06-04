package moise.dsl.fs

trait Monitorable {
  var monitoringScheme: Option[MonitoringScheme] = None
  def monitored_by(s: MonitoringScheme): this.type = {
    monitoringScheme = Some(s)
    this
  }
}
