package com.github.saeiddadkhah.entry.rest.finatra.adapter.auth

import com.github.saeiddadkhah.domain.auth._
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api._
import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.CommonDTOFactory

object AuthDTOFactory {

  def session: Session => SessionDTO = o =>
    SessionDTO(o.key, o.userID, o.username)

  def user: User => UserDTO = { o =>
    val signUpAt = CommonDTOFactory zonedDateTime o.signUpAt
    UserDTO(o.id, o.username, o.name, o.eMail, o.bio, o.photoURL, o.followers, o.followings, signUpAt)
  }

}
