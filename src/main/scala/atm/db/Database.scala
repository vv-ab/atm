package atm.db

import atm.model.{DepositEvent, Event, History, WithdrawEvent}

import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
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

  def readHistory(sourceFile: String): Either[String, History] = {

    try {
      val source = Source.fromFile(sourceFile)
      val lines = source.getLines().toList
      source.close()
      val lastEvent: Option[Event] = lines.foldLeft(Option.empty[Event])({ (previous, line) =>

        val split = line.split(" ")

        val result = split match {
          case Array("+", amount) =>
            Some(DepositEvent(amount.toInt, previous))
          case Array("-", amount) =>
            Some(WithdrawEvent(amount.toInt, previous))
          case _ =>
            None
        }

        result
      })

      val history = History(lastEvent)
      Right(history)
    }
    catch {
      case _: FileNotFoundException => Left("No history found.")
    }
  }

  def writeHistory(destinationFile: String, history: History): Either[String, Unit] = {
    def foldEvents(result: String, currentEvent: Option[Event]): String = {
      currentEvent match {
        case Some(DepositEvent(amount, previous)) =>
          val newResult = s"+ $amount\n" + result
          foldEvents(newResult, previous)
        case Some(WithdrawEvent(amount, previous)) =>
          val newResult = s"- $amount\n" + result
          foldEvents(newResult, previous)
        case _ =>
          result
      }
    }
    val fileContent: String = foldEvents("", history.lastEvent)
    try {
      Files.write(Paths.get(destinationFile), fileContent.getBytes(StandardCharsets.UTF_8))
      Right(())
    }
    catch
    {
      case _: Throwable => Left("failed to write history")
    }
  }
}
