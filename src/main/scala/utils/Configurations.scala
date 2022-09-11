package utils

import com.typesafe.config.ConfigFactory
import pdi.jwt.JwtAlgorithm

class Configurations {
  val token_duration: Int = ConfigFactory.load().getInt("token_duration_in_seconds")
  val secretkey: String = ConfigFactory.load().getString("secret_key")
  val hash_algorithm = JwtAlgorithm.HS256
}
