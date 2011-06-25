package moise.dsl.ss

object SS {
  def with_roles (r: Role*) = SS() with_roles (r: _*)
  def with_linktypes (l: CustomLinkType*) = SS() with_linktypes (l: _*)
  def with_group(g: Group) = SS() with_group g
}

case class SS() {
  var roles: List[Role] = List()
  var linktypes: List[CustomLinkType] = List()
  var group: Option[Group] = None

  def with_roles (r: Role*) = {
    roles = r.toList
    this
  }

  def with_linktypes (l: CustomLinkType*) = {
    linktypes = l.toList
    this
  }
  
  def with_group(g: Group) = {
    group = Some(g)
    this
  }
}
