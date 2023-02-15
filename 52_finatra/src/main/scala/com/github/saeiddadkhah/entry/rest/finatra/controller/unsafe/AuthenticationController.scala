package com.github.saeiddadkhah.entry.rest.finatra.controller.unsafe

import com.github.saeiddadkhah.contract.service.auth._
import com.github.saeiddadkhah.entry.rest.finatra.UnauthenticatedRequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.AuthDTOFactory
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.AuthFactory
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api._
import com.google.inject.Inject
import com.twitter.finagle.http.Cookie
import com.twitter.finatra.http.Controller

import scala.concurrent.ExecutionContext.Implicits._

class AuthenticationController @Inject()(signInService: SignInService, signUpService: SignUpService) extends Controller {

  prefix("/api/v1") {

    post("/users", "Sign Up") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignUpRequestDTO]
      signUpService call AuthFactory.signUpRequest(requestWrapper, requestDTO) map AuthDTOFactory.user map { serviceResponseDTO =>
        response created serviceResponseDTO
      }
    }

    post("/users/:username/sessions", "Sign In") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignInRequestDTO]
      signInService call AuthFactory.signInRequest(requestWrapper, requestDTO) map { serviceResponse =>
        response.created()
          .cookie(new Cookie("key", serviceResponse.key, path = Some("/")))
          .cookie(new Cookie("userID", serviceResponse.userID.toString, path = Some("/")))
          .cookie(new Cookie("username", serviceResponse.username, path = Some("/")))
      }
    }

  }

}
