package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api

import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

case class PostDTO
(
  id: Long,
  authorID: Long,
  title: String,
  paragraphs: Vector[ParagraphDTO],
  tags: Vector[String],
  comments: Vector[CommentDTO],
  claps: Long,
  createdAt: ZonedDateTimeDTO,
  updatedAt: Option[ZonedDateTimeDTO]
)
