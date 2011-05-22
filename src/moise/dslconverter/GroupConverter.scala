package moise.dslconverter

import moise.dsl.ss.{Group, GroupRole, LinkClass, CompatibilityClass, SubGroup, Cardinality}
import moise.{GroupSpecificationType => GroupXb, Formationconstraints => ConstraintsXb,
              Roles => RolesXb, Links => LinksXb, Subgroups => SubGroupsXb}

import scala.collection.mutable.ListBuffer

object GroupConverter {
 
  def convert(g: Group) = convertSubGroup(g, None, None)

  private def convertSubGroup(g: Group, min: Option[BigInt], max: Option[BigInt]) =
    GroupXb(roles = convertRoles(g.roles),
      links = convertLinks(g.links),
      subgroups = convertSubGroups(g.subGroups),
      formationconstraints = convertConstraints(g.cardinalities, g.compatibilities),
      id = g.name,
      min = min,
      max = max,
      monitoringscheme = g.monitoringScheme.map({ s => s.name }).getOrElse(Some(""))) // ka, warum scalaxb immer ein Monitoring Scheme will => im zweifelsfall "" übergeben

   private def convertRoles(r: ListBuffer[GroupRole]) = {
    if (r.length == 0) None
    else Some(RolesXb(r map { RoleConverter.convertGroupRoleToRoleXb(_) }: _*))
  }

  private def convertLinks(l: List[LinkClass]) =
    if (l.length == 0) None
    else Some(LinksXb(l map { RoleRelConverter.convertLink(_) }: _*))


  // immer eine Sequenz mit null oder einem SubGroupsXb-Element zurückgeben
  // das xsd sagt zwar, es seinen 0...* erlaubt, aber das erscheint unlogisch
  private def convertSubGroups(s: ListBuffer[SubGroup]): Seq[SubGroupsXb] =
    if (s.length == 0 ) Seq()
    else Seq(SubGroupsXb(includegroupspecification = Seq(),
                groupspecification = s map { s => convertSubGroup(s.group, s.min, s.max)}))


  private def convertConstraints(cardinalities: ListBuffer[Cardinality],
                                  compatibilities: List[CompatibilityClass]) =
    if (cardinalities.length == 0 || compatibilities.length == 0 ) None
    else Some(
       ConstraintsXb(cardinality = cardinalities map { CardinalityConverter.convertCardinality(_) },
                compatibility = compatibilities map { RoleRelConverter.convertCompatibility(_) })
      )

}
