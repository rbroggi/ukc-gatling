package com.amadeus.config

object Config {

  // Input parameters for stress-test
  val targetIPPort = getProperty("ipPort", "localhost")
  val key = getProperty("key", "myKey")
  val partition = getProperty("partition", "myPartition")
  // base64("user@partition:password")
  val basicAuth = getProperty("basicAuth", "dXNlckBwYXJ0aXRpb246cGFzc3dvcmQ=")
  val bearerAuth = getProperty("bearerAuth", "invalid")
  val auth = if (bearerAuth.equals("invalid")) s"Basic ${basicAuth}" else s"Bearer ${bearerAuth}";
  val users = Integer.getInteger("users", 100).toInt
  val duration = Integer.getInteger("duration", 1).toInt
  val payloadSize = Integer.getInteger("payloadSize", 200).toInt

  val finalUserCount = Integer.getInteger("finalUserCount", 500).toInt
  val rampupDuration = Integer.getInteger("rampupDuration", 60).toInt

  // Derived properties
  val baseUrl = s"https://${targetIPPort}"
  val encryptionUrl = s"${baseUrl}/api/v1/keys/${key}/encrypt"
  val hmacSignUrl = s"${baseUrl}/api/v1/keys/${key}/mac"
  val signUrl = s"${baseUrl}/api/v1/keys/${key}/sign"
  val hmacVerifyUrl = s"${baseUrl}/api/v1/keys/${key}/macVerify"
  val decryptionUrl = s"${baseUrl}/api/v1/keys/${key}/decrypt"

  def getProperty(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

}