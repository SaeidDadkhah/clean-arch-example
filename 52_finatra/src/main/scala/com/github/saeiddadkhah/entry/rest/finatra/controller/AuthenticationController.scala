package com.github.saeiddadkhah.entry.rest.finatra.controller

import com.github.saeiddadkhah.contract.service.auth._
import com.github.saeiddadkhah.entry.rest.finatra.RequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.AuthFactory
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api._
import com.google.inject.Inject
import com.twitter.finagle.http.Cookie
import com.twitter.finatra.http.Controller
import com.twitter.util.Duration

import scala.concurrent.ExecutionContext.Implicits._

class AuthenticationController @Inject()(signOutService: SignOutService) extends Controller {

  prefix("/api/v1") {

    delete("/users/:user_id/sessions/current", "Sign Out") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignOutRequestDTO]
      signOutService call AuthFactory.signOuRequest(requestWrapper, requestDTO) map { _ =>
        response.noContent
          .cookie(new Cookie("key", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
          .cookie(new Cookie("userID", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
          .cookie(new Cookie("username", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
      }
    }

  }

}
