package com.amadeus.scenarios

import io.gatling.core.Predef._
import com.amadeus.config.Config.payloadSize
import com.amadeus.requests.{HmacSignRequest, HmacVerifyRequest}

import java.util.Base64

object HmacSignVerifyScenario {
  val b64Payload = Base64.getEncoder.encodeToString(Array.fill(payloadSize)((scala.util.Random.nextInt(256) - 128).toByte))
  val feeder = Iterator.continually(Map(
    "plainTextB64" -> b64Payload,
  ))

  val scen = scenario("HMAC Sign Verify")
    .exec(
      forever{
        feed(feeder).exec(HmacSignRequest.sign).exec(HmacVerifyRequest.verify)
      }
    )
}
