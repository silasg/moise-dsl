
package moise.dslconverter

import moise.dsl.ss.{Role, GroupRole}
import moise.{RoleDefType => RoleDefXb, Role => RoleXb, Extends => ExtendsXb}

object RoleConverter {

  def convertGroupRoleToRoleXb(r: GroupRole) = RoleXb(id = r.role map { _.name },
                                                      min = r.min,
                                                      max = r.max)

  def convertRoleToRoleDefXb(r: Role) = RoleDefXb(properties = None,
                                                  extendsValue = r.extendedRoles map { e => ExtendsXb(Some(e.name)) } ,
                                                  id = r.name)
}
