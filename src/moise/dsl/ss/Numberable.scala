package moise.dsl.ss

case class Number(i: Int) {
  def times = this
}

trait Numerable[T] {
  var min: Option[BigInt] = None
  var max: Option[BigInt] = None

  def at_least(n: Number) = {
    min = Some(n.i)
    this.asInstanceOf[T]
  }

  def at_most(n: Number) = {
    max = Some(n.i)
    this.asInstanceOf[T]
  }

  def exactly(n: Number) = {
    min = Some(n.i)
    max = Some(n.i)
    this.asInstanceOf[T]
  }

  def and_at_most(n: Number) = at_most(n)
}

case class SubGroup(val group: Group) extends Numerable[SubGroup]
trait CardinalityType {
  val name: String
}
case class Cardinality(val cardType: CardinalityType) extends Numerable[Cardinality]
case class GroupRole(val role: Option[Role]) extends Numerable[GroupRole]
object any_role extends GroupRole(None)
