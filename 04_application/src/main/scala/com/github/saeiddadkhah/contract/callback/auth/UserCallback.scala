package com.github.saeiddadkhah.contract.callback.auth

import java.time.ZonedDateTime

import com.github.saeiddadkhah.domain.auth.User

import scala.concurrent.Future

abstract class UserCallback {

  def add(
           username: String,
           password: String,
           name: String,
           eMail: String,
           bio: Option[String],
           photoURL: Option[String],
           followers: Long,
           followings: Long,
           signUpAt: ZonedDateTime
         ): Future[User]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   **/
  def get(id: Long): Future[Option[User]]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   **/
  def getBy(username: String): Future[Option[User]]

  def remove(id: Long): Future[Unit]

  def update(user: User): Future[Unit]

}
