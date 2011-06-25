package moise.dslconverter

import moise.dsl.os.OS
import oml.{`package`, OsType => OsXb}
import scala.xml.Elem

object XMLConverter {
  def toXml(os: OS) = {
    val elementName = "organisational-specification"
    val scope = `package`.defaultScope
    def toOsXb = OSConverter.convert(os)
    val nodeSeq = scalaxb.toXML[OsXb](toOsXb, elementName, scope)
    nodeSeq(0).asInstanceOf[Elem]
  }
}
