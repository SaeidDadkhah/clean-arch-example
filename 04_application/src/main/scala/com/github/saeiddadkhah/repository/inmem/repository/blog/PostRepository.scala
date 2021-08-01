package com.github.saeiddadkhah.repository.inmem.repository.blog

import java.time.ZonedDateTime

import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.domain.blog.Comment
import com.github.saeiddadkhah.domain.blog.Paragraph
import com.github.saeiddadkhah.domain.blog.Post
import com.github.saeiddadkhah.modules.SimpleInMemoryModule

import scala.concurrent.Future

class PostRepository extends PostCallback with SimpleInMemoryModule[Post] {

  override def add(
                    authorID: Long,
                    title: String,
                    paragraphs: Vector[Paragraph],
                    tags: Vector[String],
                    comments: Vector[Comment],
                    claps: Long,
                    createdAt: ZonedDateTime,
                    updatedAt: Option[ZonedDateTime]
                  ): Future[Post] = {
    val post = Post(generateID(), authorID, title, paragraphs, tags, comments, claps, createdAt, updatedAt)
    addToMemory(post).map(_ => post)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   * */
  override def get(id: Long): Future[Option[Post]] = Future {
    data.find(_.id == id)
  }

  /**
   * Add limit and offset parameters when you expect multiple objects.
   * */
  override def getBy(userID: Long, limit: Int, offset: Int): Future[Vector[Post]] = Future {
    pagination(data.filter(_.authorID == userID), limit, offset)
  }

  override def remove(id: Long): Future[Unit] = {
    removeFromMemory(_.id == id)
  }

  override def update(post: Post): Future[Unit] = {
    updateInMemory(post, _.id == post.id)
  }

}

// Singleton Repository
object PostRepository extends PostRepository
