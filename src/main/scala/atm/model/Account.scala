package atm.model

import atm.model.{Event, History}

import scala.io.Source

case class Account(name: String, history: History)
