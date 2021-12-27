package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.application
import com.github.saeiddadkhah.contract.service
import com.github.saeiddadkhah.modules.CallbackModule.Auth._
import com.github.saeiddadkhah.modules.CallbackModule.Blog._

object ServiceModule {

  object Auth {

    import application.auth._
    import service.auth._

    val signInService: SignInService = new SignInUseCase(sessionCallback, userCallback)
    val signUpService: SignUpService = new SignUpUseCase(userCallback)

  }

  object Blog {

    import application.blog._
    import service.blog._

    val publishPostService: PublishPostService = new PublishPostUseCase(postCallback, userCallback)

  }

}
