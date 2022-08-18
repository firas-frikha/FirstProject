package services

import Models.User
import utils.CqlWrapper._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object UserService  extends {
  def addNewUser(user: User) = {
    val cqlCommand = s"""INSERT INTO users_keyspace.user_table (email,firstname, lastname, number) VALUES('${user.email}', '${user.firstName}', '${user.lastName}', ${user.number} )"""
    adduser(cqlCommand)
    Future("User Added Successfully")
  }

}
