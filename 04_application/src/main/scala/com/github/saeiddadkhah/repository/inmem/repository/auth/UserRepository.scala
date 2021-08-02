package com.github.saeiddadkhah.repository.inmem.repository.auth

import java.time.ZonedDateTime

import com.github.saeiddadkhah.contract.callback.auth.UserCallback
import com.github.saeiddadkhah.domain.auth.User
import com.github.saeiddadkhah.modules.SimpleInMemoryModule

import scala.concurrent.Future

class UserRepository extends UserCallback with SimpleInMemoryModule[User] {

  override val name: String = "User"

  override def add(
                    username: String,
                    password: String,
                    name: String,
                    eMail: String,
                    bio: Option[String],
                    photoURL: Option[String],
                    followers: Long,
                    followings: Long,
                    signUpAt: ZonedDateTime
                  ): Future[User] = {
    val user = User(generateID(), username, password, name, eMail, bio, photoURL, followers, followings, signUpAt)
    addToMemory(user).map(_ => user)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   * */
  override def get(id: Long): Future[Option[User]] = Future {
    data.find(_.id == id)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   * */
  override def getBy(username: String): Future[Option[User]] = Future {
    data.find(_.username == username)
  }

  override def remove(id: Long): Future[Unit] = {
    removeFromMemory(_.id == id)
  }

  override def update(user: User): Future[Unit] = {
    updateInMemory(user, _.id == user.id)
  }

}

// Singleton Repository
object UserRepository extends UserRepository
