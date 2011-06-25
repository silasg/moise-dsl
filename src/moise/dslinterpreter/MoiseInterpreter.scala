package moise.dslinterpreter

import scala.tools.nsc.{Interpreter, Settings}
import scala.io.Source

import moise.dsl.os.OS
import moise.dslconverter.XMLConverter
 
object MoiseInterpreter {
  def interpret (dslFile: String): String = {
    var settings = new Settings
    settings.usejavacp.value = true
    var res = Array[AnyRef](null)
    val i = new Interpreter(settings)
    i.bind("result", "Array[AnyRef]", res)
    i.interpret("import moise.dsl.fs._")
    i.interpret("import moise.dsl.ss._")
    i.interpret("import moise.dsl.ns._")
    i.interpret("import moise.dsl.os._")
    i.interpret("import moise.dsl.helper.Implicits._")
    Source.fromFile(dslFile).getLines.foreach(i interpret _)
    i.interpret("result(0) = res0")
    val os = res(0).asInstanceOf[OS]
    val osXml = XMLConverter toXml os
    osXml.toString
  }

  /**
   * For testing/debugging only since I don't want an unit test with reference to an external file
   */
  def main (args: Array[String]) = println(interpret(args(0)))
}