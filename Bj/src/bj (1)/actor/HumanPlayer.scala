package bj.actor
import scala.actors.Actor
import scala.actors.OutputChannel
import bj.card.Hand
import bj.hkeeping.Ok
import bj.card.Card
import bj.util.Logs
import bj.hkeeping.Broke
import bj.ui.HumanGUI

object HumanPlayer {
  

  
   /** Unique id counter for players */
  var id: Int = -1

  /** Creates, starts, and send the players on their way.  */
  def start(player : Player) {   
      player.start
      
      player ! Go
    
  }
}

class HumanPlayer(name: String, var bankRoll: Double, betAmt: Double) extends Player(name,  bankRoll, betAmt) {
override
  def act {
    loop {
      react {
        // Receives message to tell player to place its bet
        case Go =>
          debug(this+" received Go placing bet = "+betAmt+" from bankroll = "+bankroll)
          bet
          
        // Receives the dealer's up-card which is player's cue to play
        case Up(card) =>
          
          debug(this + " received dealer's up card = " + card)
          
          //displayUpCard()
          
          play(card)

        // Receives a card from the dealer
        case card: Card =>         
          hitMe(card)

        // Receives broke message
        case Broke =>
          debug(this+ " received BROKE")
          
        // Receives message about dealt card
        case Observe(card,player,shoeSize) =>
          debug(this+" observed: "+card)
          observe(card,player,shoeSize)
          
        // Receives the table number I've been assigned to
        case TableNumber(tid : Int) =>
          debug(this+" received table assignment tid = "+tid)
          assign(tid)
        
        case Win(gain) =>
          val won = betAmt * gain
          
          bankRoll += won
          
          debug(this+" received WIN " + won + " new bankroll = "+bankroll)
          
        case Loose(gain) =>
          val lost = betAmt * gain
          
          bankRoll += lost
          
          debug(this+" received LOOSE " + lost + " new bankroll = "+bankroll)          
          
        case Push(gain) =>
          debug(this+" received PUSH bankroll = "+bankroll)
          
        // Receives an ACK
        case Ok =>
          debug(this + " received Ok")

        // Receives something completely from left field
        case dontKnow =>
          // Got something we REALLY didn't expect
          debug(this+" received unexpected: "+dontKnow)
      }
    }

  }
}

