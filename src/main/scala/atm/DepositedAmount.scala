package atm

case class DepositedAmount(balance: Int) extends State {
  def run(): State = {
    println("How much money are you depositing?")
    val depositedAmount = Console.in.readLine().toInt
    val endBalance = balance + depositedAmount
    println(s"Your balance now: ${endBalance}€")
    MainMenu(endBalance)

  }
}
