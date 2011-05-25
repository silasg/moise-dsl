package moise.dsl.fs

trait Monitorable[T] {
  var monitoringScheme: Option[Scheme] = None
  def monitored_by(s: Scheme) = {
    monitoringScheme = Some(s)
    this.asInstanceOf[T]
  }
}
