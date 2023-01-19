package com.github.saeiddadkhah.domain.blog

case class Clap(userID: Long, postID: Long, count: Long) {

  def clap(): Clap = {
    copy(count = count + 1)
  }

}
