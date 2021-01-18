package com.amadeus.simulations

import com.amadeus.scenarios.EncryptSymmetricScenario
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import com.amadeus.config.Config._

import scala.concurrent.duration._

class SymmetricEncryptionSimulation extends Simulation {
  private val createUserExec = EncryptSymmetricScenario.scen
    .inject(rampUsers(1500) during (60 seconds))
    .throttle(reachRps(5000) in (60 seconds),
      holdFor(60 seconds))
  setUp(createUserExec).maxDuration(duration minutes)
}
