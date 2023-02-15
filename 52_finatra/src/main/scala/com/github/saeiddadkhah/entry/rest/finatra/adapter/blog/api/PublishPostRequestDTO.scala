package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api

case class PublishPostRequestDTO(title: String, paragraphs: Vector[ParagraphDTO], tags: Vector[String])
