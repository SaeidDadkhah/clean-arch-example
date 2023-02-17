package com.github.saeiddadkhah.entry.rest.finatra.controller

import com.github.saeiddadkhah.contract.service.blog._
import com.github.saeiddadkhah.entry.rest.finatra.RequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.BlogDTOFactory
import com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.BlogFactory
import com.github.saeiddadkhah.entry.rest.finatra.adapter.blog.api._
import com.google.inject.Inject
import com.twitter.finatra.http.Controller

import scala.concurrent.ExecutionContext.Implicits._

class BlogController @Inject()(getPostService: GetPostService, publishPostService: PublishPostService) extends Controller {

  prefix("/api/v1") {

    post("/posts", "Publish Post") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[PublishPostRequestDTO]
      publishPostService call BlogFactory.publishPostRequest(requestWrapper, requestDTO) map BlogDTOFactory.post map { responseDTO =>
        response created responseDTO
      }
    }

    get("/posts/:post_id", "Publish Post") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[GetPostRequestDTO]
      getPostService call BlogFactory.getPostRequest(requestWrapper, requestDTO) map BlogDTOFactory.post
    }

  }

}
