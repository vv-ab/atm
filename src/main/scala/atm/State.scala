package atm

trait State {
  def run(): State
}
