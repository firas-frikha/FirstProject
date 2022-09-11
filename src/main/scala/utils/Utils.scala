package utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim, JwtSprayJson}

import scala.util.{Failure, Success}

object Utils extends Configurations {

  def verifyToken(token: String): Boolean = {
    println(Jwt.isValid(token, secretkey, Seq(hash_algorithm)))
    val token_validity = Jwt.isValid(token, secretkey, Seq(hash_algorithm))
    if (token_validity){
      Jwt.decode(token,secretkey,Seq(hash_algorithm)) match {
        case Success(claim) =>
          (claim.expiration.getOrElse(0L) > System.currentTimeMillis() /1000)
        case Failure(exception) =>
          println("Could not authorize due to the following exception")
          println(exception)
          false
      }
    }
    else
      false
  }

}
