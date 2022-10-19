package atm.states

import atm.db.Database
import atm.State
import atm.model.{History, WithdrawEvent}

case class WithdrawnAmount(history: History) extends State {
  def run(): State = {
    println("How much money do you want to withdraw?")
    val withdrawnAmount = Console.in.readLine().toInt
    val newHistory = history.copy(lastEvent = Some(WithdrawEvent(withdrawnAmount, history.lastEvent)))
    Database.writeHistory("account.events", newHistory)
    println(s"Your balance now: ${newHistory.currentBalance()}€")
    MainMenu(newHistory)
  }
}
