package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api

case class ParagraphDTO
(
  `type`: ParagraphDTO.Type,
  caption: Option[String] = None, // Image
  text: Option[String] = None, // Header, Text
  url: Option[String] = None, // Image
)

object ParagraphDTO {

  type Type = String

  object Type {

    val Header: Type = "Header"
    val Image: Type = "Image"
    val Splitter: Type = "Splitter"
    val Text: Type = "Text"

  }

}
