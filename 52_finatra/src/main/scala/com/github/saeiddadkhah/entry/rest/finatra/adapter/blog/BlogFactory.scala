package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog

import com.github.saeiddadkhah.contract.service.blog._
import com.github.saeiddadkhah.domain.blog.Paragraph
import com.github.saeiddadkhah.entry.rest.finatra.RequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api._

object BlogFactory {

  def paragraph: ParagraphDTO => Paragraph = { dto =>
    dto.`type` match {
      case ParagraphDTO.Type.Header =>
        val text = dto.text.getOrElse(throw new Exception("text field is required!"))
        Paragraph.Header(text)
      case ParagraphDTO.Type.Image =>
        val url = dto.url.getOrElse(throw new Exception("url field is required!"))
        val caption = dto.caption.getOrElse(throw new Exception("caption field is required!"))
        Paragraph.Image(url, caption)
      case ParagraphDTO.Type.Splitter => Paragraph.Splitter
      case ParagraphDTO.Type.Text =>
        val text = dto.text.getOrElse(throw new Exception("text field is required!"))
        Paragraph.Text(text)
    }
  }

  def publishPostRequest: (RequestWrapper, PublishPostRequestDTO) => PublishPostService.Request = { (rw, dto) =>
    val paragraphs = dto.paragraphs map paragraph
    PublishPostService.Request(rw.getUserID, dto.title, paragraphs, dto.tags)
  }

}
