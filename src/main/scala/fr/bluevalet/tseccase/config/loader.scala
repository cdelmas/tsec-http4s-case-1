package fr.bluevalet.tseccase.config

import cats.effect.{Async, ContextShift}
import cats.implicits._
import ciris._
import ciris.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.net.UserPortNumber
import eu.timepit.refined.types.string.NonEmptyString
import fr.bluevalet.tseccase.config.data._

object load {

  def apply[F[_]: Async: ContextShift]: F[Config] =
    (httpConfig, authConfig)
      .parMapN((http, auth) => Config(http, auth))
      .load[F]

  private def authConfig: ConfigValue[AuthConfig] =
    (
      env("JWT_SECRET_KEY")
    ).map(s => AuthConfig(s.getBytes()))

  private def httpConfig: ConfigValue[HttpConfig] =
    (
      env("HOST").as[NonEmptyString].default("0.0.0.0"),
      env("PORT").as[UserPortNumber].default(8080)
    ).parMapN(HttpConfig)
}
