package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.service.auth.SignInService
import com.github.saeiddadkhah.domain.auth.Session
import com.github.saeiddadkhah.domain.exception.DomainException.WrongUsernameAndPassword
import com.github.saeiddadkhah.modules.ConfigModule
import com.github.saeiddadkhah.util.AuthUtils
import com.github.saeiddadkhah.util.FutureUtils._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignInUseCase(sessionCallback: SessionCallback, userCallback: UserCallback) extends SignInService {

  import SignInUseCase._

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    user <- userCallback getBy request.username getOrThrow WrongUsernameAndPassword
    // Hereafter, we will use user.username instead of request.username.
    hashedPassword = AuthUtils hashPassword request.password
    session <- If(hashedPassword == user.password) thenDo {
      val key = AuthUtils.sessionKey(user.id, user.username, secret)
      sessionCallback.add(key, user.id, user.username)
    } elseThrow {
      WrongUsernameAndPassword // Show the same error to hide the auth procedure and existence of username
    }
  } yield session

}

object SignInUseCase extends ConfigModule {

  private lazy val secret = config getString "application.authentication.secret"

}
