package com.github.saeiddadkhah.repository.inmem.repository.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.domain.auth.Session
import com.github.saeiddadkhah.modules.SimpleInMemoryModule
import com.google.inject.Singleton

import scala.concurrent.Future

@Singleton
class SessionRepository extends SessionCallback with SimpleInMemoryModule[Session] {

  override val name: String = "Session"

  override def add(key: String, userID: Long, username: String): Future[Session] = {
    val session = Session(key, userID, username)
    addToMemory(session).map(_ => session)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   */
  override def get(key: String): Future[Option[Session]] = Future {
    data.find(_.key == key)
  }

  override def remove(key: String): Future[Unit] = {
    removeFromMemory(_.key == key)
  }

}
