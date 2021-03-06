package moise.dsl.ss

import scala.collection.mutable.ListBuffer

object Role {
  def named(n: String) = new Role(n)
}

case class Role(val name: String) extends CardinalityType {
  val extendedRoles = ListBuffer[Role]() //TODO: ggf. in Constructor verschieben

  def complements(roles: Role*) = {
    roles.foreach(extendedRoles += _)
    this
  }
}