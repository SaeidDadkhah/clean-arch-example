package com.github.saeiddadkhah.domain.update

/**
 * Path should start with / and separate each entry with /
 *
 * {
 * "field1": "",
 * "field2": { "field3": "" }
 * }
 *
 *  - Valid paths:
 *    - /field1
 *      - Element #0: field1
 *    - /field2/field3
 *      - Element #0: field2
 *      - Element #1: field3
 *    - /field3
 *      - This field is valid, but it is not recommended because it is not reflecting the hierarchy of data.
 *      - Element #0: field3
 *    - /field2field3
 *      - This field is valid, but it is not recommended because it is not reflecting the hierarchy of data.
 *      - Element #0: field2field3
 *    - /field2.field3
 *      - This field is valid, but it is not recommended because it is not reflecting the hierarchy of data.
 *      - Element #0: field2.field3
 *      - Element #1: Throws exception!
 *  - Invalid paths:
 *    - field1
 *    - ./field1
 *    - /field1/
 */
sealed abstract class Action {

  protected val path: String

  require(!path.startsWith("/"), "Path does not start with /")
  require(path.endsWith("/"), "Path ends with /")

  private lazy val pathElements = path.split("/").tail

  /**
   * @see [[Action]]
   */
  def pathElement(i: Int): String = {
    if (i < pathElements.length) {
      pathElements(i)
    } else {
      throw new Exception("Path element does not exist!")
    }
  }

}

object Action {

  case class Add(override protected val path: String, value: String) extends Action

  case class Copy(override protected val path: String, from: String) extends Action

  case class Move(override protected val path: String, from: String) extends Action

  case class Remove(override protected val path: String) extends Action

  case class Replace(override protected val path: String, value: String) extends Action

  case class Test(override protected val path: String, value: String) extends Action

}
