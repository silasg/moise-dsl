package moise.dsl.ss

case class Compatibilty extends RoleRel {
  relType = Some(compatibility)

  object compatibility extends RelType {
    val relTypeString = "compatibility"
  }

}