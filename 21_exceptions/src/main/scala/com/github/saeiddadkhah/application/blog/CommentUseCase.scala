package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.callback.blog.CommentCallback
import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.CommentService
import com.github.saeiddadkhah.domain.blog.Comment
import com.github.saeiddadkhah.domain.exception.DomainException.NotFound

import java.time.ZonedDateTime
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class CommentUseCase(commentCallback: CommentCallback, postCallback: PostCallback, userCallback: UserCallback) extends CommentService {

  override def call(request: CommentService.Request)(implicit ec: ExecutionContext): Future[Comment] = for {
    userOption <- userCallback get request.userID
    user <- userOption match {
      case Some(user) => Future successful user
      case None => Future failed NotFound("User", request.userID)
    } // Hereafter, we will use user.id instead of request.userID.
    postOption <- postCallback get request.postID
    post <- postOption match {
      case Some(post) => Future successful post
      case None => Future failed NotFound("Post", request.postID)
    } // Hereafter, we will use post.id instead of request.postID.

    comment <- commentCallback.add(user.id, post.id, request.text, ZonedDateTime.now())
  } yield comment

}
