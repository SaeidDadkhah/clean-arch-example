package com.github.saeiddadkhah.modules

import com.github.saeiddadkhah.domain.exception.DomainException.NotFound
import com.github.saeiddadkhah.util.FutureUtils._

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future

trait SimpleInMemoryModule[T] {

  val name: String

  implicit val ec: ExecutionContext = SimpleInMemoryModule.ec

  var data: Vector[T] = Vector.empty
  val id: AtomicLong = new AtomicLong(1)

  def addToMemory(t: T): Future[Unit] = Future {
    data synchronized {
      data = data :+ t
    }
  }

  def generateID(): Long = {
    id.getAndIncrement()
  }

  def pagination(data: Vector[T], limit: Int, offset: Int): Vector[T] = {
    if (data.size <= offset) {
      Vector.empty
    } else {
      val dataWithOffset = data drop offset
      if (dataWithOffset.size <= limit) {
        dataWithOffset
      } else {
        dataWithOffset take limit
      }
    }
  }

  def removeFromMemory(predicate: T => Boolean): Future[Unit] = {
    val index = data indexWhere predicate
    If(index != -1) `then` {
      data synchronized {
        data = data.take(index) ++ data.drop(index + 1)
      }
    } elseThrow {
      NotFound("Record", name)
    }
  }

  def updateInMemory(t: T, predicate: T => Boolean): Future[Unit] = {
    val index = data indexWhere predicate
    If(index != -1) `then` {
      data synchronized {
        data = (data.take(index) :+ t) ++ data.drop(index + 1)
      }
    } elseThrow {
      NotFound("Record", name)
    }
  }

}

object SimpleInMemoryModule {

  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

}
