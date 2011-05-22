package moise.dslconverter

import moise.dsl.ss.{Cardinality, CardinalityType, Role}
import moise.{Cardinality => CardinalityXb,
              RoleValue => CTypeRoleXb, Group => CTypeGroupXb}

object CardinalityConverter {
  def convertCardinality(c: Cardinality) = CardinalityXb(min = c.min,
                                                          max = c.max,
                                                          objectValue = convertType(c.cardType),
                                                          id = c.cardType.name)

  private def convertType(t: CardinalityType) = {
    if (t.isInstanceOf[Role]) CTypeRoleXb
    else CTypeGroupXb // sonst gilt: t.isInstanceOf[Group]
  }
}