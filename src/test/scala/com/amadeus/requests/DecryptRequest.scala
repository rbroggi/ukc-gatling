package com.amadeus.requests

import com.amadeus.config.Config.{baseUrl, auth, decryptionUrl, encryptionUrl, partition}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DecryptRequest {

  val headers = Map("Authorization" -> s"Basic ${auth}")

  val decrypt = http("Decrypt")
    .post(decryptionUrl)
    .queryParam("partitionId", partition)
    .headers(headers)
    .body(ElFileBody("bodies/decryptBody.json")).asJson
    .check(status is 200)
}
