package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.service.auth.SignInService
import com.github.saeiddadkhah.domain.auth.Session
import com.github.saeiddadkhah.modules.CallbackModule.Auth.sessionCallback
import com.github.saeiddadkhah.modules.CallbackModule.Auth.userCallback
import com.github.saeiddadkhah.util.AuthUtils.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignInUseCase(sessionCallback: SessionCallback, userCallback: UserCallback) extends SignInService {

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback getBy request.username
    user <- userOption match {
      case Some(user) => Future successful user
      case None => Future failed new Exception(s"Username or password is not correct!")
    } // Hereafter, we will use user.username instead of request.username.
    hashedPassword = AuthUtils hashPassword request.password
    session <- if(hashedPassword == user.password) {
      val key  = AuthUtils sessionKey user
      sessionCallback.add(key, user.id, user.username)
    } else {
      Future failed new Exception(s"Username or password is not correct!")
    }
  } yield session

}

// Singleton Use Case
object SignInUseCase extends SignInUseCase(sessionCallback, userCallback)
