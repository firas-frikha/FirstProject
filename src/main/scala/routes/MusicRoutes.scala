package routes

import Models.Music
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import services.MusicService.{AddNewTitle, GetTitleById, ListAllTitles, MusicActor}
import utils.CommonMarshaller
import utils.Utils.verifyToken

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object MusicRoutes extends CommonMarshaller{

  implicit val system: ActorSystem = ActorSystem("MusicActor")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val musicActor: ActorRef = system.actorOf(Props[MusicActor], "MusicRoutes")

  implicit val timeout = Timeout(2 seconds)
  def addNewTitleRoute: Route = {
    pathPrefix("music") {
      (path("add-title") & post) {
        entity(as[Music]) { musicTitle =>
          val addedTitle = (musicActor ? AddNewTitle(musicTitle)).mapTo[String]
          complete(addedTitle)
        }
      } ~
        (get & path("all-title")) {
          optionalHeaderValueByName("Authorization") {
            jwttoken =>
              if (verifyToken(jwttoken.getOrElse(""))) {
                val allTitles = (musicActor ? ListAllTitles).mapTo[List[Music]]
                complete(allTitles)
              }

              else
                complete(StatusCodes.Unauthorized)

          }
        } ~
        (get & path("by-id" / IntNumber)) {
          id => {
            (optionalHeaderValueByName("Authorization")) {
              token =>
                if (verifyToken(token.getOrElse(""))) {
                  val extracted_title = (musicActor ? GetTitleById(id)).mapTo[Music]
                  complete(extracted_title)
                }
                else
                  complete(StatusCodes.Unauthorized)
            }

          }
        }
    }
  }
}
