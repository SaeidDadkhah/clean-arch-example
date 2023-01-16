package com.github.saeiddadkhah

import com.github.saeiddadkhah.contract.service.auth.SignInService
import com.github.saeiddadkhah.modules.CallbackModule
import com.github.saeiddadkhah.modules.ServiceModule
import com.google.inject.Guice

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends App {

  // Init Injector
  val injector = Guice.createInjector(CallbackModule, ServiceModule)

  // Application
  val signInService = injector getInstance classOf[SignInService]
  signInService call SignInService.Request("username", "password")

}
