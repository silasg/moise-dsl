package moise.dsl.fs

trait PlanOperator
case object Parallel extends PlanOperator
case object Choice extends PlanOperator
case object Sequence extends PlanOperator


case class Choice(override val children: List[SchemeElement]) extends Plan(children, Choice) {
  override def or (s: SchemeElement) = Choice(children ::: s :: Nil)
}

case class Sequence(override val children: List[SchemeElement]) extends Plan(children, Sequence) {
  override def then (s: SchemeElement) = Sequence(children ::: s :: Nil)
}

case class Parallel(override val children: List[SchemeElement]) extends Plan(children, Parallel) {
  override def parallel_with (s: SchemeElement) = Parallel(children ::: s :: Nil)
}

abstract case class Plan(val children: List[SchemeElement],
                          val operator: PlanOperator,
                          var successRate: Option[Double] = None) extends SchemeElement {
  def with_a_success_rate_of(d: Double) = {
    successRate = Some(d)
    this
  }
}
