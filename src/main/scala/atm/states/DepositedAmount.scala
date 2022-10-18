package atm.states

import atm.db.Database
import atm.State
import atm.model.History

case class DepositedAmount(balance: Int, history: History) extends State {
  def run(): State = {
    println("How much money are you depositing?")
    val depositedAmount = Console.in.readLine().toInt
    val endBalance = balance + depositedAmount
    println(s"Your balance now: ${endBalance}€")
    Database.writeBalance("balance.csv", endBalance)
    MainMenu(endBalance, history)
  }
}
