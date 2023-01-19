package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.service.auth.AuthorizeService
import com.github.saeiddadkhah.domain.exception.DomainException.LoginIsRequired
import com.github.saeiddadkhah.modules.ConfigModule
import com.github.saeiddadkhah.util.AuthUtils
import com.github.saeiddadkhah.util.FutureUtils._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AuthorizeUseCase(sessionCallback: SessionCallback) extends AuthorizeService {

  import AuthorizeUseCase._

  override def call(request: AuthorizeService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Checking invalid key
    _ <- If(AuthUtils.sessionKey(request.session.userID, request.session.username, secret) != request.session.key) `throw` LoginIsRequired

    // Checking logged out session
    _ <- sessionCallback get request.session.key getOrThrow LoginIsRequired // Show the same error to hide the auth procedure
  } yield ()

}

object AuthorizeUseCase extends ConfigModule {

  private lazy val secret = config getString "application.authentication.secret"

}
