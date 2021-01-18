package com.amadeus.requests

import com.amadeus.config.Config.{auth, hmacSignUrl, partition, signUrl}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EcdsaSignRequest {

  val headers = Map("Authorization" -> s"Basic ${auth}")

  val sign = http("ECDSA sign")
    .post(signUrl)
    .queryParam("partitionId", partition)
    .headers(headers)
    .body(ElFileBody("bodies/ecdsaSignBody.json")).asJson
    .check(status.is(200),
      jsonPath("$.signature").saveAs("signatureB64")
    )

}
