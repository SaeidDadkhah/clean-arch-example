package com.github.saeiddadkhah.application.auth

import java.time.ZonedDateTime

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.service.auth.SignUpService
import com.github.saeiddadkhah.domain.auth.User
import com.github.saeiddadkhah.modules.CallbackModule.Auth.userCallback
import com.github.saeiddadkhah.util.AuthUtils.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignUpUseCase(userCallback: UserCallback) extends SignUpService {

  override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[User] = {
    // Persist hashed password
    val hashedPassword = AuthUtils hashPassword request.password

    for {
      // Check username availability
      previousUserOption <- userCallback getBy request.name
      _ <- previousUserOption match {
        case None => Future.unit
        case Some(previousUser) => Future failed new Exception(s"This username is taken: ${previousUser.username}")
      }

      // Create user
      user <- userCallback.add(request.username, hashedPassword, request.name, request.eMail, None, None, 0, 0, ZonedDateTime.now())
    } yield user
  }

}

// Singleton Use Case
object SignUpUseCase extends SignUpUseCase(userCallback)
