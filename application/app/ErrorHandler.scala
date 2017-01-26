import javax.inject.Singleton

import play.api.http.HttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {
  override def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Future.successful(
      Status(statusCode)
    )
  }

  override def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      InternalServerError
    )
  }
}
