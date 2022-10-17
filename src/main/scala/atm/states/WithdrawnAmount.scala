package atm.states

import atm.{Database, State}

case class WithdrawnAmount(balance: Int) extends State {
  def run(): State = {
    println("How much money do you want to withdraw?")
    val withdrawnAmount = Console.in.readLine().toInt
    val endBalance = balance - withdrawnAmount
    println(s"Your balance now: ${endBalance}€")
    Database.writeBalance("balance.csv", endBalance)
    MainMenu(endBalance)
  }
}
