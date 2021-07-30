package com.github.saeiddadkhah.contract.callback.blog

import java.time.ZonedDateTime

import com.github.saeiddadkhah.domain.blog.Comment
import com.github.saeiddadkhah.domain.blog.Paragraph
import com.github.saeiddadkhah.domain.blog.Post

import scala.concurrent.Future

abstract class PostCallback {

  def add(
           authorID: Long,
           title: String,
           paragraphs: Vector[Paragraph],
           tags: Vector[String],
           comments: Vector[Comment],
           claps: Long,
           createdAt: ZonedDateTime,
           updatedAt: Option[ZonedDateTime]
         ): Future[Post]

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   **/
  def get(id: Long): Future[Option[Post]]

  /**
   * Add limit and offset parameters when you expect multiple objects.
   **/
  def getBy(userID: Long, limit: Int, offset: Int): Future[Vector[Post]]

  def remove(id: Long): Future[Unit]

  def update(user: Post): Future[Unit]

}
