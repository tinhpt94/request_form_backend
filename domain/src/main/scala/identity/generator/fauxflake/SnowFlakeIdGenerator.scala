package identity.generator.fauxflake

import com.github.rholder.fauxflake.DefaultIdGenerator
import com.github.rholder.fauxflake.provider.SystemTimeProvider
import com.github.rholder.fauxflake.provider.twitter.SnowflakeEncodingProvider
import identity.generator.IdGeneratorProvider

import scala.concurrent.duration.Duration

class SnowFlakeIdGenerator extends IdGeneratorProvider {

  private val machineIdProvider = new RequestFormMachineIdProvider

  private val systemTimeProvider = new SystemTimeProvider
  private val snowFlakeEncodingProvider = new SnowflakeEncodingProvider(machineIdProvider.getMachineId)
  private val snowFlakeIdGenerator = new DefaultIdGenerator(systemTimeProvider, snowFlakeEncodingProvider)

  override def nextId: Long = snowFlakeIdGenerator.generateId(Duration("1 second").toSeconds.toInt).asLong
}
