package moise.dsl.ns

object NS {
  def with_norms(norms: Norm*) = NS(norms: _*)
}

case class NS(val norms: Norm*)
