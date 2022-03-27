package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.contract.callback
import com.github.saeiddadkhah.repository.inmem.{repository => inmem}

object CallbackModule {

  object Auth {

    import callback.auth._
    import inmem.auth._

    val sessionCallback: SessionCallback = new SessionRepository()
    val userCallback: UserCallback = new UserRepository()

  }

  object Blog {

    import callback.blog._
    import inmem.blog._

    val clapCallback: ClapCallback = new ClapRepository()
    val commentCallback: CommentCallback = new CommentRepository()
    val postCallback: PostCallback = new PostRepository()

  }

}
