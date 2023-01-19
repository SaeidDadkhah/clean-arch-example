package com.github.saeiddadkhah

import com.github.saeiddadkhah.contract.service.auth.SignInService
import com.github.saeiddadkhah.modules.ServiceModule.Auth.signInService

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends App {

  signInService call SignInService.Request("username", "password")

}
