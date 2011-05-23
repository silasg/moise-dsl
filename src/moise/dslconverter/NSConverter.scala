package moise.dslconverter

import moise.dsl.ns.NS
import moise.{NsType => NsXb}

object NSConverter {
  def convert(ns: NS) = NsXb(properties = None,
                             norm = ns.norms map { NormConverter.convertNorm(_) })
}
