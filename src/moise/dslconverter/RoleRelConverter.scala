package moise.dslconverter

import moise.dsl.ss.{RelType, CustomLinkType, authority, communication, compatibility, acquaintance,
                     LinkClass, CompatibilityClass, RelScope, InterGroup, IntraGroup, RoleRel}
import moise.{Compatibility => CompatibilityXb, Link => LinkXb,
              Intergroup => IntergroupXb, Intragroup => IntragroupXb}

object RoleRelConverter {
  
   // laut Moise-Doku sind Scope, extendsToSubGroups und biDir optional mit
   // intraGroup, false, false als Default. Ka, warum diese Properties in den scalaxb-Klassen
   // keine Option[T] geworden sind. Daher werden die Default-Werte hier beim Konverterieren gesetzt, falls nötig
   val defaults = (IntragroupXb, false, false)

  def convertLink(l: LinkClass) = {
    val p = convertCommonRelPart(l)
    LinkXb(from = l.fromRole map { _.name },
            to = l.toRole map { _.name },
            typeValue = p._1,
            scope = p._2,
            extendssubgroups = p._3,
            bidir = p._4)
  }

  def convertCompatibility(c: CompatibilityClass) = {
    val p = convertCommonRelPart(c)
    CompatibilityXb(from = c.fromRole.name,
                    to = c.toRole.name,
                    typeValue = p._1,
                    scope = p._2,
                    extendssubgroups = p._3,
                    bidir = p._4)
  }

  private def convertCommonRelPart(r: RoleRel) = (r.relType map { toTypeString(_) },
                                                  convertScope(r.scope),
                                                  r.extendsToSubGroups.getOrElse(defaults._2),
                                                  r.biDir.getOrElse(defaults._3))

  private def convertScope(s: Option[RelScope]) = s match {
    case Some(InterGroup) => IntergroupXb
    case Some(IntraGroup) => IntragroupXb
    case None => defaults._1
  }

  def toTypeString(t: RelType) = t match {
    case `compatibility` => "compatibility"
    case `authority` => "authority"
    case `communication` => "communication"
    case `acquaintance` => "acquaintance"
    case CustomLinkType(name) => name
  }

}
