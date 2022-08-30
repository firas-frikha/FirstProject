package services

import Models.Music
import utils.CqlWrapper.addNewMusic
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MusicService {

  def addNewTitle(music: Music): Future[String]= {
    addNewMusic(music)
    Future("Title added Successfully")
  }
}
