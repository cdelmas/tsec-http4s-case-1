package fr.bluevalet.tseccase

import cats.effect._
import cats.implicits._
import fr.bluevalet.tseccase.http.auth.io._
import fr.bluevalet.tseccase.modules._
import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import org.http4s.server.blaze.BlazeServerBuilder

object Main extends IOApp {

  implicit val logger = Slf4jLogger.getLogger[IO]

  override def run(args: List[String]): IO[ExitCode] =
    config.load[IO].flatMap { cfg =>
      Logger[IO].info(s"Loaded config $cfg") >>
        (for {
          _ <- Logger[IO].info("Loading the application")
          authN = ioAuthn(cfg.authConfig.key)
          authZ = ioAuthz
          api <- HttpApi.make[IO](authN, authZ)
          _ <- BlazeServerBuilder[IO]
            .bindHttp(
              cfg.http.port.value,
              cfg.http.host.value
            )
            .withHttpApp(api.httpApp)
            .serve
            .compile
            .drain
        } yield ExitCode.Success)
    }
}
