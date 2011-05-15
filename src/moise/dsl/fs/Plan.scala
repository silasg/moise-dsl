package moise.dsl.fs

case class Choice(goals: List[SchemeElement]) extends Plan(goals) {
  override def or (s: SchemeElement) = Choice(goals ::: s :: Nil)
}
case class Sequence(goals: List[SchemeElement]) extends Plan(goals) {
  override def then (s: SchemeElement) = Sequence(goals ::: s :: Nil)
}
case class Parallel(goals: List[SchemeElement]) extends Plan(goals) {
  override def parallel_with (s: SchemeElement) = Parallel(goals ::: s :: Nil)
}

abstract class Plan(goals: List[SchemeElement]) extends SchemeElement {
  var successRate: Option[Double] = None
  def with_a_success_rate_of(d: Double) = {
    successRate = Some(d)
    this
  }
}
