package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.AuthorizeService
import com.github.saeiddadkhah.domain.exception.DomainException.LoginIsRequired
import com.github.saeiddadkhah.modules.ConfigModule
import com.github.saeiddadkhah.util.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AuthorizeUseCase(sessionCallback: SessionCallback) extends AuthorizeService {

  import AuthorizeUseCase._

  override def call(request: AuthorizeService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Checking invalid key
    _ <- if (AuthUtils.sessionKey(request.session.userID, request.session.username, secret) != request.session.key) {
      Future failed LoginIsRequired
    } else {
      Future.unit
    }

    // Checking logged out session
    sessionOption <- sessionCallback get request.session.key
    _ <- sessionOption match {
      case Some(_) => Future.unit
      case None => Future failed LoginIsRequired // Show the same error to hide the auth procedure
    }
  } yield ()

}

object AuthorizeUseCase extends ConfigModule {

  private lazy val secret = config getString "application.authentication.secret"

}
