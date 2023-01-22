package com.github.saeiddadkhah.domain.update

trait Updatable[T <: Updatable[T]] {
  base: T =>

  final def update(actions: Vector[Action]): Either[Action.Test, T] = {
    actions.foldLeft[Either[Action.Test, T]](Right(base)) {
      case (Right(t), test: Action.Test) => if (t test test) Right(t) else Left(test)
      case (Right(t), otherAction) => Right(t update otherAction)
      case (left, _) => left
    }
  }

  protected def test(action: Action.Test): Boolean

  protected def update(action: Action): T

}
