package moise.dsl.fs

case class AgentCount(val count: BigInt) {
  def agents = this
  def agent = agents
}