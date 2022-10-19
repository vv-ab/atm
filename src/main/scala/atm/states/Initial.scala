package atm.states

import atm.State
import atm.db.Database

case class Initial() extends State {
  def run(): State = {
    println("Welcome!")
    val readHistoryResult = Database.readHistory("account.events")
    readHistoryResult match {
      case Left(message) =>
        Error(message)
      case Right(history) =>
        MainMenu(history)
    }
  }
}
