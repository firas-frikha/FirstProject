package Models
import com.datastax.driver.core.Row
import com.datastax.driver.core.utils.UUIDs

case class Music(
                id: Int,
                name: String,
                Author: String,
                Album: String,
                ){
  def parseEntity(row: Row):Music ={
    Music(
      row.getInt(id),
      row.getString(name),
      row.getString(Author),
      row.getString(Album)
    )
  }
}
