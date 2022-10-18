package atm.model

import atm.model.{Event, History}

import scala.io.Source

case class Account(history: History)

case class History(lastEvent: Option[Event])
