package moise.dsl.ss

// anders als Link hat Compatibility immer from und to


case object Compatibility {
  def from(r: Role) = EmptyCompatiblity(r)
}
case class EmptyCompatiblity(val from: Role) {
  def to(r: Role) = CompatibilityClass(from, r)
}

case object compatibility extends RelType
case class CompatibilityClass(val fromRole: Role,
                              val toRole: Role) extends RoleRel[CompatibilityClass] {
  relType = Some(compatibility)
}