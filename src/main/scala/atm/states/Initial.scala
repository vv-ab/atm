package atm.states

import atm.State
import atm.db.Database

case class Initial() extends State {
  def run(): State = {
    println("Welcome!")
    val readBalanceResult = Database.readBalance("balance.csv")
    val readHistoryResult = Database.readHistory("account.events")
    (readBalanceResult, readHistoryResult) match {
      case (Left(message), _) =>
        Error(message)
      case (_, Left(message)) =>
        Error(message)
      case (Right(balance), Right(history)) =>
        MainMenu(balance, history)
    }
  }
}
