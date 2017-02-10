package identity.generator

trait IdGeneratorProvider {
  def nextId: Long
}
