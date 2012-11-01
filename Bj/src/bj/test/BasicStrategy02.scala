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

/**
 * Test 10+10 vs. 2 => STAY
 * @author Ron Coleman, Ph.D.
 */
class BasicStrategy02 extends Logs {
  // Tell log4j how to format output
  PropertyConfigurator.configure("log4j.properties")
 
 @Test
  def test {
	// Create a player with a bankroll of $100 and betting $25
    val player = new BetterPlayer("Ron", 100, 25)
    
    // Start the player otherwise, the player can't receive messages
    player.start
    
    // Deal two cards
    player ! Card(2,Card.CLUB)
    player ! Card(7,Card.DIAMOND)
    
    // Send the up-card to tell player it's its turn
    player ! Up(Card(Card.JACK,Card.SPADE))
    
    // Wait for the player to respond
    receiveWithin(2000) {
      case Hit(player.pid) =>
        debug("Got Hit Request.")
        
      case TIMEOUT =>
        debug("TEST FAILED player crashed?")
        assert(false)
        
      case huh =>
        debug("TEST FAILED got result = "+huh)
        assert(false)
    }
    
    // Hit a 10.
    player ! Card(Card.TEN,Card.DIAMOND)
    
     // Wait for the player to respond
    receiveWithin(2000) {
      case Stay(player.pid) =>
        debug("TEST PASSED")
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