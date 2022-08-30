package utils

import Models.Music
import com.datastax.driver.core.{BoundStatement, PreparedStatement, ResultSet, Row, Session}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object CqlWrapper {
  val cassandraSession: Session = CassandraSessionProvider.setCassandraConnection

  def adduser(cqlCommand: String): ResultSet = {
    cassandraSession.execute(cqlCommand)
  }
  def extractUser(cqlCommand: String) : Option[Row] = {
    Option(cassandraSession.execute(cqlCommand).one())
  }

  def addNewMusic(musicObject: Music) = {
    val cqlQuery: PreparedStatement = cassandraSession.prepare("INSERT INTO music_keyspace.music_playlist (id, name, author, album) VALUES ( ?, ?, ?, ?)")
    val bound_query = cqlQuery.bind(musicObject.id, musicObject.name, musicObject.Author, musicObject.Author)
    cassandraSession.execute(bound_query)
  }

  def extractMany(cqlCommand: String) = {
    Option(cassandraSession.execute(cqlCommand).one())
  }
}
