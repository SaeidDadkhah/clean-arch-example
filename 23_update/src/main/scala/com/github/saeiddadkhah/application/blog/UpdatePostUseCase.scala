package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.GetPostService
import com.github.saeiddadkhah.contract.service.blog.UpdatePostService
import com.github.saeiddadkhah.domain.blog.Post

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UpdatePostUseCase(getPostService: GetPostService, postCallback: PostCallback) extends UpdatePostService {

  override def call(request: UpdatePostService.Request)(implicit ec: ExecutionContext): Future[Post] = for {
    post <- getPostService call GetPostService.Request(request.postID)
    newPost = post.edit(request.title, request.paragraphs)
    _ <- postCallback update newPost
  } yield post

}
