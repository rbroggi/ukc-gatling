package com.amadeus.requests

import com.amadeus.config.Config.{auth, hmacVerifyUrl, partition}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HmacVerifyRequest {

  val headers = Map("Authorization" -> auth)

  val verify = http("Hmac Verify")
    .post(hmacVerifyUrl)
    .queryParam("partitionId", partition)
    .headers(headers)
    .body(ElFileBody("bodies/hmacVerifyBody.json")).asJson
    .check(status.is(200)
    )

}
