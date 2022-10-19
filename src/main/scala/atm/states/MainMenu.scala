package atm.states

import atm.State
import atm.model.History

case class MainMenu(history: History) extends State {
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
      WithdrawnAmount(history)
    }
    else if (action == 2) {
      DepositedAmount(history)
    }
    else if (action == 3) {
      ShowStatement(history)
    }
    else {
      Exit()
    }
  }
}
