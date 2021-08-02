package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.AuthorizeService
import com.github.saeiddadkhah.modules.CallbackModule.Auth.sessionCallback
import com.github.saeiddadkhah.util.AuthUtils.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AuthorizeUseCase(sessionCallback: SessionCallback) extends AuthorizeService {

  override def call(request: AuthorizeService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Checking invalid key
    _ <- if (AuthUtils.sessionKey(request.session.userID, request.session.username) != request.session.key) {
      Future failed new Exception("User has not logged in!")
    } else {
      Future.unit
    }

    // Checking logged out session
    sessionOption <- sessionCallback get request.session.key
    _ <- sessionOption match {
      case Some(_) => Future.unit
      case None => Future failed new Exception("User has not logged in!") // Show the same error to hide the auth procedure
    }
  } yield ()

}

object AuthorizeUseCase extends AuthorizeUseCase(sessionCallback)
