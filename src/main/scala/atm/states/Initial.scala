package atm.states

import atm.{Database, State}

case class Initial() extends State {
  def run(): State = {
    println("Welcome!")
    val result = Database.readBalance("balance.csv")
    result match {
      case Left(message) =>
        Error(message)
      case Right(balance) =>
        MainMenu(balance)
    }
  }
}
