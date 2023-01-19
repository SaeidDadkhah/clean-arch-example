package com.github.saeiddadkhah.contract.service.auth

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.auth.Session

abstract class AuthorizeService extends Service[AuthorizeService.Request, Unit]

object AuthorizeService {

  case class Request(session: Session)

}
