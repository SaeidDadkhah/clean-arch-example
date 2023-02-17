package com.github.saeiddadkhah.application.blog

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.callback.blog.ClapCallback
import com.github.saeiddadkhah.contract.callback.blog.PostCallback
import com.github.saeiddadkhah.contract.service.blog.ClapService
import com.github.saeiddadkhah.contract.service.blog.GetPostService
import com.github.saeiddadkhah.domain.blog.Clap

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ClapUseCase(clapCallback: ClapCallback, getPostService: GetPostService, postCallback: PostCallback, userCallback: UserCallback) extends ClapService {

  override def call(request: ClapService.Request)(implicit ec: ExecutionContext): Future[Clap] = for {
    // Get objects
    userOption <- userCallback get request.userID
    user <- userOption match {
      case Some(user) => Future successful user
      case None => Future failed new Exception(s"User ${request.userID} not found!")
    } // Hereafter, we will use user.id instead of request.userID.
    post <- getPostService call GetPostService.Request(request.postID)
    // Hereafter, we will use post.id instead of request.postID.
    clapOption <- clapCallback.getBy(user.id, post.id)

    // Change objects
    newClapOption = clapOption map (_.clap())
    newPost = post.clap()

    // Persist changes
    newClap <- newClapOption match {
      case None => clapCallback.add(user.id, post.id, 1)
      case Some(newClap) => for {
        _ <- clapCallback update newClap
      } yield newClap
    }
    _ <- postCallback update newPost
  } yield newClap

}
