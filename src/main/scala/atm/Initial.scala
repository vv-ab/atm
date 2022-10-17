package atm

case class Initial() extends State {
  def run(): State = {
    println("Welcome!")
    val result = Database.readBalance("balance.csv")
    result match {
      case Left(message) =>
        atm.Error(message)
      case Right(balance) =>
        MainMenu(balance)
    }
  }
}
