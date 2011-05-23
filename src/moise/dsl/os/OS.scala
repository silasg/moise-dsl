package moise.dsl.os

import moise.dsl.fs.FS
import moise.dsl.ss.SS
import moise.dsl.ns.NS

object OSVersion { val versionString = "0.8" }

object OS {
  def with_ss(ss: SS) = OS(OSVersion.versionString, ss)
}

case class OS(val version: String, val ss: SS) {
  var fs: Option[FS] = None
  var ns: Option[NS] = None
  var name: Option[String] = None

  def with_fs(fs: FS) = {
    this.fs = Some(fs)
    this
  }

  def with_ns(ns: NS) = {
    this.ns = Some(ns)
    this
  }

  def named(s: String) = {
    name = Some(s)
    this
  }
}
