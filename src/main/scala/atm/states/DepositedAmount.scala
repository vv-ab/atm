package atm.states

import atm.db.Database
import atm.State
import atm.model.{DepositEvent, History}

case class DepositedAmount(history: History) extends State {
  def run(): State = {
    println("How much money are you depositing?")
    val depositedAmount = Console.in.readLine().toInt
    val newHistory = history.copy(lastEvent = Some(DepositEvent(depositedAmount, history.lastEvent)))
    Database.writeHistory("account.events", newHistory)
    println(s"Your balance now: ${newHistory.currentBalance()}€")
    MainMenu(newHistory)
  }
}
