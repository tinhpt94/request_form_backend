package model.user

import scala.util.Try

sealed abstract class Role(val value: String)

object Role {
  def fromString(str: String): Try[Role] = Try {
    str match {
      case "ADMIN" => ADMIN
      case _       => throw new IllegalArgumentException(s"$str is not defined as Role")
    }
  }

  case object ADMIN extends Role("ADMIN")
}
