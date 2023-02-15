package com.github.saeiddadkhah.entry.rest.finatra

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.marshalling.MessageBodyManager

case class UnauthenticatedRequestWrapper(request: Request, @Inject manager: MessageBodyManager) {

  def getRequestDTO[RequestDTO](implicit m: Manifest[RequestDTO]): RequestDTO = {
    manager.read[RequestDTO](request)
  }

}
