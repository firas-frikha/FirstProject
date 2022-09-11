package routes

import Models.User
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server._
import utils.CommonMarshaller
import akka.http.scaladsl.server.Directives._
import services.UserService.addNewUser
import spray.json
import utils.Utils.verifyToken
object UserRoutes extends CommonMarshaller{

  def addNewUserRoute: Route = pathPrefix("user"){
    (path("add-user") & post) {
      entity(as[User]) {
        entered_user =>
          complete(addNewUser(entered_user))
      }
    }
  }

}
