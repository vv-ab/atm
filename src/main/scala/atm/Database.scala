package atm

import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets
import scala.io.Source
import java.nio.file.{Files, Paths}

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
  
  def writeBalance(destinationFile: String, balance: Int): Either[String, Unit] = {
    val fileContent = s"$balance"
    try {
      Files.write(Paths.get(destinationFile), fileContent.getBytes(StandardCharsets.UTF_8))
      Right(())
    }
    catch {
      case _: Throwable => Left("failed to write balance")
    }
  }
}
