package com.github.saeiddadkhah.repository.inmem.repository.auth

import com.github.saeiddadkhah.contract.callback.auth.SessionCallback
import com.github.saeiddadkhah.domain.auth.Session
import com.github.saeiddadkhah.modules.SimpleInMemoryModule

import scala.concurrent.Future

class SessionRepository extends SessionCallback with SimpleInMemoryModule[Session] {

  override def add(key: String, userID: Long, username: String): Future[Session] = {
    val session = Session(key, userID, username)
    addToMemory(session).map(_ => session)
  }

  override def remove(key: String): Future[Unit] = {
    removeFromMemory(_.key == key)
  }

}

// Singleton Repository
object SessionRepository extends SessionRepository
