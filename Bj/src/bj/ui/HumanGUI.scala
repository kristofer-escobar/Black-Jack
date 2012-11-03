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
import javax.swing.Box
import java.awt.Dimension
import java.awt.Toolkit
import java.beans.Visibility
import javax.swing.table.DefaultTableCellRenderer
import sun.swing.table.DefaultTableCellHeaderRenderer
import bj.actor.BetterPlayer
import bj.actor.Player

// Defines static members
object HumanGUI extends Logs {

  // Get screen resolution.
  val SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

  // Get screen width.
  val SCREEN_WIDTH = SCREEN_SIZE.width;

  // Get screen height.
  val SCREEN_HEIGHT = SCREEN_SIZE.height;

  // Creates connection to remote house actor.
  val HOUSE = select(Node(Setup.houseIp, Setup.housePort), 'house)

  // Relative path to card images.
  val CARD_PATH = "Resources//cards//"

  // Relative path to horizontal card images.
  val HRZNTL_CARD_PATH = "Resources//cardsH//"

  // Hash map for image files.

  PropertyConfigurator.configure("log4j.properties")

  // Create 5 Bot Players.
  val Ron = new BetterPlayer("Ron", 100, 31)
  val Bob = new BetterPlayer("Bob", 200, 32)
  val Tim = new BetterPlayer("Tim", 300, 33)
  val Joe = new BetterPlayer("Joe", 400, 34)
  val Kim = new BetterPlayer("Kim", 500, 35)

  val human = new HumanPlayer(Setup.name, Setup.bankRoll, Setup.betAmount)

} // End of Object.

class HumanGUI extends MainFrame with Logs {
  
  // Start human player.
  debug("starting human players")
  HumanPlayer.start(HumanGUI.human)

  // Start House? Maybe done on server side. 
  debug("telling house go")
  House ! Go

  // Used for remote actors.
  RemoteActor.classLoader = getClass().getClassLoader()

  // Set the Title of mainframe.
  title = "Black Jack Game"

  // Set size.
  preferredSize = new Dimension(HumanGUI.SCREEN_WIDTH, HumanGUI.SCREEN_HEIGHT)

  // Set Background Color
  background = new Color(34, 139, 34)

  // Main BoxPanel to place other panels N, S, E, W.
  val mainLayout = new BorderPanel() {

    object header extends BoxPanel(Orientation.Vertical) {

      object gameStats extends GridPanel(2, 3) {
        val lblTableNumber = new Label("<html><b>Table:</b></html>")
        val lblMinimumBet = new Label("<html><b>Minimum Bet:</b></html>")
        val lblPlayerTurn = new Label("<html><b>Player Turn:</b></html>")

        val tableNumber = new Label("4")
        val minimumBet = new Label("20")
        val playerTurn = new Label("Kris")

        contents += lblTableNumber
        contents += lblMinimumBet
        contents += lblPlayerTurn
        contents += tableNumber
        contents += minimumBet
        contents += playerTurn

      }

      object dealer extends FlowPanel() {

        val lblDealer = new Label("Dealer")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CA.png") }
        var c4 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C4.png") }
        var c5 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C5.png") }
        var c6 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C6.png") }
        var c7 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C7.png") }

        contents += lblDealer
        peer.add(Box.createHorizontalStrut(20))
        contents += backCard
        contents += c10
        contents += backCard
        contents += c5
        contents += c4
        contents += c6
        contents += c7
        peer.add(Box.createVerticalStrut(230))

      }
      contents += gameStats
      contents += dealer
    }

    // Box panel containing FlowPanels for player cards.
    object players extends GridPanel(3, 2) {

      // FlowPanel to hold player 1 cards.
      object playerOne extends FlowPanel {
        val lblPlayerOne = new Label(HumanGUI.human.playerName)
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C10.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "C2.png") }

        contents += lblPlayerOne
        peer.add(Box.createHorizontalStrut(20))
        contents += backCard
        contents += c10
        contents += c2

      }

      object playerTwo extends FlowPanel {
        val lblPlayerTwo = new Label("Player 2")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CK.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CJ.png") }
        contents += lblPlayerTwo
        peer.add(Box.createHorizontalStrut(20))
        contents += backCard
        contents += c10
        contents += c2

      }

      object playerThree extends FlowPanel {
        val lblPlayerThree = new Label("Player 3")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "D3.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CQ.png") }
        contents += lblPlayerThree
        peer.add(Box.createHorizontalStrut(20))

        contents += backCard
        contents += c10
        contents += c2

      }

      object playerFour extends FlowPanel {
        val lblPlayerFour = new Label("Player 4")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "D3.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CQ.png") }
        contents += lblPlayerFour
        peer.add(Box.createHorizontalStrut(20))

        contents += backCard
        contents += c10
        contents += c2

      }

      object playerFive extends FlowPanel {
        val lblPlayerFive = new Label("Player 5")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "D3.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CQ.png") }
        contents += lblPlayerFive
        peer.add(Box.createHorizontalStrut(20))

        contents += backCard
        contents += c10
        contents += c2

      }

      object playerSix extends FlowPanel {
        val lblPlayerSix = new Label("Player 6")
        val backCard = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "back.png") }
        val c10 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "D3.png") }
        val c2 = new Label { icon = new ImageIcon(HumanGUI.CARD_PATH + "CQ.png") }
        contents += lblPlayerSix
        peer.add(Box.createHorizontalStrut(20))

        contents += backCard
        contents += c10
        contents += c2

      }

      contents += playerOne
      contents += playerTwo
      contents += playerThree
      contents += playerFour
      contents += playerFive
      contents += playerSix

    }

    // BoxPanel player request and table.
    object table extends BoxPanel(Orientation.Vertical) {

      object playerRequest extends GridPanel(1, 4) {
        val btnHit = new Button("Hit")
        val btnStay = new Button("Stand")
        val btnDoubleDown = new Button("Double Down")
        val btnSplit = new Button("Split")

        contents += btnHit
        contents += btnStay
        contents += btnDoubleDown
        contents += btnSplit

        // Listen to player request.
        listenTo(btnHit, btnStay, btnDoubleDown, btnSplit)

        reactions += {
          case ButtonClicked(`btnHit`) =>

          case ButtonClicked(`btnHit`) =>
          case ButtonClicked(`btnHit`) =>
          case ButtonClicked(`btnHit`) =>

        }

      }

      // Create table for headers.
      val headerTable = new Table(1, 4) {

        // Make grid visible and set color.
        showGrid = true
        gridColor = Color.BLACK

        // Center header alignment.
        val dtcr = new javax.swing.table.DefaultTableCellRenderer
        dtcr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER)
        peer.setDefaultRenderer(peer.getColumnClass(0), dtcr)

        // Don't allowing editing.
        peer.setEnabled(false)

        // Set color.
        peer.setBackground(new Color(34, 139, 34))
      }

      // Set header text.
      headerTable.update(0, 0, new Label("<html><b>Player</b></html>").text)
      headerTable.update(0, 1, new Label("<html><b>Bank Roll</b></html>").text)
      headerTable.update(0, 2, new Label("<html><b>Bet Amount</b></html>").text)
      headerTable.update(0, 3, new Label("<html><b>Game Outcome</b></html>").text)

      val cellsTable = new Table(6, 4) {

        // Make grid visible and set color.
        showGrid = true
        gridColor = Color.BLACK

        // Center header alignment.
        val dtcr = new javax.swing.table.DefaultTableCellRenderer
        dtcr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER)
        peer.setDefaultRenderer(peer.getColumnClass(0), dtcr)

        // Don't allowing editing.
        peer.setEnabled(false)

        // Set color.
        peer.setBackground(new Color(34, 139, 34))

      }

      // Set player names.
      cellsTable.update(0, 0, HumanGUI.human.playerName)
      cellsTable.update(1, 0, players.playerTwo.lblPlayerTwo.text)
      cellsTable.update(2, 0, players.playerThree.lblPlayerThree.text)
      cellsTable.update(3, 0, players.playerFour.lblPlayerFour.text)
      cellsTable.update(4, 0, players.playerFive.lblPlayerFive.text)
      cellsTable.update(5, 0, players.playerSix.lblPlayerSix.text)

      // Set human player bank roll.
      cellsTable.update(0, 1, HumanGUI.human.playerBankRoll)

      // Set human player bet amount.
      cellsTable.update(0, 2, HumanGUI.human.playerBetAmount)

      // Add table to contents.
      contents += playerRequest
      peer.add(Box.createVerticalStrut(5))
      contents += headerTable
      contents += cellsTable

    }

    // Add dealer to main layout.
    add(header, BorderPanel.Position.North)

    // Add players to main layout.
    add(players, BorderPanel.Position.Center)

    // Add player request and table to main layout.
    add(table, BorderPanel.Position.South)

  } // End of grid bag panel.

  contents = mainLayout

  // Update method.
  def Update() { repaint }

} // End of class HumanGUI

