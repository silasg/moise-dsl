package moise.dslconverter

import moise.dsl.ss.{RelType, authority, communication, compatibility, 
                     LinkClass, CompatibilityClass, RelScope, InterGroup, IntraGroup}
import moise.{Compatibility => CompatibilityXb, Link => LinkXb,
              Intergroup => IntergroupXb, Intragroup => IntragroupXb}

object RoleRelConverter {
  
   // laut Moise-Doku sind Scope, extendsToSubGroups und biDir optional mit
   // intraGroup, false, false als Default. Ka, warum diese Properties in den scalaxb-Klassen
   // keine Option[T] geworden sind. Daher werden die Default-Werte hier beim Konverterieren gesetzt, falls nötig


  def convertLink(l: LinkClass) = LinkXb(from = l.fromRole map { _.name },
                                          to = l.toRole map { _.name },
                                          typeValue = l.relType map { toTypeString(_) },
                                          scope = convertScope(l.scope),
                                          extendssubgroups = l.extendsToSubGroups.getOrElse(false),
                                          bidir = l.biDir.getOrElse(false))

  def convertCompatibility(c: CompatibilityClass) = CompatibilityXb(from = c.fromRole.name,
                                                      to = c.toRole.name,
                                                      typeValue = c.relType map { toTypeString(_) },
                                                      scope = convertScope(c.scope),
                                                      extendssubgroups = c.extendsToSubGroups.getOrElse(false),
                                                      bidir = c.biDir.getOrElse(false))

  private def convertScope(s: Option[RelScope]) = s match {
    case Some(InterGroup) => IntergroupXb
    case Some(IntraGroup) => IntragroupXb
    case None => IntragroupXb
  }

  def toTypeString(t: RelType) = t match {
    case `compatibility` => "compatibility"
    case `authority` => "authority"
    case `communication` => "communication"
  }

}
