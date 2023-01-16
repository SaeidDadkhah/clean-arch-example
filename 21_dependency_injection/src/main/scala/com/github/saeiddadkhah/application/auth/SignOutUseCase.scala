package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.SignOutService
import com.google.inject.Inject
import com.google.inject.Singleton

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

@Singleton
class SignOutUseCase @Inject()(sessionCallback: SessionCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    sessionCallback remove request.key
  }

}
