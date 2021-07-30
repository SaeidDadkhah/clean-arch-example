package com.github.saeiddadkhah.domain.blog

import java.time.ZonedDateTime

case class Post(
                 id: Long,
                 authorID: Long,
                 title: String,
                 paragraphs: Vector[Paragraph],
                 tags: Vector[String],
                 comments: Vector[Comment],
                 claps: Long,
                 createdAt: ZonedDateTime,
                 updatedAt: Option[ZonedDateTime]
               ) {

  import Post._

  require(paragraphs.isEmpty, "No Paragraphs!")
  require(tags.size <= TAG_MAX, s"Tags more than 5: ${tags.size}")
  require(claps >= 0, s"Claps less than zero: $claps")

  def addComment(comment: Comment): Post = {
    copy(comments = comments :+ comment)
  }

  def clap(): Post = {
    copy(claps + 1)
  }

  def edit(title: String, paragraphs: Vector[Paragraph]): Post = {
    copy(title = title, paragraphs = paragraphs)
  }

  def undoClaps(count: Long): Post = {
    copy(claps = claps - count)
  }

}

object Post {

  private final val TAG_MAX: Int = 5

}
