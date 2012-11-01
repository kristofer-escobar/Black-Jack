package bj.test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.junit.Test
import org.apache.log4j.PropertyConfigurator
import bj.util.Logs
import bj.actor.House
import bj.actor.Dealer
import bj.actor.BetterPlayer
import bj.actor.Go
import bj.card.Card
import bj.actor.Up
import scala.actors.Actor._
import bj.actor.Stay
import scala.actors.TIMEOUT
import bj.actor.Hit
import bj.actor.DoubleDown
import bj.hkeeping.Broke

class BasicStrategy09 extends Logs {
  // Tell log4j how to format output
  PropertyConfigurator.configure("log4j.properties")
 
 @Test
  def test {
	// Create a player with a bankroll of $100 and betting $25
    val player = new BetterPlayer("Ron", 100, 25)
    
    // Start the player otherwise, the player can't receive messages
    player.start
    
    // Deal two cards
    player ! Card(5,Card.CLUB)
    player ! Card(9,Card.DIAMOND)
    
    // Send the up-card to tell player it's its turn
    player ! Up(Card(8,Card.SPADE))
    
     // Wait for the player to respond
    receiveWithin(2000) {
      case Hit(player.pid) =>
        debug("Hit Request received")
        
      case TIMEOUT =>
        debug("TEST FAILED player crashed?")
        assert(false)
        
      case huh =>
        debug("TEST FAILED got result = "+huh)
        assert(false)
    }
    
    player ! Card(Card.KING,Card.DIAMOND)

        receiveWithin(2000) {
      case Broke =>
        debug("TEST PASSED " + player + " broke.")
        assert(true)
      
      case TIMEOUT =>
        debug("TEST FAILED player crashed?")
        assert(false)
        
      case huh =>
        debug("TEST FAILED got result = "+huh)
        assert(false)
    }
    
  }
}