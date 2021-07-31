package com.github.saeiddadkhah.contract.service.blog

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.blog.Clap

abstract class ClapService extends Service[ClapService.Request, Clap]

object ClapService {

  case class Request(userID: Long, postID: Long)

}
