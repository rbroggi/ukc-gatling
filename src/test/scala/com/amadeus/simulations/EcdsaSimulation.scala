package com.amadeus.simulations

import com.amadeus.config.Config._
import com.amadeus.scenarios.EcdsaSignVerifyScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class EcdsaSimulation extends Simulation {
  private val createUserExec = EcdsaSignVerifyScenario.scen
    .inject(rampUsers(1500) during (60 seconds))
    .throttle(reachRps(5000) in (60 seconds),
      holdFor(60 seconds))
  setUp(createUserExec).maxDuration(duration minutes)
}
