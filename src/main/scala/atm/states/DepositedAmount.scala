package atm.states

import atm.{Database, State}

case class DepositedAmount(balance: Int) extends State {
  def run(): State = {
    println("How much money are you depositing?")
    val depositedAmount = Console.in.readLine().toInt
    val endBalance = balance + depositedAmount
    println(s"Your balance now: ${endBalance}€")
    Database.writeBalance("balance.csv", endBalance)
    MainMenu(endBalance)
  }
}
