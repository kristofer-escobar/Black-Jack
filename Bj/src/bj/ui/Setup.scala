package bj.ui

import swing._
import event._
import bj.actor.HumanPlayer
import bj.actor.BetterPlayer
import bj.actor.Player
import bj.util.Logs
import bj.actor.House

object Setup extends Logs {

  // Human Player setup.
  var name = ""
  var bankRoll = 0.0
  var betAmount = 0.0

  // House setup.
  var houseIp = ""
  var housePort = 0

  // Main method. (Entry point)
  def main(args: Array[String]) {

    // Create setup screen.
    val config = new Setup {
      // Center on screen.
      location = new Point((HumanGUI.SCREEN_WIDTH - size.getWidth().toInt) / 2, (HumanGUI.SCREEN_HEIGHT - size.getHeight().toInt) / 2)
    }

    // Display setup screen.
    config.visible = true
  }

  // List all Players
  val players = List[Player](HumanGUI.Ron, HumanGUI.Bob, HumanGUI.Tim, HumanGUI.Joe, HumanGUI.Kim)

  // Start House.
  debug("starting the house")
  House.start

  Thread.sleep(1000)

  // START AFTER STAY REQUEST
  // Start Bot Players.
  debug("starting bot players")
  BetterPlayer.start(players)

  Thread.sleep(1000)
}

// Configuration Screen used to collect data from user to start a game.
class Setup extends MainFrame {

  // Set title.
  title = "Black Jack Setup"
  contents = new GridPanel(6, 2) {
    
    // Add elements to setup screen.
    contents += new Label(" Enter player name: ")
    object tbPlayerName extends TextField { columns = 10 }
    contents += tbPlayerName
    contents += new Label(" Enter bank roll amount: ")
    object tbBankRoll extends TextField { columns = 10 }
    contents += tbBankRoll
    contents += new Label(" Enter bet amount: ")
    object tbBetAmount extends TextField { columns = 10 }
    contents += tbBetAmount
    contents += new Label(" Enter House ip address: ")
    object tbHouseIp extends TextField { columns = 10 }
    contents += tbHouseIp
    contents += new Label(" Enter House port number: ")
    object tbHousePort extends TextField { columns = 10 }
    contents += tbHousePort
    contents += Button("Quit") { sys.exit(0) }
    val startButton = new Button { text = "Start" }
    contents += startButton

    // FOR TESTING
    tbPlayerName.text = "Kris"
    tbBankRoll.text = "1000"
    tbBetAmount.text = "30"
    tbHouseIp.text = "192.168.1.10"
    tbHousePort.text = "80"
    tbBankRoll.text.toInt
    tbBetAmount.text.toInt
    tbHousePort.text.toInt

    // Listen to start button.
    listenTo(startButton)

    reactions += {
      case ButtonClicked(b) =>

        // Save setup.
        Setup.name = this.tbPlayerName.text
        Setup.bankRoll = this.tbBankRoll.text.toDouble
        Setup.betAmount = this.tbBetAmount.text.toDouble
        Setup.houseIp = this.tbHouseIp.text
        Setup.housePort = this.tbHousePort.text.toInt

        // Create a game screen.
        val game = new HumanGUI

        // Display game screen.
        game.visible = true

        // Close setup window.
        dispose

    }// End of Reactions.
    
  }// End of GridPanel.

}// End of Setup Class.