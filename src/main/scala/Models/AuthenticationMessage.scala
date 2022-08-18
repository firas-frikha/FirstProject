package Models

case class AuthenticationMessage(authenticated: Boolean, message: Option[String], token: Option[String])
