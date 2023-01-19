package com.github.saeiddadkhah.domain.exception

abstract sealed class DomainException extends Exception

object DomainException {

  case class Exists(elementType: String, elementValue: String) extends DomainException

  case object LoginIsRequired extends DomainException

  case class NotFound(elementType: String, elementValue: String) extends DomainException

  object NotFound {

    def apply(elementType: String, elementValue: Long): NotFound = {
      NotFound(elementType, elementValue.toString)
    }

  }

  case object WrongUsernameAndPassword extends DomainException

}
