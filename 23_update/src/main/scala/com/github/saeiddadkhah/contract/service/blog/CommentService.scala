package com.github.saeiddadkhah.contract.service.blog

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.blog.Comment

abstract class CommentService extends Service[CommentService.Request, Comment]

object CommentService {

  case class Request(userID: Long, postID: Long, text: String)

}
