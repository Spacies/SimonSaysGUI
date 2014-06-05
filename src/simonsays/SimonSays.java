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
     * This is the main method of the SimonSays game.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Set the Metal look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                //if ("Nimbus".equals(info.getName())) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimonSaysGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        // Create the game object.
        final Game game = Game.getGameObject();

        // Create the game GUI
        final SimonSaysGUI gui = SimonSaysGUI.getSingletonSimonSaysGUI(game);
        
       
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
           public void run() 
           {
               gui.setVisible(true);
            }
        });
        
        // display welcome splash
        gui.displayInstructions();
    }
}    

