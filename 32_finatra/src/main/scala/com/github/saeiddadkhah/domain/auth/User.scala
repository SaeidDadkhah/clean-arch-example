package com.github.saeiddadkhah.domain.auth

import java.time.ZonedDateTime

case class User(
                 id: Long,
                 username: String,
                 password: String,
                 name: String,
                 eMail: String,
                 bio: Option[String],
                 photoURL: Option[String],
                 followers: Long,
                 followings: Long,
                 signUpAt: ZonedDateTime
               ) {

  require(followers >= 0, s"Followers less than zero: $followers")
  require(followings >= 0, s"Followings less than zero: $followings")

  def addFollower(): User = {
    copy(followers = followers + 1)
  }

  def addFollowing(): User = {
    copy(followings = followings + 1)
  }

  def removeBio(): User = {
    copy(bio = None)
  }

  def removeFollower(): User = {
    copy(followers = followers - 1)
  }

  def removeFollowing(): User = {
    copy(followings = followings - 1)
  }

  def removePhotoURL(): User = {
    copy(photoURL = None)
  }

  def setBio(bio: String): User = {
    copy(bio = Some(bio))
  }

  def setEMail(eMail: String): User = {
    copy(eMail = eMail)
  }

  def setName(name: String): User = {
    copy(name = name)
  }

  def setPhotoURL(photoURL: String): User = {
    copy(photoURL = Some(photoURL))
  }

  def setPassword(password: String): User = {
    copy(password = password)
  }

}
