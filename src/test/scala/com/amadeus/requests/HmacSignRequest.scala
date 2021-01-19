package com.amadeus.requests

import com.amadeus.config.Config.{auth, encryptionUrl, hmacSignUrl, partition}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HmacSignRequest {

  val headers = Map("Authorization" -> auth)

  val sign = http("Hmac")
    .post(hmacSignUrl)
    .queryParam("partitionId", partition)
    .headers(headers)
    .body(ElFileBody("bodies/hmacBody.json")).asJson
    .check(status.is(200),
      jsonPath("$.mac").saveAs("hmacB64")
    )

}
