package com.amadeus.config

object Config {

  // Input parameters for stress-test
  val targetIPPort = getProperty("ipPort", "localhost")
  val key = getProperty("key", "myKey")
  val partition = getProperty("partition", "myPartition")
  // base64("user@partition:password")
  val auth = getProperty("auth", "dXNlckBwYXJ0aXRpb246cGFzc3dvcmQ=")
  val users = Integer.getInteger("users", 100).toInt
  val duration = Integer.getInteger("duration", 1).toInt
  val payloadSize = Integer.getInteger("payloadSize", 200).toInt

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