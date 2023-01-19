package com.github.saeiddadkhah.repository.inmem.repository.blog

import com.github.saeiddadkhah.contract.callback.blog.ClapCallback
import com.github.saeiddadkhah.domain.blog.Clap
import com.github.saeiddadkhah.modules.SimpleInMemoryModule

import scala.concurrent.Future

class ClapRepository extends ClapCallback with SimpleInMemoryModule[Clap] {

  override val name: String = "Clap"

  override def add(userID: Long, postID: Long, count: Long): Future[Clap] = {
    val clap = Clap(userID, postID, count)
    addToMemory(clap).map(_ => clap)
  }

  /**
   * Use [[scala.Option]] class to show explicitly that you don't expect a result for any parameter.
   */
  override def getBy(userID: Long, postID: Long): Future[Option[Clap]] = Future {
    data.find(clap => clap.userID == userID && clap.postID == postID)
  }

  /**
   * Add limit and offset parameters when you expect multiple objects.
   */
  override def getBy(postID: Long, limit: Int, offset: Int): Future[Vector[Clap]] = Future {
    pagination(data.filter(_.postID == postID), limit, offset)
  }

  override def remove(userID: Long, postID: Long): Future[Unit] = {
    removeFromMemory(clap => clap.userID == userID && clap.postID == postID)
  }

  override def update(clap: Clap): Future[Unit] = {
    updateInMemory(clap, old => old.userID == clap.userID && old.postID == clap.postID)
  }

}
