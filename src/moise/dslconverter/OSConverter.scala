package moise.dslconverter

import moise.dsl.os.OS
import oml.{OsType => OsXb}

object OSConverter {
  def convert(os: OS) = OsXb(properties = None,
                              structuralspecification = SSConverter convert os.ss,
                              functionalspecification = os.fs map { FSConverter convert _ },
                              normativespecification = os.ns map { NSConverter convert _ },
                              id = os.name,
                              osversion = os.version)
}
