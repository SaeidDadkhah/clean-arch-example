package com.github.saeiddadkhah.contract.callback.blog

import java.time.ZonedDateTime

import com.github.saeiddadkhah.domain.blog.Comment

import scala.concurrent.Future

abstract class CommentCallback {

  def add(userID: Long, postID: Long, text: String, createdAt: ZonedDateTime): Future[Comment]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   */
  def get(id: Long): Future[Option[Comment]]

  /**
   * Add limit and offset parameters when you expect multiple objects.
   */
  def getBy(postID: Long, limit: Int, offset: Int): Future[Vector[Comment]]

  def remove(id: Long): Future[Unit]

  def update(comment: Comment): Future[Unit]

}
