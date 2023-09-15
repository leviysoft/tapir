package sttp.tapir.server.vertx

import io.vertx.ext.web.{RoutingContext}

import sttp.tapir.server.vertx.Helpers.RichResponse

/**
  * Common error handler implementation for all Vertx interpreter classes.
  * 
  * Ends the response of the current routing context safely.
  * 
  * @param rc 
  *   the routing context where the response shall be ended
  * @param ex
  *   exception that occurred during the interpreter call
  */
trait VertxErrorHandler {
  def handleError(rc: RoutingContext, ex: Throwable): Unit = {
    if (rc.response().bytesWritten() > 0) rc.response().safeEndWait()
    rc.fail(ex)
  }
}
