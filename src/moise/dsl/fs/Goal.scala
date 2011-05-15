package moise.dsl.fs

case class AgentCount(val count: BigInt) {
  def agents = this
  def agent = agents
}

object Goal {
  def named(s: String) = {
    new Goal(s)
  }
}


case class Goal(val name: String) extends SchemeElement
