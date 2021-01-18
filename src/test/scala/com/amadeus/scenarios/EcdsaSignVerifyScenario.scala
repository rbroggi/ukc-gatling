package com.amadeus.scenarios

import com.amadeus.config.Config.payloadSize
import com.amadeus.requests.{EcdsaSignRequest}
import io.gatling.core.Predef._

import java.util.Base64

object EcdsaSignVerifyScenario {
  val b64Payload = Base64.getEncoder.encodeToString(Array.fill(payloadSize)((scala.util.Random.nextInt(256) - 128).toByte))
  val feeder = Iterator.continually(Map(
    "plainTextB64" -> b64Payload,
  ))

  val scen = scenario("ECDSA Sign")
    .exec(
      forever{
        feed(feeder).exec(EcdsaSignRequest.sign)
      }
    )
}
