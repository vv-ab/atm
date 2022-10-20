package atm.states

import atm.db.Database
import atm.State
import atm.model.{Account, DepositEvent, History}

case class DepositedAmount(account: Account) extends State {
  def run(): State = {
    println("How much money are you depositing?")
    val depositedAmount = Console.in.readLine().toInt
    val newHistory = account.history.copy(lastEvent = Some(DepositEvent(depositedAmount, account.history.lastEvent)))
    Database.writeHistory(s"${account.name.toLowerCase()}.events", newHistory)
    println(s"Your balance now: ${newHistory.currentBalance()}€")
    MainMenu(account.copy(history = newHistory))
  }
}
