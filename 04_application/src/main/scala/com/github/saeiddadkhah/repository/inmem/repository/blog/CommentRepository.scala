package com.github.saeiddadkhah.repository.inmem.repository.blog

import java.time.ZonedDateTime

import com.github.saeiddadkhah.contract.callback.blog.CommentCallback
import com.github.saeiddadkhah.domain.blog.Comment
import com.github.saeiddadkhah.modules.SimpleInMemoryModule

import scala.concurrent.Future

class CommentRepository extends CommentCallback with SimpleInMemoryModule[Comment] {

  override def add(userID: Long, postID: Long, text: String, createdAt: ZonedDateTime): Future[Comment] = {
    val comment = Comment(generateID(), userID, postID, text, createdAt)
    addToMemory(comment).map(_ => comment)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   * */
  override def get(id: Long): Future[Option[Comment]] = Future {
    data.find(_.id == id)
  }

  /**
   * Add limit and offset parameters when you expect multiple objects.
   * */
  override def getBy(postID: Long, limit: Int, offset: Int): Future[Vector[Comment]] = Future {
    pagination(data.filter(_.postID == postID), limit, offset)
  }

  override def remove(id: Long): Future[Unit] = {
    removeFromMemory(_.id == id)
  }

  override def update(comment: Comment): Future[Unit] = {
    updateInMemory(comment, _.id == comment.id)
  }

}
