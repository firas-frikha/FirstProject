package services

import Models.Music
import Models.Music.parseEntity
import akka.actor.{Actor, ActorLogging}
import utils.CqlWrapper._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MusicService {

  def listAllMusicTitles: List[Music] = {
    val cqlQuery = cassandraSession.execute("select * from music_keyspace.music_playlist")
    List(parseEntity(cqlQuery.one()))
  }

  def extractTitleById(id: Int): Music = {
    val cqlQuery = cassandraSession.execute(s"select * from music_keyspace.music_playlist where id = $id")
    parseEntity(cqlQuery.one())
  }
  case class AddNewTitle(music: Music)
  object ListAllTitles
  case class GetTitleById(id: Int)

  class MusicActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case AddNewTitle(music) =>
        addNewMusic(music)
        sender() ! "Title added successfully ! "
      case ListAllTitles =>
        sender() ! listAllMusicTitles
      case GetTitleById(id) =>
        sender() ! extractTitleById(id)
    }
  }
}
