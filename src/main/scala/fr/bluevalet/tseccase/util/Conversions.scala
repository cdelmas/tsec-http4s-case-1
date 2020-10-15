package fr.bluevalet.tseccase.util

object Conversions {

  def caseClassToMap(cc: Any, nameConverter: String => String = identity): Map[String, Any] =
    cc.getClass.getDeclaredFields.foldLeft(Map[String, Any]()) { (a, f) =>
      f.setAccessible(true)
      val value = f.get(cc) match {
        case caseClassInstance: Product => caseClassToMap(caseClassInstance): Map[String, Any]
        case x                          => x
      }
      a + (nameConverter(f.getName) -> value)
    }

  def camelToSnakeCase(s: String): String = s.replaceAll("""([A-Z])""", "_$1").toLowerCase

}
