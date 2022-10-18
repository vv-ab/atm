package atm.model

trait Event {

}

case class WithdrawEvent(amount:  Int, previous: Option[Event]) extends Event
case class DepositEvent(amount: Int, previous: Option[Event]) extends Event