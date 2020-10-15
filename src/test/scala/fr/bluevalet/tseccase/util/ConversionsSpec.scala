package fr.bluevalet.tseccase.util

import fr.bluevalet.tseccase.util.Conversions._
import munit.FunSuite

case class Simple(i: Int, s: String)
case class Leaf(s: String)
case class Nested(i: Int, s: Simple, l: Leaf)
case class Example(exampleId: Long, exampleLabel: String)

class ConversionsSpec extends FunSuite {

  test("Convert a simple case class") {
    val toConvert = Simple(3, "trois")

    val m = caseClassToMap(toConvert)

    assertEquals(m, Map[String, Any]("i" -> 3, "s" -> "trois"))
  }

  test("Convert a nested class") {
    val toConvert = Nested(42, Simple(3, "trois"), Leaf("quarante deux"))

    val m = caseClassToMap(toConvert)

    assertEquals(
      m,
      Map[String, Any](
        "i" -> 42,
        "s" -> Map[String, Any]("i" -> 3, "s" -> "trois"),
        "l" -> Map[String, Any]("s" -> "quarante deux")
      )
    )
  }

  test("Use a name converter") {
    val toConvert = Example(42, "label")

    val m = caseClassToMap(toConvert, camelToSnakeCase)

    assertEquals(m, Map[String, Any]("example_id" -> 42, "example_label" -> "label"))
  }

}
