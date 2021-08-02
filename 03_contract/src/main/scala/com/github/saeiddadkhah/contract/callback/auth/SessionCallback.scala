package com.github.saeiddadkhah.contract.callback.auth

import com.github.saeiddadkhah.domain.auth.Session

import scala.concurrent.Future

abstract class SessionCallback {

  def add(key: String, userID: Long, username: String): Future[Session]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   * */
  def get(key: String): Future[Option[Session]]

  def remove(key: String): Future[Unit]

}
