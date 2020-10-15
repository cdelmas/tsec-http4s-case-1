package fr.bluevalet.tseccase.http.routes

import cats._
import cats.effect.Sync
import cats.implicits._
import fr.bluevalet.tseccase.http.auth.{AuthN, AuthZ}
import io.chrisdavenport.log4cats.Logger
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import tsec.authentication.TSecAuthService
import tsec.authentication._

final class SampleSecuredRoutes[F[_]: Sync: Defer: Monad: Logger](
  authN: AuthN[F],
  authZ: AuthZ[F]
) extends Http4sDsl[F] {

  private[routes] val prefixPath = "/specimen-secured"
  private val httpRoutes =
    TSecAuthService.withAuthorization(authZ) {
      case req @ GET -> Root asAuthed u =>
        for {
          _    <- Logger[F].info(s"Doing stuff on ${req} with ${u}")
          resp <- NoContent()
        } yield resp
    }
  val routes: HttpRoutes[F] = Router(
    prefixPath -> authN.liftService(httpRoutes)
  )
}
