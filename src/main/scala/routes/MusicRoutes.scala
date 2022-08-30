package routes

import Models.Music
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import services.MusicService.addNewTitle
import utils.CommonMarshaller

object MusicRoutes extends CommonMarshaller{

  def addNewTitleRoute: Route ={
    (path("add-title") & post){
      entity(as[Music]){ musicTitle =>
        complete(addNewTitle(musicTitle))
      }
    }
  }

}
