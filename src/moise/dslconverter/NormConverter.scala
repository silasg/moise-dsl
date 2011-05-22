package moise.dslconverter

import moise.dsl.ns.{Norm, NormType, Obligation, Permission}
import moise.{Norm => NormXb}

object NormConverter {

  def convertNorm(n: Norm) = NormXb(id = n.name,
                                    condition = n.condition,
                                    role = n.role.name,
                                    typeValue = convertNormType(n.normType),
                                    mission = n.mission.name,
                                    timeconstraint = n.timeConstraint map { TimeTermConverter.convertToAttributeString(_)})
  
  private def convertNormType(t: NormType) = t match {
    case Obligation => "obligation"
    case Permission => "permission"
  }

}
