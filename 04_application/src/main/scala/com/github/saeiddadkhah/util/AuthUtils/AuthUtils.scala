package com.github.saeiddadkhah.util.AuthUtils

import com.github.saeiddadkhah.domain.auth.User

object AuthUtils {

  def hashPassword(password: String): String = {
    // Do some other stuff like salting and peppering
    password.hashCode.toString
  }

  def sessionKey(user: User): String = {
    s"key_for_${user.username}"
  }

}
