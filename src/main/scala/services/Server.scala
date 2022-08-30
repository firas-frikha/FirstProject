package services

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import routes._
import services.Server.system.dispatcher
import akka.http.scaladsl.server.Directives._
import com.datastax.driver.core.{Cluster, Row, Session}

import scala.util.{Failure, Success}

object Server extends App {

  implicit lazy val system: ActorSystem = ActorSystem("My-first-Project")

  val host = "0.0.0.0"
  val port = 8080

  val routes = WelcomeRoutes.welcomeMessage ~ LoginRoutes.login ~ UserRoutes.addNewUserRoute ~ MusicRoutes.addNewTitleRoute
  val binding = Http().newServerAt(host, port).bind(routes)

  binding.onComplete {
    case Success(_)     => {
      println(s"Server listening at $host:$port")
    }
    case Failure(error) => println(s"Failed: ${error.getMessage}")
  }

}
