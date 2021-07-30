package com.github.saeiddadkhah.contract.service

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class Service[Request, Response] {

  /**
   * We should wrap all of computations to handle exceptions in a functional way.
   * Scala's Try class is the other option. Since it is synchronous, it is not a good option.
   *
   * @see [[Future]]
   * @see [[scala.util.Try]]
   * */
  def call(request: Request)(implicit ec: ExecutionContext): Future[Response]

}
