package com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api

import com.twitter.finatra.http.annotations.RouteParam

case class GetPostRequestDTO(@RouteParam("post_id") postID: Long)
