package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.application
import com.github.saeiddadkhah.contract.service

object ServiceModule {

  object Auth {

    import application.auth._
    import service.auth._

    val signInService: SignInService = SignInUseCase
    val signUpService: SignUpService = SignUpUseCase

  }

  object Blog {

    import application.blog._
    import service.blog._

    val publishPostService: PublishPostService = PublishPostUseCase

  }

}
