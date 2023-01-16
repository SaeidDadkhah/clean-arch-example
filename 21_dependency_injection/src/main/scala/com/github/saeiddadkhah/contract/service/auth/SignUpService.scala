package com.github.saeiddadkhah.contract.service.auth

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.auth.User

abstract class SignUpService extends Service[SignUpService.Request, User]

object SignUpService {

  case class Request(username: String, password: String, name: String, eMail: String)

}
