package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api

import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

case class CommentDTO(id: Long, userID: Long, postID: Long, text: String, createdAt: ZonedDateTimeDTO)
