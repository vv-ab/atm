import atm.State
import atm.states.Initial

@main
def main(): Unit = {
  var state: State = Initial()
  while (true) {
    state = state.run()
  }
}
