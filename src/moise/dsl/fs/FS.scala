package moise.dsl.fs

object FS {
  def with_schemes(schemes: Scheme*) = FS(schemes: _*)
}

case class FS(val schemes: Scheme*)
