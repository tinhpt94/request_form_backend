package identity.generator.fauxflake

import com.github.rholder.fauxflake.api.MachineIdProvider

class RequestFormMachineIdProvider extends MachineIdProvider {
  override def getMachineId: Long = 1234L
}
