package bj.test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.junit.Test
import org.apache.log4j.PropertyConfigurator
import bj.util.Logs
import bj.actor.House
import bj.actor.Player
import bj.actor.Go

/**
 * This class tests two players, two at different same table.
 * @author Ron Coleman, Ph.D.
 */
class TwoPlayer02 extends Logs {
  PropertyConfigurator.configure("log4j.properties")
  
  @Test
  def test {
    debug("starting the house")
    House.start

    Thread.sleep(1000)

    debug("starting players")
    val players = List[Player](
      new Player("Bob", 100, 100),
      new Player("Alice",300,200)
    )

    Player.start(players)

    Thread.sleep(1000)

    debug("telling house go")
    House ! Go
    
    Thread.sleep(5000)
    
    assert(players(0).bankroll == 0)
    assert(players(1).bankroll == 500)   
  }
}