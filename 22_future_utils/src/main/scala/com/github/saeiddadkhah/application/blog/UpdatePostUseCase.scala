package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.UpdatePostService
import com.github.saeiddadkhah.domain.blog.Post
import com.github.saeiddadkhah.domain.exception.DomainException.NotFound
import com.github.saeiddadkhah.util.FutureUtils._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UpdatePostUseCase(postCallback: PostCallback) extends UpdatePostService {

  override def call(request: UpdatePostService.Request)(implicit ec: ExecutionContext): Future[Post] = for {
    post <- postCallback get request.postID getOrThrow NotFound("Post", request.postID)
    newPost = post.edit(request.title, request.paragraphs)
    _ <- postCallback update newPost
  } yield post

}
