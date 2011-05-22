package moise.dsl.ss

trait LinkType extends RelType
case object authority extends LinkType
case object communication extends LinkType

// Link erbt alle von RoleRelSignature vorgegebenen Methoden, da es
// RoleRelConstructor beerbt und gibt für den Aufruf einer jeden (wir wissen ja
// nicht, welche zuerst aufgerufen wird, da jede optional ist) eine neue Instanz
// der Klasse LinkClass zurück, auf der die Methode entsprechend aufgerufen wurde


// der ganze Aufwand hier mit diesem Konstruktor-Objekt dient nur dazu, später z.B. nicht
// "Link()" für einen neuen Link aufrufen zu müssen, sonden nur "Link"
object Link extends RoleRelSignature {
  def from(r: Role) = LinkClass() from r
  def to(r: Role) = LinkClass() to r
  def between(groups: BubbleTraitForGroups) = LinkClass() between groups
  def inside(group: BubbleTraitForGroup) = LinkClass() inside group
  def in(dir: RoleRelDirection) = LinkClass() in dir
  def is_valid_for(g: BubbleTraitForSubGroups) = LinkClass() is_valid_for g
  def is_not_valid_for(g: BubbleTraitForSubGroups) = LinkClass() is_not_valid_for g
}

case class LinkClass(var fromRole: Option[Role] = None,
                     var toRole: Option[Role] = None) extends RoleRel[LinkClass] {
  def from(r: Role) = {
    fromRole = Some(r)
    this
  }

  def to(r: Role) = {
    toRole = Some(r)
    this
  }

  def expresses(t: LinkType) = {
    relType = Some(t)
    this
  }
}
