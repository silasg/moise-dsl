package moise.dsl.ns

import moise.dsl.ss.Role
import moise.dsl.fs.Mission
import moise.dsl.helper.{TimeTerm, TimeSpan, NotNumericTimeTerm}

abstract case class NormType
case object Obligation extends NormType
case object Permission extends NormType

object Norm {
  def named (n: String) = EmptyNorm(n)
}

case class EmptyNorm(val name: String) {
  def permits(r: Role) = NormWithoutMission(name, Permission, r)
  def obligates(r: Role) = NormWithoutMission(name, Obligation, r)
}

case class NormWithoutMission(val name: String, val normType: NormType, val role: Role) {
  def participation_in(m: Mission) = Norm(name, normType, role, m)
}

case class Norm(val name: String, 
                val normType: NormType,
                val role: Role,
                val mission: Mission,
                var timeConstraint: Option[TimeTerm] = None,
                var condition: Option[String] = None) {

  def valid_for(t: TimeSpan) = {
    timeConstraint = Some(t)
    this
  }

  def valid(t: NotNumericTimeTerm) = {
    timeConstraint = Some(t)
    this
  }

  def with_the_condition_that (c: String) = {
    condition = Some(c)
    this
  }
}
