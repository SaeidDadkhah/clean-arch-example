package com.github.saeiddadkhah.contract.callback.blog

import com.github.saeiddadkhah.domain.blog.Clap

import scala.concurrent.Future

abstract class ClapCallback {

  def add(userID: Long, postID: Long, count: Long): Future[Clap]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   **/
  def getBy(userID: Long, postID: Long): Future[Option[Clap]]

  /**
   * Add limit and offset parameters when you expect multiple objects.
   **/
  def getBy(postID: Long, limit: Int, offset: Int): Future[Vector[Clap]]

  def remove(id: Long): Future[Unit]

  def update(user: Clap): Future[Unit]

}
