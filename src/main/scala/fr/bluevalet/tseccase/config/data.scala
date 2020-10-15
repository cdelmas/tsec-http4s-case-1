package fr.bluevalet.tseccase.config

import eu.timepit.refined.types.net.UserPortNumber
import eu.timepit.refined.types.string.NonEmptyString

object data {

  case class Config(
    http: HttpConfig,
    authConfig: AuthConfig
  )

  case class AuthConfig(
    key: Array[Byte]
  )
  case class HttpConfig(host: NonEmptyString, port: UserPortNumber)

}
