import com.sun.org.apache.bcel.internal.classfile.SourceFile

import java.io.FileNotFoundException
import scala.io.Source
@main
def main(): Unit = {
  println("Welcome! What do you need?")

  val balance = readBalance("balance.csv")

  balance match {
    case Left(message) =>
      println(s"$message")
    case Right(balance) =>

      println(s"Your current balance: ${balance}€")
      println(
        """|1: Withdraw money
           |2: Deposit money
           |3: Nothing
           |"""
          .stripMargin
      )

      val action = Console.in.readLine().toInt

      if (action == 1) {
        println("How much money do you want to withdraw?")
        val withdrawnAmount = Console.in.readLine().toInt
        val endBalance = balance - withdrawnAmount
        println(s"Your balance now: ${endBalance}€")
      }
      else if (action == 2) {
        println("How much money are you depositing?")
        val depositedAmount = Console.in.readLine().toInt
        val endBalance = balance + depositedAmount
        println(s"Your balance now: ${endBalance}€")
      }
      else {
        println("Goodbye")
      }
  }
}

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