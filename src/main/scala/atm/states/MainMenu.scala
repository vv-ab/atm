package atm.states

import atm.State
import atm.model.{Account, History}

case class MainMenu(account: Account) extends State {
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
      WithdrawnAmount(account)
    }
    else if (action == 2) {
      DepositedAmount(account)
    }
    else if (action == 3) {
      ShowStatement(account)
    }
    else {
      Exit()
    }
  }
}
