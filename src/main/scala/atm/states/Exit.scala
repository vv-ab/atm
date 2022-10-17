package atm.states

import atm.State

case class Exit() extends State {
  def run(): State = {
    System.exit(0)
    Exit()
  }
}
