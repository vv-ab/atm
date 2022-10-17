package atm

case class MainMenu(balance: Int) extends State {
  def run(): State = {
    println(" What do you need?")
    println(
      """|1: Withdraw money
         |2: Deposit money
         |3: Nothing
         |"""
        .stripMargin
    )

    val action = Console.in.readLine().toInt
    if (action == 1) {
      WithdrawnAmount(balance)
    }
    else if (action == 2) {
      DepositedAmount(balance)
    }
    else {
      Exit()
    }
  }
}
