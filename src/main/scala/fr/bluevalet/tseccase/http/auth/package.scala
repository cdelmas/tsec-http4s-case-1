package fr.bluevalet.tseccase.http

import fr.bluevalet.tseccase.http.auth.TSec._
import tsec.authentication.{AugmentedJWT, SecuredRequestHandler}
import tsec.authorization.Authorization
import tsec.mac.jca.HMACSHA384

package object auth {

  type JwtToken    = AugmentedJWT[HMACSHA384, String]
  type AuthN[F[_]] = SecuredRequestHandler[F, String, domain.Identity, JwtToken]
  type AuthZ[F[_]] = Authorization[F, domain.Identity, JwtToken]

  object io {

    import cats.effect.IO

    def ioAuthn(key: Array[Byte]): AuthN[IO] =
      SecuredRequestHandler(authenticator[IO](key))

    def ioAuthz: AuthZ[IO] = alwaysAuthorize[IO]
  }
}
