package atm.model

case class History(lastEvent: Option[Event]) {
  def forEachEvent(fn: Event => Unit): Unit = {

    def innerForEach(event: Option[Event]): Unit = {
      event match {
        case Some(event) =>
          fn(event)
          innerForEach(event.previous)
        case None =>
      }
    }

    innerForEach(lastEvent)
  }

  def currentBalance(): Int = {

    def sum(previousValue: Int, event: Option[Event]): Int = {
      event match {
        case Some(DepositEvent(amount, previous)) =>
          sum(previousValue + amount, previous)
        case Some(WithdrawEvent(amount, previous)) =>
          sum(previousValue - amount, previous)
        case _ =>
          previousValue
      }
    }

    sum(0, lastEvent)
  }
}

object History {
  val empty: History = History(lastEvent = None)
}