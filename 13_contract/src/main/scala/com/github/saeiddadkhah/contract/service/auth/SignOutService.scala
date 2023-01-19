package com.github.saeiddadkhah.contract.service.auth

import com.github.saeiddadkhah.contract.service.Service

abstract class SignOutService extends Service[SignOutService.Request, Unit]

object SignOutService {

  case class Request(key: String)

}
