package com.dinotech.elastic4s

sealed trait Operator
object Operator {

  def valueOf(str: String): Operator = str.toLowerCase match {
    case "or"  => Or
    case "and" => And
  }

  case object And extends Operator
  case object Or  extends Operator

  def AND = And
  def OR  = Or
}
