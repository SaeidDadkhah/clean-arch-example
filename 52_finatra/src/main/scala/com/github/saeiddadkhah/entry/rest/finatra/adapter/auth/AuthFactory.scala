package com.github.saeiddadkhah.entry.rest.finatra.adapter.auth

import com.github.saeiddadkhah.contract.service.auth._
import com.github.saeiddadkhah.entry.rest.finatra.RequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.UnauthenticatedRequestWrapper
import com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api._

object AuthFactory {

  def signInRequest: (UnauthenticatedRequestWrapper, SignInRequestDTO) => SignInService.Request = (_, dto) =>
    SignInService.Request(dto.username, dto.password)

  def signOuRequest: (RequestWrapper, SignOutRequestDTO) => SignOutService.Request = (rw, _) =>
    SignOutService.Request(rw.getKey)

  def signUpRequest: (UnauthenticatedRequestWrapper, SignUpRequestDTO) => SignUpService.Request = (_, dto) =>
    SignUpService.Request(dto.username, dto.password, dto.name, dto.eMail)

}
