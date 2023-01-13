package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.contract.callback
import com.github.saeiddadkhah.repository.inmem.{repository => inmem}
import com.twitter.inject.TwitterModule

object CallbackModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[callback.auth.SessionCallback]) to classOf[inmem.auth.SessionRepository]
    bind(classOf[callback.auth.UserCallback]) to classOf[inmem.auth.UserRepository]
    // Blog
    bind(classOf[callback.blog.ClapCallback]) to classOf[inmem.blog.ClapRepository]
    bind(classOf[callback.blog.CommentCallback]) to classOf[inmem.blog.CommentRepository]
    bind(classOf[callback.blog.PostCallback]) to classOf[inmem.blog.PostRepository]

  }

}
