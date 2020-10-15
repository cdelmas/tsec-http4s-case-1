package fr.bluevalet.tseccase.http.auth

object domain {
  sealed abstract class Identity {
    def id: String
  }

  final case class Service(id: String) extends Identity
}
