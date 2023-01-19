package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.callback.blog.CommentCallback
import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.CommentService
import com.github.saeiddadkhah.domain.blog.Comment
import com.github.saeiddadkhah.domain.exception.DomainException.NotFound
import com.github.saeiddadkhah.util.FutureUtils._

import java.time.ZonedDateTime
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class CommentUseCase(commentCallback: CommentCallback, postCallback: PostCallback, userCallback: UserCallback) extends CommentService {

  override def call(request: CommentService.Request)(implicit ec: ExecutionContext): Future[Comment] = for {
    user <- userCallback get request.userID getOrThrow NotFound("User", request.userID)
    // Hereafter, we will use user.id instead of request.userID.
    post <- postCallback get request.postID getOrThrow NotFound("Post", request.postID)
    // Hereafter, we will use post.id instead of request.postID.

    comment <- commentCallback.add(user.id, post.id, request.text, ZonedDateTime.now())
  } yield comment

}
