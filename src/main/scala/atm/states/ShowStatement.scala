package atm.states

import atm.State
import atm.model.{Account, DepositEvent, History, WithdrawEvent}

case class ShowStatement(account: Account) extends State {

  def run(): State = {
    println("Your statement:")
    account.history.forEachEvent({ event =>
      event match
        case DepositEvent(amount, previous) =>
          println(s"Deposit: $amount")
        case WithdrawEvent(amount, previous) =>
          println(s"Withdraw: $amount")
        case _ =>
    })
    println(s"Balance: ${account.history.currentBalance()}")
    println("Enter any key to return:")
    Console.in.readLine()
    MainMenu(account)
  }
}
