package com.amadeus.simulations

import com.amadeus.config.Config._
import com.amadeus.scenarios.{EncryptDecryptScenario, EncryptSymmetricScenario}
import io.gatling.core.Predef._

import scala.concurrent.duration._

class SymmetricEncryptionDecryptionSimulation extends Simulation {
  private val createUserExec = EncryptDecryptScenario.scen
    .inject(rampUsers(1500) during (60 seconds))
    .throttle(reachRps(5000) in (60 seconds),
      holdFor(60 seconds))
  setUp(createUserExec).maxDuration(duration minutes)
}
