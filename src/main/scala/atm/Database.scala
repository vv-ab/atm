package atm

import java.io.FileNotFoundException
import scala.io.Source

object Database {
  def readBalance(sourceFile: String): Either[String, Int] = {

    try {
      val source = Source.fromFile(sourceFile)
      val balance = source.mkString.toInt
      source.close()
      Right(balance)
    }
    catch {
      case _: FileNotFoundException => Left("account not found")
    }
  }
}
