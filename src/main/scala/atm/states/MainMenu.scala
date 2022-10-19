package atm.states

import atm.State
import atm.model.History

case class MainMenu(balance: Int, history: History) extends State {
  def run(): State = {
    println(" What do you need?")
    println(
      """|1: Withdraw money
         |2: Deposit money
         |3: Show statement
         |4: Nothing
         |"""
        .stripMargin
    )

    val action = Console.in.readLine().toInt
    if (action == 1) {
      WithdrawnAmount(balance, history)
    }
    else if (action == 2) {
      DepositedAmount(balance, history)
    }
    else if (action == 3) {
      ShowStatement(balance, history)
    }
    else {
      Exit()
    }
  }
}
