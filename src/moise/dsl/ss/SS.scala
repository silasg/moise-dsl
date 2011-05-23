package moise.dsl.ss

trait SSSignature {
  def with_roles (roles: Role*)
  def with_linktypes (linktypes: CustomLinkType*)
  def with_group(group: Group)
}

object SS extends SSSignature {
  def with_roles (r: Role*) = SS() with_roles (r: _*)
  def with_linktypes (l: CustomLinkType*) = SS() with_linktypes (l: _*)
  def with_group(g: Group) = SS() with_group g
}

case class SS() {
  var roles: List[Role] = List()
  var linktypes: List[CustomLinkType] = List()
  var group: Option[Group] = None

  def with_roles (r: Role*) = {
    roles = roles.toList
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
