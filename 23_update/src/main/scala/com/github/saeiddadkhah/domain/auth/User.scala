package com.github.saeiddadkhah.domain.auth

import com.github.saeiddadkhah.domain.update.Action
import com.github.saeiddadkhah.domain.update.Updatable

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
               ) extends Updatable[User] {

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

  override protected def test(action: Action.Test): Boolean = action.pathElement(0) match {
    case User.bio => if (action.value == "") bio.isEmpty else bio contains action.value
    case User.eMail => eMail == action.value
    case User.followers => followers == action.value.toLong
    case User.followings => followings == action.value.toLong
    case User.name => name == action.value
    case User.password => password == action.value
    case User.photoURL => if (action.value == "") photoURL.isEmpty else photoURL contains action.value
  }

  override protected def update(action: Action): User = action match {
    case add: Action.Add => add.pathElement(0) match {
      case User.followers => addFollower()
      case User.followings => addFollowing()
      case other => throw new Exception(s"Add action on /$other is not implemented!")
    }
    case remove: Action.Remove => remove.pathElement(0) match {
      case User.bio => removeBio()
      case User.followers => removeFollower()
      case User.followings => removeFollowing()
      case User.photoURL => removePhotoURL()
      case other => throw new Exception(s"Remove action on /$other is not implemented!")
    }
    case replace: Action.Replace => replace.pathElement(0) match {
      case User.bio => setBio(replace.value)
      case User.eMail => setEMail(replace.value)
      case User.name => setName(replace.value)
      case User.photoURL => setPhotoURL(replace.value)
      case User.password => setPassword(replace.value)
      case other => throw new Exception(s"Replace action on /$other is not implemented!")
    }
    case other => throw new Exception(s"$other is not implemented!")
  }

}

object User {

  val bio = "bio"
  val eMail = "eMail"
  val followers = "followers"
  val followings = "followings"
  val name = "name"
  val password = "password"
  val photoURL = "photoURL"

}
