package fr.bluevalet.tseccase.modules

import cats.effect._
import fr.bluevalet.tseccase.http.auth.{AuthN, AuthZ}
import fr.bluevalet.tseccase.http.routes._
import io.chrisdavenport.log4cats.Logger
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.middleware._

import scala.concurrent.duration._

object HttpApi {
  def make[F[_]: Concurrent: Timer: Logger](
    authN: AuthN[F],
    authZ: AuthZ[F]
  ): F[HttpApi[F]] =
    Sync[F].delay(
      new HttpApi[F](
        authN,
        authZ
      )
    )
}

final class HttpApi[F[_]: Concurrent: Timer: Logger] private (
  authN: AuthN[F],
  authZ: AuthZ[F]
) {

  private val closedRoutes = new SampleSecuredRoutes[F](authN, authZ).routes
  private val routes: HttpRoutes[F] = Router(
    "/v1" -> closedRoutes
  )
  private val middleware: HttpRoutes[F] => HttpRoutes[F] = {
    { http: HttpRoutes[F] =>
      AutoSlash(http)
    } andThen { http: HttpRoutes[F] =>
      CORS(http, CORS.DefaultCORSConfig)
    } andThen { http: HttpRoutes[F] =>
      Timeout(60.seconds)(http) // TODO: should be less, by far!
    }
  }
  private val loggers: HttpApp[F] => HttpApp[F] = {
    { http: HttpApp[F] =>
      RequestLogger.httpApp(true, true)(http)
    } andThen { http: HttpApp[F] =>
      ResponseLogger.httpApp(true, true)(http)
    }
  }
  val httpApp: HttpApp[F] = loggers(middleware(routes).orNotFound)
}
