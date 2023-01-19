package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.service.auth.SignInService
import com.github.saeiddadkhah.domain.auth.Session
import com.github.saeiddadkhah.domain.exception.DomainException.WrongUsernameAndPassword
import com.github.saeiddadkhah.modules.ConfigModule
import com.github.saeiddadkhah.util.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignInUseCase(sessionCallback: SessionCallback, userCallback: UserCallback) extends SignInService {

  import SignInUseCase._

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback getBy request.username
    user <- userOption match {
      case Some(user) => Future successful user
      case None => Future failed WrongUsernameAndPassword
    } // Hereafter, we will use user.username instead of request.username.
    hashedPassword = AuthUtils hashPassword request.password
    session <- if (hashedPassword == user.password) {
      val key = AuthUtils.sessionKey(user.id, user.username, secret)
      sessionCallback.add(key, user.id, user.username)
    } else {
      Future failed WrongUsernameAndPassword // Show the same error to hide the auth procedure and existence of username
    }
  } yield session

}

object SignInUseCase extends ConfigModule {

  private lazy val secret = config getString "application.authentication.secret"

}
