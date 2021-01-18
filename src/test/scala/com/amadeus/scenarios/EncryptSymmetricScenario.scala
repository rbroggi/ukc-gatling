package com.amadeus.scenarios

import com.amadeus.config.Config.payloadSize
import com.amadeus.requests.{EncryptRequest}
import io.gatling.core.Predef._

import java.util.Base64

object EncryptSymmetricScenario {
  val b64Payload = Base64.getEncoder.encodeToString(Array.fill(payloadSize)((scala.util.Random.nextInt(256) - 128).toByte))
  val feeder = Iterator.continually(Map(
    "plainTextB64" -> b64Payload,
  ))

  val scen = scenario("Encrypt")
    .exec(
      forever{
        feed(feeder).exec(EncryptRequest.encrypt)
      }
    )
}
