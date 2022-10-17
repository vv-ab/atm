import atm.{State, Initial}

@main
def main(): Unit = {
  var state: State = Initial()
  while (true) {
    state = state.run()
  }
}
