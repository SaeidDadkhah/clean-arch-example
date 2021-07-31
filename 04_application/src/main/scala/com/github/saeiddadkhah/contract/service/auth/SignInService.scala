package com.github.saeiddadkhah.contract.service.auth

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.auth.Session

abstract class SignInService extends Service[SignInService.Request, Session]

object SignInService {

  case class Request(username: String, password: String)

}
