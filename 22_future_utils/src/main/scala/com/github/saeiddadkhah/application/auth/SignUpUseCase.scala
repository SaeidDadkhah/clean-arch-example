package com.github.saeiddadkhah.application.auth

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.contract.service.auth.SignUpService
import com.github.saeiddadkhah.domain.auth.User
import com.github.saeiddadkhah.domain.exception.DomainException.Exists
import com.github.saeiddadkhah.util.AuthUtils
import com.github.saeiddadkhah.util.FutureUtils._

import java.time.ZonedDateTime
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignUpUseCase(userCallback: UserCallback) extends SignUpService {

  override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[User] = {
    // Persist hashed password
    val hashedPassword = AuthUtils hashPassword request.password

    for {
      // Check username availability
      _ <- userCallback getBy request.name throwIfExists Exists("Username", request.username)

      // Create user
      user <- userCallback.add(request.username, hashedPassword, request.name, request.eMail, None, None, 0, 0, ZonedDateTime.now())
    } yield user
  }

}
