package atm.states

import atm.db.Database
import atm.State
import atm.model.{History, WithdrawEvent}

case class WithdrawnAmount(balance: Int, history: History) extends State {
  def run(): State = {
    println("How much money do you want to withdraw?")
    val withdrawnAmount = Console.in.readLine().toInt
    val endBalance = balance - withdrawnAmount
    println(s"Your balance now: ${endBalance}€")
    Database.writeBalance("balance.csv", endBalance)
    val newHistory = history.copy(lastEvent = Some(WithdrawEvent(withdrawnAmount, history.lastEvent)))
    Database.writeHistory("account.events", newHistory)
    MainMenu(endBalance, newHistory)
  }
}
