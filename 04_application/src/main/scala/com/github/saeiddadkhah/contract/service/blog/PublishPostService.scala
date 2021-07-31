package com.github.saeiddadkhah.contract.service.blog

import com.github.saeiddadkhah.contract.service.Service
import com.github.saeiddadkhah.domain.blog.Paragraph
import com.github.saeiddadkhah.domain.blog.Post

abstract class PublishPostService extends Service[PublishPostService.Request, Post]

object PublishPostService {

  case class Request(authorID: Long, title: String, paragraphs: Vector[Paragraph], tags: Vector[String])

}
