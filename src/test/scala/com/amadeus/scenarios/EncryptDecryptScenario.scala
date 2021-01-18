package com.amadeus.scenarios

import com.amadeus.config.Config.payloadSize
import com.amadeus.requests.{DecryptRequest, EncryptRequest}
import io.gatling.core.Predef.{feed, forever, scenario}

import java.util.Base64

object EncryptDecryptScenario {
  val b64Payload = Base64.getEncoder.encodeToString(Array.fill(payloadSize)((scala.util.Random.nextInt(256) - 128).toByte))
  val feeder = Iterator.continually(Map(
    "plainTextB64" -> b64Payload,
  ))

  val scen = scenario("Encrypt/Decrypt")
    .exec(
      forever{
        feed(feeder).exec(EncryptRequest.encrypt).exec(DecryptRequest.decrypt)
      }
    )
}
