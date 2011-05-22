package moise.dsl.ss

trait RelType

trait BubbleTraitForGroup
trait BubbleTraitForGroups
object group extends BubbleTraitForGroup
object groups extends BubbleTraitForGroups

trait BubbleTraitForSubGroups
object subgroups extends BubbleTraitForSubGroups

trait RelScope
case object InterGroup extends RelScope
case object IntraGroup extends RelScope

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
  def between (groups: BubbleTraitForGroups): RoleRelSignature
  def inside (group: BubbleTraitForGroup): RoleRelSignature
  def in (dir: RoleRelDirection): RoleRelSignature
  def is_valid_for (g: BubbleTraitForSubGroups): RoleRelSignature
  def is_not_valid_for (g: BubbleTraitForSubGroups): RoleRelSignature
}

abstract case class RoleRel[R<:RoleRelSignature](var scope: Option[RelScope] = None,
                                                 var biDir: Option[Boolean] = None,
                                                 var extendsToSubGroups: Option[Boolean] = None) extends RoleRelSignature{

  def between (groups: BubbleTraitForGroups) = {
    scope = Some(InterGroup)
    this.asInstanceOf[R]
  }

  def inside (group: BubbleTraitForGroup) = {
    scope = Some(IntraGroup)
    this.asInstanceOf[R]
  }

  def in (dir: RoleRelDirection) = {
    biDir = Some(dir.biDir)
    this.asInstanceOf[R]
  }

  def is_valid_for (g: BubbleTraitForSubGroups) = {
    extendsToSubGroups = Some(true)
    this.asInstanceOf[R]
  }

  def is_not_valid_for (g: BubbleTraitForSubGroups) = {
    extendsToSubGroups = Some(false)
    this.asInstanceOf[R]
  }
}