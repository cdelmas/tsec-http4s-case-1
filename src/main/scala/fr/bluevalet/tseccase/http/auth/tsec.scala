package fr.bluevalet.tseccase.http.auth

import cats.Monad
import cats.data.OptionT
import cats.effect.Sync
import cats.implicits._
import fr.bluevalet.tseccase.http.auth.domain._
import tsec.authentication._
import tsec.authorization._
import tsec.mac.jca.HMACSHA384

import scala.concurrent.duration._

object TSec {

  def authenticator[F[_]: Sync](
    key: Array[Byte]
  ): JWTAuthenticator[F, String, Identity, HMACSHA384] = {
    val signingKey = HMACSHA384.buildKey(key)(HMACSHA384.idKeygenMac)
    JWTAuthenticator.unbacked.inBearerToken(
      10.minute,
      None,
      (id: String) => OptionT.fromOption(Service(id).some),
      signingKey
    )
  }

  def alwaysAuthorize[F[_]: Monad]: Authorization[F, Identity, AugmentedJWT[HMACSHA384, String]] =
    (toAuth: SecuredRequest[F, Identity, AugmentedJWT[HMACSHA384, String]]) => OptionT.fromOption(toAuth.some)
}
