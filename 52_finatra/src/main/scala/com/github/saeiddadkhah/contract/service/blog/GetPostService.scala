package com.github.saeiddadkhah.contract.service.blog

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.blog.Post

abstract class GetPostService extends Service[GetPostService.Request, Post]

object GetPostService {

  case class Request(postID: Long)

}
