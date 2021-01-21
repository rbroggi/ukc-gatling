package com.amadeus.simulations

import com.amadeus.config.Config._
import com.amadeus.scenarios.EncryptDecryptScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class SymmetricEncryptionDecryptionReqsPerSecSimulation extends Simulation {
  private val createUserExec = EncryptDecryptScenario.scen
    .inject(
      constantUsersPerSec(finalUserCount).during(duration seconds),
    )

  setUp(createUserExec).maxDuration(duration minutes)
}
