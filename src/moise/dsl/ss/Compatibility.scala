package moise.dsl.ss

// Beschreibung siehe Link
object Compatibility extends RoleRelConstructor[CompatibilityClass] {
  def construct = CompatibilityClass()
}

case class CompatibilityClass extends RoleRel[CompatibilityClass] {
  relType = Some(compatibility)

  object compatibility extends RelType {
    val relTypeString = "compatibility"
  }

}