package com.github.saeiddadkhah.util.AuthUtils

object AuthUtils {

  def hashPassword(password: String): String = {
    // Do some other stuff like salting and peppering
    password.hashCode.toString
  }

  //noinspection ScalaUnusedSymbol
  def sessionKey(userID: Long, username: String): String = {
    // Do some other stuff like encrypting using a private key or signing the session
    s"key_for_$username"
  }

}
