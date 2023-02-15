package com.github.saeiddadkhah.entry.rest.finatra

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.marshalling.MessageBodyManager

case class RequestWrapper(request: Request, @Inject manager: MessageBodyManager) {

  def getRequestDTO[RequestDTO](implicit m: Manifest[RequestDTO]): RequestDTO = {
    manager.read[RequestDTO](request)
  }

  def getKey: String = {
    request.cookies.get("key").map(_.value).getOrElse(throw new Exception("Login is required!"))
  }

  def getUserID: Long = {
    request.cookies.get("userID").map(_.value.toLong).getOrElse(throw new Exception("Login is required!"))
  }

  def getUsername: String = {
    request.cookies.get("username").map(_.value).getOrElse(throw new Exception("Login is required!"))
  }

}
