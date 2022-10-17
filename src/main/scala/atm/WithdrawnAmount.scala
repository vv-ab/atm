package atm

case class WithdrawnAmount(balance: Int) extends State {
  def run(): State = {
    println("How much money do you want to withdraw?")
    val withdrawnAmount = Console.in.readLine().toInt
    val endBalance = balance - withdrawnAmount
    println(s"Your balance now: ${endBalance}€")
    MainMenu(endBalance)
  }
}
