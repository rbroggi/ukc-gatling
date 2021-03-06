package com.amadeus.simulations

import com.amadeus.config.Config._
import com.amadeus.scenarios.EncryptDecryptScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class SymmetricEncryptionDecryptionUserRampupSimulation extends Simulation {
  private val createUserExec = EncryptDecryptScenario.scen
    .inject(atOnceUsers(1),
      rampUsers(finalUserCount) during (rampupDuration seconds))
  setUp(createUserExec).maxDuration(duration minutes)
}
