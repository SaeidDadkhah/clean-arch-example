package com.github.saeiddadkhah.contract.service.blog

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.blog.Paragraph
import com.github.saeiddadkhah.domain.blog.Post

abstract class UpdatePostService extends Service[UpdatePostService.Request, Post]

object UpdatePostService {

  case class Request(postID: Long, title: String, paragraphs: Vector[Paragraph], tags: Vector[String])

}
