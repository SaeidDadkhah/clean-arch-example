package com.github.saeiddadkhah.contract.callback.auth

import com.github.saeiddadkhah.domain.auth.Session

import scala.concurrent.Future

abstract class SessionCallback {

  def add(key: String, userID: Long, username: String): Future[Session]

  def remove(key: String): Future[Unit]

}
