package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.WelcomeService.welcomeMessagePrinter


object WelcomeRoutes {

  def welcomeMessage: Route = path("welcome-message") {
    get {
      complete(welcomeMessagePrinter)
    }
  }

}
