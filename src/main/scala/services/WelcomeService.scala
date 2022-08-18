package services

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object WelcomeService {

  def welcomeMessagePrinter: Future[String] =  {
    Future("Hello to my first App")
  }
}
