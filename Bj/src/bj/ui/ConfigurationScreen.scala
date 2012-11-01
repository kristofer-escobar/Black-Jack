package bj.ui

import swing._
import event._

object ConfigurationScreen {

  var playerName = ""
  var bankRoll = 0
  var betAmount = 0
  var houseIP = ""
  var housePort = 0
  
}

// Configuration Screen used to collect data from user to start a game.
class ConfigurationScreen extends MainFrame {
  
  title = "Black Jack Game"
    //preferredSize = new Dimension(700, 400)
  contents = new GridBagPanel {
    def constraints(x: Int, y: Int, 
		    gridwidth: Int = 1, gridheight: Int = 1,
		    weightx: Double = 0.0, weighty: Double = 0.0,
		    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None) 
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c
    }

    //add(new Button("Button @ (2,1)"), 
	//constraints(2, 1))
    //add(new Button("Button @ (2,2)"), 
	//constraints(2, 2))
    //add(new CheckBox("Check me!"), 
	//constraints(0, 2))
    // Prompt user to enter player name.
    add(new Label(" Enter player name: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 0, gridheight=1, fill=GridBagPanel.Fill.Both))
    
	// Create text box for player name.
	object tbPlayerName extends TextField{columns = 10}
	
    // Add to GridBagPanel
	add(tbPlayerName, constraints(1, 0, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
    
	// Prompt user to enter bank roll.
	add(new Label(" Enter bank roll amount: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 1, gridheight=1, fill=GridBagPanel.Fill.Both))
	
	// Create text field for bank roll.
	object tbBankRoll extends TextField{columns = 10}
	
	// Add to GridBagPanel
    add(tbBankRoll, 
	constraints(1, 1, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
	
	// Prompt user to enter bet amount.
	add(new Label(" Enter bet amount: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 2, gridheight=1, fill=GridBagPanel.Fill.Both))
	
	// Create text field for bet amount.
	object tbBetAmount extends TextField{columns = 10}
    
    // Add to GridBagPanel
    add(tbBetAmount, constraints(1, 2, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
	
	// Prompt user to enter house ip address.
	add(new Label(" Enter House ip address: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 3, gridheight=1, fill=GridBagPanel.Fill.Both))
	
	// Create text field for house ip address.
	object tbHouseIp extends TextField{columns = 10}
  
    // Add to GridBagPanel
    add(tbHouseIp, constraints(1, 3, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
	
	// Label to prompt user to enter house port number.
	add(new Label(" Enter House port number: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 4, gridheight=1, fill=GridBagPanel.Fill.Both))
	
	// Crete text field for house port number.
	object tbHousePort extends TextField{columns = 10}
    
    // Add to GridBagPanel
    add(tbHousePort, constraints(1, 4, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
	
    //add(new ScrollPane(new TextArea),
	//constraints(1, 1, gridheight=3, weighty = 1.0, 
		//    fill=GridBagPanel.Fill.Both))
	
	// Cancel button to exit.
    add(Button("Quit") { sys.exit(0) }, 
	constraints(0, 5, fill=GridBagPanel.Fill.Horizontal))
	
	// Start button.
	val startButton = new Button{text = "Start"}
    
    // Add button to GridBagPanel
	add(startButton, constraints(1, 5, fill=GridBagPanel.Fill.Horizontal))
	
	// FOR TESTING
	tbPlayerName.text = "kris"
	tbBankRoll.text = "1"
	tbBetAmount.text = "1"
	tbHouseIp.text = "1"
	tbHousePort.text = "1"  
	tbBankRoll.text.toInt
	tbBetAmount.text.toInt
	tbHousePort.text.toInt
	
	// Listen to start button.
	listenTo(startButton)
	
	reactions += {
	  case ButtonClicked(b) => 
	    	// Save configuration.
	    	ConfigurationScreen.playerName = this.tbPlayerName.text
	    	ConfigurationScreen.bankRoll   = this.tbBankRoll.text.toInt
	    	ConfigurationScreen.betAmount  = this.tbBetAmount.text.toInt
	    	ConfigurationScreen.houseIP    = this.tbHouseIp.text
	    	ConfigurationScreen.housePort  = this.tbHousePort.text.toInt
	    	
	    	// Create a game screen and hide the configuration screen.
	    	val game = new HumanGUI()
	    	this.visible = false
	    	game.visible = true
	   
	}
  }
}