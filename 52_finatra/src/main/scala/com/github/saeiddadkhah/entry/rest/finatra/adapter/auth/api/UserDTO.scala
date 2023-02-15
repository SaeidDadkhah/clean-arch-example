package com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api

import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

case class UserDTO(
                    id: Long,
                    username: String,
                    // password: String, // We should never serialize password!
                    name: String,
                    eMail: String,
                    bio: Option[String],
                    photoURL: Option[String],
                    followers: Long,
                    followings: Long,
                    signUpAt: ZonedDateTimeDTO
                  )
