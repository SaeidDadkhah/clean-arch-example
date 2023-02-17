package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.application
import com.github.saeiddadkhah.contract.service
import com.github.saeiddadkhah.modules.CallbackModule.Auth._
import com.github.saeiddadkhah.modules.CallbackModule.Blog._

object ServiceModule {

  object Auth {

    import application.auth._
    import service.auth._

    val authorizeService: AuthorizeService = new AuthorizeUseCase(sessionCallback)
    val signInService: SignInService = new SignInUseCase(sessionCallback, userCallback)
    val signOutService: SignOutService = new SignOutUseCase(sessionCallback)
    val signUpService: SignUpService = new SignUpUseCase(userCallback)

  }

  object Blog {

    import application.blog._
    import service.blog._

    val getPostService: GetPostService = new GetPostUseCase(postCallback)
    val clapService: ClapService = new ClapUseCase(clapCallback, getPostService, postCallback, userCallback)
    val commentService: CommentService = new CommentUseCase(commentCallback, getPostService, userCallback)
    val publishPostService: PublishPostService = new PublishPostUseCase(postCallback, userCallback)
    val updatePostService: UpdatePostService = new UpdatePostUseCase(getPostService, postCallback)

  }

}
