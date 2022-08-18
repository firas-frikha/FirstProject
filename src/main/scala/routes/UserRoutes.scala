package routes

import Models.User
import akka.http.scaladsl.server.Route
import utils.CommonMarshaller
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import services.UserService.addNewUser

object UserRoutes extends CommonMarshaller{

  def addNewUserRoute: Route = path("add-user"){
    post{
      entity(as[User]) {
        entered_user =>
          complete(addNewUser(entered_user))
      }
    }
  }

}
