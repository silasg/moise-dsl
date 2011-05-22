package moise.dsl.ss

// Beschreibung siehe Link
case object Compatibility extends RoleRelConstructor[CompatibilityClass] {
  def construct = CompatibilityClass()
}

case object compatibility extends RelType
case class CompatibilityClass extends RoleRel[CompatibilityClass] {
  relType = Some(compatibility)
}