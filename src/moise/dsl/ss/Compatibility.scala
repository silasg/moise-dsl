package moise.dsl.ss

// Beschreibung siehe Link
object Compatibility extends RoleRelConstructor[CompatibiltyClass] {
  def construct = CompatibiltyClass()
}

case class CompatibiltyClass extends RoleRel[CompatibiltyClass] {
  relType = Some(compatibility)

  object compatibility extends RelType {
    val relTypeString = "compatibility"
  }

}