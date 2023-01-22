package com.github.saeiddadkhah.domain.blog

import com.github.saeiddadkhah.domain.update.Action
import com.github.saeiddadkhah.domain.update.Updatable

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
               ) extends Updatable[Post] {

  import Post._

  require(paragraphs.nonEmpty, "No Paragraphs!")
  require(tags.size <= TAG_MAX, s"Tags more than $TAG_MAX: ${tags.size}")
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

  override protected def test(action: Action.Test): Boolean = action.pathElement(0) match {
    case Post.claps => claps == action.value.toLong
    case Post.title => title == action.value
  }

  override protected def update(action: Action): Post = action match {
    case add: Action.Add => add.pathElement(0) match {
      case Post.claps => clap()
      case other => throw new Exception(s"Add action on /$other is not implemented!")
    }
    case remove: Action.Remove => remove.pathElement(0) match {
      case Post.claps => undoClaps(1)
      case other => throw new Exception(s"Replace action on /$other is not implemented!")
    }
    case replace: Action.Replace => replace.pathElement(0) match {
      case Post.title => edit(replace.value, paragraphs)
      case other => throw new Exception(s"Replace action on /$other is not implemented!")
    }
    case other => throw new Exception(s"$other is not implemented!")
  }

}

object Post {

  private final val TAG_MAX: Int = 5

  val claps = "claps"
  val title = "title"

}
