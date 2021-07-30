package com.github.saeiddadkhah.domain.blog

sealed abstract class Paragraph

object Paragraph {

  case class Header(text: String) extends Paragraph

  case class Image(url: String, caption: String) extends Paragraph

  case object Splitter extends Paragraph

  case class Text(text: String) extends Paragraph

}
