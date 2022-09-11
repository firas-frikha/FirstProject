package services

import Models.{AuthenticationMessage, Credentials}
import akka.http.scaladsl.model.StatusCodes
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}
import utils.Configurations
import utils.CqlWrapper.extractUser
import utils.Utils.secretkey

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object LoginService extends Configurations {
  def verifyCredentials(credentials: Credentials): Future[AuthenticationMessage] = {
    val cqlCommand = s""" SELECT * FROM configuration.credentials where email= '${credentials.email}' """
    val extracted_line = extractUser(cqlCommand)
    val return_message =
      if (extracted_line.isDefined)
      {
        val password = extracted_line.get.getString("password")
        val email = extracted_line.get.getString("email")
        if (password == credentials.password && email == credentials.email)
        {
          val claim = JwtClaim(
            expiration = Some((System.currentTimeMillis() / 1000) + token_duration),
            issuedAt = Some(System.currentTimeMillis() /1000),
            content =
              s"""{
                |"email": ${email},
                |"role": superAdmin
                |}
                |""".stripMargin
          )
          val createdToken = Jwt.encode(claim, secretkey, hash_algorithm)
          val message = "Welcome Back"
          AuthenticationMessage(true, Some(message), Some(createdToken))
        }
        else
          AuthenticationMessage(false, Some("Wrong Credentials"), None)
      }
      else {
        val message = "email does not exist"
        AuthenticationMessage(false,Some(message), None)
      }
    Future(return_message)
  }
}
