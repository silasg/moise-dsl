package moise.dslconverter

import moise.dsl.fs.FS
import oml.{FsType => FsXb}

object FSConverter {
  def convert(fs: FS) = FsXb(properties = None,
                             scheme = fs.schemes map { SchemeConverter.convert(_) })
}
