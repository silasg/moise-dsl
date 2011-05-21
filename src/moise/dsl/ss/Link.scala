package moise.dsl.ss

trait LinkType extends RelType

object authority extends LinkType {
  val relTypeString = "authority"
}

object communication extends LinkType {
  val relTypeString = "communication"
}

// Link erbt alle von RoleRelSignature vorgegebenen Methoden, da es
// RoleRelConstructor beerbt und gibt für den Aufruf einer jeden (wir wissen ja
// nicht, welche zuerst aufgerufen wird, da jede optional ist) eine neue Instanz
// der Klasse LinkClass zurück, auf der die Methode entsprechend aufgerufen wurde
object Link extends RoleRelConstructor[LinkClass] {
  def construct = LinkClass()
}

case class LinkClass extends RoleRel[LinkClass] {
  def expresses(t: LinkType) = {
    relType = Some(t)
    this
  }
}
