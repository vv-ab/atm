package atm.states

import atm.State

case class Error(message: String) extends State {
  def run(): State = {
    println(s"An error occurred: $message")
    Exit()
  }
}
