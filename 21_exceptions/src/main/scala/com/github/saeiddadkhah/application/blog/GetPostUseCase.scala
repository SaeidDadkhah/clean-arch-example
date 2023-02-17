package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.GetPostService
import com.github.saeiddadkhah.domain.blog.Post
import com.github.saeiddadkhah.domain.exception.DomainException.NotFound

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class GetPostUseCase(postCallback: PostCallback) extends GetPostService {

  override def call(request: GetPostService.Request)(implicit ec: ExecutionContext): Future[Post] = for {
    postOption <- postCallback get request.postID
    post <- postOption match {
      case Some(post) => Future successful post
      case None => Future failed NotFound("Post", request.postID)
    }
  } yield post

}
