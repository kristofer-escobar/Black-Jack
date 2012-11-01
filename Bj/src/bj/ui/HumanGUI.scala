package bj.ui

import swing._
import event._
import bj.actor.HumanPlayer
import scala.actors.remote.RemoteActor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.Node
import bj.actor.Go
import bj.card.Card
import javax.swing.ImageIcon
import bj.actor.House
import org.apache.log4j.PropertyConfigurator
import bj.actor.House
import bj.actor.Dealer
import scala.actors.Actor
import bj.util.Logs
import org.apache.log4j.Logger
import java.awt.Point
import javax.swing.plaf.basic.BasicBorders
import java.awt.Color
import javax.media.j3d.Background


// Defines static members
object HumanGUI extends Logs{
  // Creates connection to remote house actor.
  val HOUSE = select(Node(ConfigurationScreen.houseIP, ConfigurationScreen.housePort), 'house)
  
  // Relative path to card images.
  val CARDPATH = "Resources//cards//"
   
  // Relative path to horizontal card images.
  val HCARDPATH = "Resources//cardsH//"
  
  // Hash map for image files.
 
  
   PropertyConfigurator.configure("log4j.properties")
  
  // Main method.
  def main(args: Array[String]) {
    
    // Create and display configuration screen.
    val configScreen = new ConfigurationScreen
    configScreen.visible = true   
    
    // Instance of a human player.
    val humanPlayer = new HumanPlayer(ConfigurationScreen.playerName,
        ConfigurationScreen.bankRoll,
        ConfigurationScreen.betAmount)
  
    
    debug("starting the house")
    House.start
    
    Thread.sleep(1000)
    
    debug("starting players")
    //val players = List[Player](new Player("Ron", 100, 30))
    //val players = List[BetterPlayer](new BetterPlayer("Ron", 100, 30))
    
    //Player.start(players)
    HumanPlayer.start(humanPlayer)
    
    Thread.sleep(1000)
    
    debug("telling house go")
    //House ! Go
    
  }
}

class HumanGUI extends MainFrame {
  // Used for remote actors.
  RemoteActor.classLoader = getClass().getClassLoader()
  
  // Create instance of a human player.

  
  // Start human player.
  //humanPlayer.start
  
  // Start the game (house).
  HumanGUI.HOUSE ! Go
  
  // Set the Title of mainframe.
  title = "Black Jack Game"
    
  // Set default size.
  preferredSize = new Dimension(1024,768)
  //centerOnScreen
  //var setLocation = new Point(0,0)
  
// Location sets the location of the mainframe on the screen.  
//location = new Point(0,0)
  
//val test = locationOnScreen
 // val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
  
  // Set Background Color
  background = new Color(34,139,34)
  
  // Box panel to place other panels N, S, E, W.
  val mainLayout = new BorderPanel() {
    
    // BorderPanel for Dealer.
    object dealer extends BorderPanel(){
      
      // FlowPanel for dealer  name.
      object dealerName extends FlowPanel{
        val lblDealer = new Label("DEALER")
        contents += lblDealer  
      }
      
      object dealerCards extends FlowPanel{
        
        val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
        val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CA.png") }
        contents += backCard
        contents += c10
        
      }

      add(dealerName, BorderPanel.Position.West)
      add(dealerCards, BorderPanel.Position.Center)
    }

    object playerNamesBoxPanel extends BoxPanel(Orientation.Vertical){
      
      
         object player1Name extends FlowPanel{
        
        val lblDealer = new Label("DEALER")
        val lblPlayer1 = new Label("PLAYER 1")
        val lblPlayer2 = new Label("PLAYER 2")
        val lblPlayer3 = new Label("PLAYER 3")
        val lblPlayer4 = new Label("PLAYER 4")

        contents += lblPlayer1

      }
         
          object player2Name extends FlowPanel{
        
        val lblPlayer2 = new Label("PLAYER 2")
        contents += lblPlayer2 

      }
          
           object player3Name extends FlowPanel{

        val lblPlayer3 = new Label("PLAYER 3")
        contents += lblPlayer3  
      }
      
      //contents += dealerNameBorderPanel
      contents += player1Name
      contents += player2Name
      contents += player3Name
    }

    // Box panel containing FlowPanels for player cards.
    object playersBoxPanel extends BoxPanel(Orientation.Vertical){
      
      // FlowPanel to hold player 1 cards.
      object player1 extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "C10.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "C2.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      object player2 extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CK.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CJ.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      object player3 extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "D3.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CQ.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      contents += player1
      contents += player2
      contents += player3
      
    }
    

    // Box panel containing FlowPanels for player bets.
    object playersBetBoxPanel extends BoxPanel(Orientation.Vertical){
      
      // FlowPanel to hold player 1 cards.
      object player1Bet extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "C10.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "C2.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      object player2Bet extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CK.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CJ.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      object player3Bet extends FlowPanel{
        
    	  val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
    	  val c10 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "D3.png") }
    	  val c2 = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "CQ.png") }
        
        contents += backCard
        contents += c10
        contents += c2
        
      }
      
      contents += player1Bet
      contents += player2Bet
      contents += player3Bet
      
    }
    
    
    // BoxPanel for table.
    object tableBoxPanel extends BoxPanel(Orientation.Vertical) {
    
    // Create table for headers.
    val headerTable = new Table(1,3){ showGrid = true; gridColor = Color.BLACK}
    headerTable.update(0,0, "Bank Roll")
    headerTable.update(0,1, "Bet Amount")
    headerTable.update(0,2, "Game Outcome")
    
    val cellsTable = new Table(1,3){ showGrid = true; gridColor = Color.BLACK}
    
      contents += headerTable
      contents += cellsTable
      
    }
    
    // Add boxPanel with table to BorderPanel.
    add(dealer, BorderPanel.Position.North)
    add(tableBoxPanel, BorderPanel.Position.South)
    add(playersBoxPanel, BorderPanel.Position.Center)
    add(playerNamesBoxPanel, BorderPanel.Position.West)
    //add(playerBetsBoxPanel, BorderPanel.Position.East)
    
  
//    //val backCard = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "back.png") }
//    //add(backCard, constraints(0, 0, gridwidth=2, gridheight=2, fill=GridBagPanel.Fill.Both))
//    
//    //val test = new Label {icon = new ImageIcon(HumanGUI.CARDPATH + "C10.png") }
//    //add(test, constraints(2, 0, 1, gridheight=2, fill=GridBagPanel.Fill.Both))
//
//        
 } // End of grid bag panel.
      //add(card2, constraints(1, 1, 1, gridheight=1, fill=GridBagPanel.Fill.Both))
  
  contents = mainLayout

} // End of class HumanGUI

