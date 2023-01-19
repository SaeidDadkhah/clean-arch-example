package com.github.saeiddadkhah.domain.blog

import java.time.ZonedDateTime

case class Comment(id: Long, userID: Long, postID: Long, text: String, createdAt: ZonedDateTime)
