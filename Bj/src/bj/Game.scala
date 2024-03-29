//Copyright (C) 2011 Ron Coleman. Contact: ronncoleman@gmail.com
//
//This library is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either
//version 3 of the License, or (at your option) any later version.
//
//This library is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this library; if not, write to the Free Software
//Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
package bj
//import bj.actor.Player
import bj.actor.BetterPlayer
import bj.actor.House
import bj.actor.Dealer
import scala.actors.Actor
import bj.actor.Go
import bj.util.Logs
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.log4j.PropertyConfigurator

case class Done
case class Launch

object Game extends Logs {
//  Logger.getLogger(getClass).setLevel(Level.DEBUG)
//    
  PropertyConfigurator.configure("log4j.properties")
  
  def main(args: Array[String]) : Unit = {
    debug("starting the house")
    House.start
    
    Thread.sleep(1000)
    
    debug("starting players")
    //val players = List[Player](new Player("Ron", 100, 30))
    val players = List[BetterPlayer](new BetterPlayer("Ron", 100, 30))
    
    //Player.start(players)
    BetterPlayer.start(players)
    
    Thread.sleep(1000)
    
    debug("telling house go")
    House ! Go
  }
}