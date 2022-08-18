package utils

import Models._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

trait CommonMarshaller extends SprayJsonSupport
  with DefaultJsonProtocol{
  implicit val credentialFormat = jsonFormat4(Credentials.apply)
  implicit val userFormat = jsonFormat4(User.apply)
}
