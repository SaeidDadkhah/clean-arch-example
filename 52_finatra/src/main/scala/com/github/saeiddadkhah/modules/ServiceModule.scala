package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.application
import com.github.saeiddadkhah.contract.service
import com.twitter.inject.TwitterModule

object ServiceModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[service.auth.AuthorizeService]) to classOf[application.auth.AuthorizeUseCase]
    bind(classOf[service.auth.SignInService]) to classOf[application.auth.SignInUseCase]
    bind(classOf[service.auth.SignOutService]) to classOf[application.auth.SignOutUseCase]
    bind(classOf[service.auth.SignUpService]) to classOf[application.auth.SignUpUseCase]
    // Blog
    bind(classOf[service.blog.ClapService]) to classOf[application.blog.ClapUseCase]
    bind(classOf[service.blog.CommentService]) to classOf[application.blog.CommentUseCase]
    bind(classOf[service.blog.PublishPostService]) to classOf[application.blog.PublishPostUseCase]

  }

}
