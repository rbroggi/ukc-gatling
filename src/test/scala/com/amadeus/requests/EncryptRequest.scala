package com.amadeus.requests

import com.amadeus.config.Config.{auth, encryptionUrl, partition}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EncryptRequest {

  val headers = Map("Authorization" -> auth)

  val encrypt = http("Encrypt")
    .post(encryptionUrl)
    .queryParam("partitionId", partition)
    .headers(headers)
    .body(ElFileBody("bodies/encryptBody.json")).asJson
    .check(status.is(200),
      jsonPath("$.cipherTextBase64").saveAs("cipherB64")
    )

}
