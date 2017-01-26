package identity

import identity.generator.IdGeneratorProvider
import identity.generator.fauxflake.SnowFlakeIdGenerator

class Id(idGeneratorProvider: IdGeneratorProvider) {
  def randomID: Long = idGeneratorProvider.nextId
}

object Id {
  private lazy val defaultProvider = new SnowFlakeIdGenerator

  def apply(provider: String = ""): Id = provider.toLowerCase match {
    case "snowflake" => new Id(new SnowFlakeIdGenerator) // Twitter SnowFlake's Algorithm
    case _           => new Id(defaultProvider)
  }
}