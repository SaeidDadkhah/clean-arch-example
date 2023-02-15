package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog

import com.github.saeiddadkhah.domain.blog._
import com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api._
import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.CommonDTOFactory

object BlogDTOFactory {

  def comment: Comment => CommentDTO = { o =>
    val createdAt = CommonDTOFactory zonedDateTime o.createdAt
    CommentDTO(o.id, o.userID, o.postID, o.text, createdAt)
  }

  def paragraph: Paragraph => ParagraphDTO = {
    case o: Paragraph.Header => ParagraphDTO(ParagraphDTO.Type.Header, text = Some(o.text))
    case o: Paragraph.Image => ParagraphDTO(ParagraphDTO.Type.Image, caption = Some(o.caption), url = Some(o.url))
    case Paragraph.Splitter => ParagraphDTO(ParagraphDTO.Type.Splitter)
    case o: Paragraph.Text => ParagraphDTO(ParagraphDTO.Type.Text, text = Some(o.text))
  }

  def post: Post => PostDTO = { o =>
    val comments = o.comments map comment
    val createdAt = CommonDTOFactory zonedDateTime o.createdAt
    val paragraphs = o.paragraphs map paragraph
    val updatedAt = o.updatedAt map CommonDTOFactory.zonedDateTime
    PostDTO(o.id, o.authorID, o.title, paragraphs, o.tags, comments, o.claps, createdAt, updatedAt)
  }

}
