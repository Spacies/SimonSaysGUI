package simonsays;

import simonsays.gameModel.Game;
import simonsays.gui.SimonSaysGUI;

/**
 * This is a SimonSays game produced for the project component of Project 
 * Design and Construction, AUT - Semester 1, 2014
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 18/03/14
 */
public class SimonSays 
{
   
    
    /**
     * This is the main method of the SimonSays game
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create the game object.
        final Game game = new Game();
        // Currently handled in Game class, otherwise GUI not displayed until
        // CLI exits.
//      // Create the game GUI
        final SimonSaysGUI gui = SimonSaysGUI.getSingletonSimonSaysGUI(game);
        
//        
//        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
           public void run() 
           {
               gui.setVisible(true);
            }
        });
    }
}    

