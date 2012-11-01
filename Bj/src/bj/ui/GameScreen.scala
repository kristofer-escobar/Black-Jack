package bj.ui

import swing._
import event._
import bj.ui._

class GameScreen() extends MainFrame {
  title = "Black Jack Game"
    preferredSize = new Dimension(800,600)
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
    add(new Label(" Enter player name: "), //{border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 0, gridheight=1, fill=GridBagPanel.Fill.Both))
	object test extends TextField{columns = 10}
    add(test, 
	constraints(1, 0, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
	Console.println(name)
	add(new Label(name),
	constraints(0, 1, gridheight=1, fill=GridBagPanel.Fill.Both))
	test.text = ConfigurationScreen.playerName
	  

  }
  
  
}