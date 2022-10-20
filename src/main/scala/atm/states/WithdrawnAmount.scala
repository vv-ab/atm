package atm.states

import atm.db.Database
import atm.State
import atm.model.{History, Account, WithdrawEvent}

case class WithdrawnAmount(account: Account) extends State {
  def run(): State = {
    println("How much money do you want to withdraw?")
    val withdrawnAmount = Console.in.readLine().toInt
    val newHistory = account.history.copy(lastEvent = Some(WithdrawEvent(withdrawnAmount, account.history.lastEvent)))
    Database.writeHistory(s"${account.name.toLowerCase()}.events", newHistory)
    println(s"Your balance now: ${newHistory.currentBalance()}€")
    MainMenu(account.copy(history = newHistory))
  }
}
