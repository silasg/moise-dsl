package moise.dslconverter

import moise.dsl.ss.{SS, Role, CustomLinkType}
import oml.{SsType => SsXb, Roledefinitions => RoleDefsXb,
            Linktypes => LinkTypesXb, Linktype => LinkTypeXb}

object SSConverter {
  def convert(ss: SS) = SsXb(properties = None,
                              roledefinitions = convertRoleDefinitions(ss.roles),
                              linktypes = convertLinkTypes(ss.linktypes),
                              groupspecification = ss.group map { GroupConverter.convert(_) })

  private def convertRoleDefinitions(roles: List[Role]) =
    if (roles.length == 0) None
    else Some(RoleDefsXb(roles map { RoleConverter.convertRoleToRoleDefXb(_)}: _*))

  private def convertLinkTypes(linktypes: List[CustomLinkType]) =
    if (linktypes.length == 0) None
    else Some(LinkTypesXb(linktypes map { lt => LinkTypeXb(lt.name) }: _*))
}
