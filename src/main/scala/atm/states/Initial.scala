package atm.states

import atm.State
import atm.db.Database
import atm.model.{Account, History}

case class Initial() extends State {
  def run(): State = {
    println("Welcome!\nEnter your account's PIN:")
    val pin = Console.in.readLine()

    val account: Option[Account] = pin match {
      case "1234" =>
        println("Hello Tom!")
        Some(Account("Tom", History.empty))
      case "4321" =>
        println("Hello Jerry!")
        Some(Account("Jerry", History.empty))
      case _ =>
        None
    }

    account match {
      case Some(account) =>
        val readHistoryResult = Database.readHistory(s"${account.name.toLowerCase()}.events")
        readHistoryResult match {
          case Left(message) =>
            Error(message)
          case Right(history) =>
            MainMenu(account.copy(history = history))
        }
      case None =>
        Error("unknown account")
    }
  }
}
