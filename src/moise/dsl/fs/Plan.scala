package moise.dsl.fs

// TODO: scalaxb-Importe entfernen
import moise.{PlanOperatorType, Parallel => ParallelOperator,
              Sequence => SequenceOperator, Choice => ChoiceOperator}

case class Choice(override val children: List[SchemeElement]) extends Plan(children, ChoiceOperator) {
  override def or (s: SchemeElement) = Choice(children ::: s :: Nil)
}

case class Sequence(override val children: List[SchemeElement]) extends Plan(children, SequenceOperator) {
  override def then (s: SchemeElement) = Sequence(children ::: s :: Nil)
}

case class Parallel(override val children: List[SchemeElement]) extends Plan(children, ParallelOperator) {
  override def parallel_with (s: SchemeElement) = Parallel(children ::: s :: Nil)
}

abstract case class Plan(val children: List[SchemeElement],
                          val operator: PlanOperatorType,
                          var successRate: Option[Double] = None) extends SchemeElement {
  def with_a_success_rate_of(d: Double) = {
    successRate = Some(d)
    this
  }
}
