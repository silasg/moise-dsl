package moise.dsl.fs

import moise.{PlanOperatorType, Parallel => ParallelOperator,
              Sequence => SequenceOperator, Choice => ChoiceOperator}

case class Choice(override val children: List[SchemeElement]) extends Plan(children) {
  override def or (s: SchemeElement) = Choice(children ::: s :: Nil)
  val operator = ChoiceOperator
}

case class Sequence(override val children: List[SchemeElement]) extends Plan(children) {
  override def then (s: SchemeElement) = Sequence(children ::: s :: Nil)
  val operator = SequenceOperator
}

case class Parallel(override val children: List[SchemeElement]) extends Plan(children) {
  override def parallel_with (s: SchemeElement) = Parallel(children ::: s :: Nil)
  val operator = ParallelOperator
}

abstract class Plan(val children: List[SchemeElement]) extends SchemeElement {
  val operator: PlanOperatorType
  var successRate: Option[Double] = None
  def with_a_success_rate_of(d: Double) = {
    successRate = Some(d)
    this
  }
}
