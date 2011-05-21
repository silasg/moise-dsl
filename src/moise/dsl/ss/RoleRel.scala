package moise.dsl.ss

trait RelType {
  val relTypeString: String
}

trait BubbleTraitForGroup
trait BubbleTraitForGroups
object group extends BubbleTraitForGroup
object groups extends BubbleTraitForGroups

trait BubbleTraitForSubGroups
object subgroups extends BubbleTraitForSubGroups

trait RelScope
object InterGroup extends RelScope
object IntraGroup extends RelScope

case class RoleRelDirection(val biDir: Boolean)
object one {
  def direction = RoleRelDirection(false)
}
trait MoreThanOneDirection {
  def directions = RoleRelDirection(true)
}
object both extends MoreThanOneDirection
object two extends MoreThanOneDirection

trait RoleRelSignature {
  var relType: Option[RelType] = None
  def from(r: Role): RoleRelSignature
  def to(r: Role):  RoleRelSignature
  def between (groups: BubbleTraitForGroups):  RoleRelSignature
  def inside (group: BubbleTraitForGroup):  RoleRelSignature
  def in (dir: RoleRelDirection):  RoleRelSignature
  def is_valid_for (g: BubbleTraitForSubGroups):  RoleRelSignature
  def is_not_valid_for (g: BubbleTraitForSubGroups):  RoleRelSignature
}

// der ganze Aufwand hier mit RoleRelConstructor dient nur dazu, später z.B. nicht
// "Link()" für einen neuen Link aufrufen zu müssen, sonden nur "Link"
abstract class RoleRelConstructor extends RoleRelSignature {
  def construct: RoleRelSignature
  def from(r: Role) = construct from r
  def to(r: Role) = construct to r
  def between(groups: BubbleTraitForGroups) = construct between groups
  def inside(group: BubbleTraitForGroup) = construct inside group
  def in(dir: RoleRelDirection) = construct in dir
  def is_valid_for(g: BubbleTraitForSubGroups) = construct is_valid_for g
  def is_not_valid_for(g: BubbleTraitForSubGroups) = construct is_not_valid_for g
}

abstract case class RoleRel extends RoleRelSignature{
  var fromRole: Option[Role] = None
  var toRole: Option[Role] = None
  var scope: Option[RelScope] = None
  var biDir: Option[Boolean] = None
  var extendsToSubGroups: Option[Boolean] = None

  def from(r: Role) = {
    fromRole = Some(r)
    this
  }

  def to(r: Role) = {
    toRole = Some(r)
    this
  }

  def between (groups: BubbleTraitForGroups) = {
    scope = Some(InterGroup)
    this
  }

  def inside (group: BubbleTraitForGroup) = {
    scope = Some(IntraGroup)
    this
  }

  def in (dir: RoleRelDirection) = {
    biDir = Some(dir.biDir)
    this
  }

  def is_valid_for (g: BubbleTraitForSubGroups) = {
    extendsToSubGroups = Some(true)
    this
  }

  def is_not_valid_for (g: BubbleTraitForSubGroups) = {
    extendsToSubGroups = Some(false)
    this
  }
}