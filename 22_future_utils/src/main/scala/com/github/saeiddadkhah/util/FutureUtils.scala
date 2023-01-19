package com.github.saeiddadkhah.util

import com.github.saeiddadkhah.domain.exception.DomainException

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object FutureUtils {

  implicit class FutureOptionUtils[A](option: Future[Option[A]]) {

    def getOrThrow(exception: => DomainException)(implicit ec: ExecutionContext): Future[A] = {
      option flatMap {
        case Some(value) => Future successful value
        case None => Future failed exception
      }
    }

    def throwIfExists(exception: DomainException)(implicit ec: ExecutionContext): Future[Unit] = {
      option flatMap {
        case Some(_) => Future failed exception
        case None => Future.unit
      }
    }

  }

  class If(condition: => Boolean) {

    def `then`[T](firstFunction: => T)(implicit ec: ExecutionContext): IfThenElse[T] = IfThenElse(condition, Future(firstFunction))

    def thenDo[T](firstFunction: => Future[T]): IfThenElse[T] = IfThenElse(condition, firstFunction)

    def `throw`(exception: => DomainException): Future[Unit] = {
      if (condition) Future failed exception else Future.unit
    }

  }

  object If {

    def apply(condition: => Boolean): If = new If(condition)

  }

  private[FutureUtils] class IfThenElse[T](condition: => Boolean, firstFunction: => Future[T]) {

    def elseThrow(exception: DomainException): Future[T] = {
      if (condition) firstFunction else Future failed exception
    }

  }

  object IfThenElse {

    def apply[T](condition: => Boolean, firstFunction: => Future[T]): IfThenElse[T] = new IfThenElse(condition, firstFunction)

  }

}
