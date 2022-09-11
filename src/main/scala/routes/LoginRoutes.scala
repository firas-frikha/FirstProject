package routes

import Models.Credentials
import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.model.headers.{HttpCookie, RawHeader}

import scala.util.{Failure, Success}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.LoginService.verifyCredentials
import utils.CommonMarshaller

object LoginRoutes extends CommonMarshaller {

  def login: Route = path("login") {
    post {
      entity(as[Credentials]) {
        creds =>
          onComplete(verifyCredentials(creds)) {
            case Success(authentificationMessage) => authentificationMessage.authenticated match {
              case true => {
                respondWithHeader(RawHeader("Access-Token",authentificationMessage.token.get)) {
                  complete(StatusCodes.OK)
                }
              }
              case false => {
                println(authentificationMessage.message.get)
                complete(StatusCodes.Unauthorized)
              }
            }
            case Failure(e) => complete((StatusCodes.InternalServerError, s"An error occurred: ${e.getMessage}"))
          }
      }
    }
  }

}
