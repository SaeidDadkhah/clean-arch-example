package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.contract.callback
import com.github.saeiddadkhah.repository.inmem.{repository => inmem}

object CallbackModule {

  object Auth {

    import callback.auth._
    import inmem.auth._

    val sessionCallback: SessionCallback = SessionRepository
    val userCallback: UserCallback = UserRepository

  }

  object Blog {

    import callback.blog._
    import inmem.blog._

    val clapCallback: ClapCallback = ClapRepository
    val commentCallback: CommentCallback = CommentRepository
    val postCallback: PostCallback = PostRepository

  }

}
