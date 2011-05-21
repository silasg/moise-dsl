package moise.dsl.ss

import scala.collection.mutable.ListBuffer

object Role {
  def named(n: String) = new Role(n)
}

class Role(val name: String) {
  val extendedRoles = ListBuffer[Role]()

  def complements(roles: Role*) = {
    roles.foreach(extendedRoles += _)
    this
  }
}