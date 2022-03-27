package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.SignOutService

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignOutUseCase(sessionCallback: SessionCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    sessionCallback remove request.key
  }

}
