package utils

import com.datastax.driver.core.{BoundStatement, PreparedStatement, ResultSet, Row, Session}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object CqlWrapper {
  val cassandraSession: Session = CassandraSessionProvider.setCassandraConnection

  def adduser(cqlCommand: String): ResultSet = {
    cassandraSession.execute(cqlCommand)
  }
  def extractUser(cqlCommand: String) : ResultSet = {
    cassandraSession.execute(cqlCommand)
  }
}
