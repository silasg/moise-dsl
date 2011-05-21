package moise.dsl.ss

case class Number(i: Int) {
  def times = this
}


case class SubGroup(val group: Group) {
  var min: Option[BigInt] = None
  var max: Option[BigInt] = None

  def at_least(n: Number) = {
    min = Some(n.i)
    this
  }

  def at_most(n: Number) = {
    max = Some(n.i)
    this
  }

  def and_at_most(n: Number) = at_most(n)

}
