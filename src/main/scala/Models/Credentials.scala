package Models

case class Credentials(
                        email: String,
                        password: String,
                        firstname: Option[String],
                        lastname: Option[String]
                      )
