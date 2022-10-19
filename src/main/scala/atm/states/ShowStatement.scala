package atm.states

import atm.State
import atm.model.{DepositEvent, History, WithdrawEvent}

case class ShowStatement(history: History) extends State {

  def run(): State = {
    println("Your statement:")
    history.forEachEvent({ event =>
      event match
        case DepositEvent(amount, previous) =>
          println(s"Deposit: $amount")
        case WithdrawEvent(amount, previous) =>
          println(s"Withdraw: $amount")
        case _ =>
    })
    println(s"Balance: ${history.currentBalance()}")
    println("Enter any key to return:")
    Console.in.readLine()
    MainMenu(history)
  }
}
