package services

import Models.{AuthenticationMessage, Credentials}
import akka.http.scaladsl.model.StatusCodes
import pdi.jwt.{Jwt, JwtAlgorithm}
import utils.CqlWrapper.extractUser

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object LoginService {
  def verifyCredentials(credentials: Credentials): Future[AuthenticationMessage] = {
    val cqlCommand = s""" SELECT * FROM configuration.credentials where email= '${credentials.email}' """
    val extracted_line = extractUser(cqlCommand).one()
    val return_message =
      if (extracted_line != null)
      {
        val password = extracted_line.getString("password")
        val email = extracted_line.getString("email")
        if (password == credentials.password && email == credentials.email)
        {
          val newToken = Jwt.encode("""{"firstname":"firas", "lastname":"firas"}""", "secretKey", JwtAlgorithm.HS256)
          val message = "Correct Credentials"
          AuthenticationMessage(true, Some(message), Some(newToken))
        }
        else
          AuthenticationMessage(false, None, None)
      }
      else {
        val message = "email does not exist"
        AuthenticationMessage(false,Some(message), None)
      }
    Future(return_message)
  }
}
