package utils
import com.datastax.driver.core._

object CassandraSessionProvider {

  def setCassandraConnection: Session = {
    val cluster: Cluster = new Cluster.Builder().addContactPoint("0.0.0.0").withPort(9042).build()
    val session: Session = cluster.connect()
    session
  }

}
