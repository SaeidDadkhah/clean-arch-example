package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.SignOutService
import com.github.saeiddadkhah.modules.CallbackModule.Auth.sessionCallback

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignOutUseCase(sessionCallback: SessionCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    sessionCallback remove request.key
  }

}

object SignOutUseCase extends SignOutUseCase(sessionCallback)
