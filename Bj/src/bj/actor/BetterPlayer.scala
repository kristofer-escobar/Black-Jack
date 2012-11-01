package bj.actor
import scala.actors.Actor
import scala.actors.OutputChannel
import bj.card.Hand
import bj.hkeeping.Ok
import bj.card.Card
import bj.util.Logs
import bj.hkeeping.Broke

object BetterPlayer {
   /** Unique id counter for players */
  var id: Int = -1

  /** Creates, starts, and send the players on their way.  */
  def start(players : List[Player]) {   
    players.foreach { p =>
      p.start
      
      p ! Go
    }
  }
}

class BetterPlayer(name: String, bankroll: Double, betAmt: Double) extends Player(name, bankroll, betAmt) {
  //TODO: check for card.length on certain scenarios.
  //
  override
  def analyze(upcard : Card) : Request = {
  //debug("Inside overriden analyze.")   
  
    upcard.value match{
      case 2 => 
        if(value <= 9 || value == 12 || (softValue >= 3 && softValue <= 7) || 
            ( cards(0).value == 6 && cards(1).value == 6) || ( cards(0).value == 4 && cards(1).value == 4) || 
            ( cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Hit(pid)
        else if(value == 10 || value == 11 || (cards(0).value == 5 && cards(1).value == 5))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) ||
            (cards(0).value == 9 && cards(1).value == 9) || (cards(0).value == 7 && cards(1).value == 7))
          Split(pid)
        else
          Stay(pid)
      case 3  =>
        if(value <= 8 || value == 12 || (softValue >= 3 && softValue <= 6) || 
            ( cards(0).value == 4 && cards(1).value == 4) || ( cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Hit(pid)
        else if(value == 9 || value == 10 || value == 11 || (cards(0).value == 5 && cards(1).value == 5) || softValue == 7 || softValue == 8)
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) ||
            (cards(0).value == 9 && cards(1).value == 9) || (cards(0).value == 7 && cards(1).value == 7) || (cards(0).value == 6 && cards(1).value == 6))
          Split(pid)
        else
          Stay(pid)
      case 4  =>
          if(value <= 8 || (softValue >= 3 && softValue <= 4) || (cards(0).value == 4 && cards(1).value == 4))
          Hit(pid)
        else if((value >= 9 && value <= 11) || (cards(0).value == 5 && cards(1).value == 5) || (softValue >= 5 && softValue <= 8))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || (cards(0).value == 9 && cards(1).value == 9) || 
            (cards(0).value == 7 && cards(1).value == 7) || (cards(0).value == 6 && cards(1).value == 6) || ( cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Split(pid)
        else
          Stay(pid)
      case 5  =>
        if(value <= 8 || (cards(0).value == 4 && cards(1).value == 4))
          Hit(pid)
        else if((value >= 9 && value <= 11) || (cards(0).value == 5 && cards(1).value == 5) || (softValue >= 3 && softValue <= 8))
        {
          debug("inside DD " + "Card Value: " + value + "upcard: " + upcard.value + " " + cards(0).value + " " + cards(1).value + " " + softValue) 
         DoubleDown(pid, bankroll, betAmt)
        }
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || (cards(0).value == 9 && cards(1).value == 9) || 
            (cards(0).value == 7 && cards(1).value == 7) || (cards(0).value == 6 && cards(1).value == 6) || ( cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Split(pid)
        else
          Stay(pid)
      case 6  =>
        if(value <= 8 || (cards(0).value == 4 && cards(1).value == 4 && cards.length == 2))
          Hit(pid)
        else if((value >= 9 && value <= 11) || (cards(0).value == 5 && cards(1).value == 5) || (softValue >= 3 && softValue <= 8))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || (cards(0).value == 9 && cards(1).value == 9) || 
            (cards(0).value == 7 && cards(1).value == 7) || (cards(0).value == 6 && cards(1).value == 6) || ( cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Split(pid)
        else
          Stay(pid)
      case 7  =>
        if(value <= 9 || (value >= 12 && value <= 16) || (cards(0).value == 4 && cards(1).value == 4) || (softValue >= 3 && softValue <=7) || (cards(0).value == 6 && cards(1).value == 6))
          Hit(pid)
        else if(value == 10 || value == 11 || (cards(0).value == 5 && cards(1).value == 5))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || (cards(0).value == 7 && cards(1).value == 7) || 
            (cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2))
          Split(pid)
        else
          Stay(pid)
      case 8  =>
        if(value <= 9 || (value >= 12 && value <= 16) || (cards(0).value == 4 && cards(1).value == 4) || (softValue >= 3 && softValue <=7) ||  (cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2) ||
            (cards(0).value == 6 && cards(1).value == 6) || (cards(0).value == 7 && cards(1).value == 7))
          Hit(pid)
        else if(value == 10 || value == 11 || (cards(0).value == 5 && cards(1).value == 5))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || ( cards(0).value == 9 && cards(1).value == 9))
          Split(pid)
        else
          Stay(pid)
      case 9  =>
        if(value <= 9 || (value >= 12 && value <= 16)|| (cards(0).value == 4 && cards(1).value == 4) || (softValue >= 3 && softValue <=8) || (cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2) ||
            (cards(0).value == 6 && cards(1).value == 6) || (cards(0).value == 7 && cards(1).value == 7))
          Hit(pid)
        else if(value == 10 || value == 11 || (cards(0).value == 5 && cards(1).value == 5))
          DoubleDown(pid, bankroll, betAmt)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8) || ( cards(0).value == 9 && cards(1).value == 9))
          Split(pid)
        else
          Stay(pid)
      case 10 =>
        if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8))
          Split(pid)
        else if(value <= 10 || (value >= 12 && value <= 16) || (cards(0).value == 4 && cards(1).value == 4) || (softValue >= 3 && softValue <=8) || (cards(0).value == 3 && cards(1).value == 3) || ( cards(0).value == 2 && cards(1).value == 2) || 
            (cards(0).value == 5 && cards(1).value == 5) || (cards(0).value == 6 && cards(1).value == 6) || (cards(0).value == 7 && cards(1).value == 7))
          Hit(pid)
        else if(value == 11)
          DoubleDown(pid, bankroll, betAmt)
        else
          Stay(pid)
      case 1  => 
        if(value <= 16 || (softValue >= 3 && softValue <=8) || ( cards(0).value == 2 && cards(1).value == 2) || (cards(0).value == 3 && cards(1).value == 3) ||
           (cards(0).value == 4 && cards(1).value == 4) || (cards(0).value == 5 && cards(1).value == 5) || (cards(0).value == 6 && cards(1).value == 6) || (cards(0).value == 7 && cards(1).value == 7))
          Hit(pid)
        else if((cards(0).ace && cards(1).ace) || (cards(0).value == 8 && cards(1).value == 8))
          Split(pid)
        else
          Stay(pid)
      case _  =>
        Hit(pid)
        }
    }
}

