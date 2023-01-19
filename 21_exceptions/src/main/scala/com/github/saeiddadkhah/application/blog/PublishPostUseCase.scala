package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.PublishPostService
import com.github.saeiddadkhah.domain.blog.Post
import com.github.saeiddadkhah.domain.exception.DomainException.NotFound

import java.time.ZonedDateTime
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class PublishPostUseCase(postCallback: PostCallback, userCallback: UserCallback) extends PublishPostService {

  override def call(request: PublishPostService.Request)(implicit ec: ExecutionContext): Future[Post] = for {
    // Check author existence
    authorOption <- userCallback get request.authorID
    author <- authorOption match {
      case Some(author) => Future successful author
      case None => Future failed NotFound("User", request.authorID)
    } // Hereafter, we will use author.id instead of request.authorID.

    // Add post
    post <- postCallback.add(author.id, request.title, request.paragraphs, request.tags, Vector.empty, 0, ZonedDateTime.now(), None)
  } yield post

}
