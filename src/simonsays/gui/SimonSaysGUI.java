
package simonsays.gui;

import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import simonsays.gameModel.Game;
import simonsays.gameModel.GameEventListener;
import simonsays.gameModel.GameState;
import simonsays.gameModel.MakeSound;
import simonsays.gameModel.Output;

/**
 *
 * @author Jaimes Booth 1305390
 * @version 12/05/14
 * @modified 14/05/14
 *  Basic four coloured button GUI.
 *  Added mouse click event listeners on buttons to play tones and add to input
 *  list.
 * @modified 18/05/14 Jaimes
 *  Added start and exit to menu bar
 */
public class SimonSaysGUI extends javax.swing.JFrame implements GameEventListener
{
    final Game game;
    final MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
    final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
    final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
    final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");
    private static SimonSaysGUI gui;
    Thread outputThread;

    /**
     * Creates new form SimonSaysGUINoImage
     */
    private SimonSaysGUI(Game game)
    {
        this.game = game;
        game.addGameEventListener(this);
        initComponents();   
        //update();
    }
    
    public static synchronized SimonSaysGUI getSingletonSimonSaysGUI(Game game)
    {
        if(gui == null)
        {
            gui = new SimonSaysGUI(game);
        }
        return gui;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
    
    public void update()
    {

        
    }
    
    /**
     * Iterates through the output list and plays the associated tones,
     * flashing the associated buttons.
     * @param outputList The list of integers representing output
     */
    public void buttonOutput(List<Integer> outputList)
    {
        for (int element = 0; element < outputList.size(); element++)
        {
            
            if(outputList.get(element).equals(1))
            {            
                jBtnGreen.setBackground(Color.WHITE);
                //update();
                toneC.playNPause();
                jBtnGreen.setBackground(Color.GREEN);         
            }
            else if(outputList.get(element).equals(3))
            {
                jBtnRed.setBackground(Color.WHITE);  
                //update();
                toneE.playNPause();
                jBtnRed.setBackground(Color.RED);
            }
            else if(outputList.get(element).equals(5))
            {
                jBtnBlue.setBackground(Color.WHITE);
                //update();
                toneG.playNPause();
                jBtnBlue.setBackground(Color.BLUE);
            }   
            else if(outputList.get(element).equals(7))
            {
                jBtnYellow.setBackground(Color.WHITE);
                //update();
                toneB.playNPause();
                jBtnYellow.setBackground(Color.YELLOW);
            }  
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlWindow = new javax.swing.JPanel();
        jPnlButtons = new javax.swing.JPanel();
        jBtnGreen = new javax.swing.JButton();
        jBtnRed = new javax.swing.JButton();
        jBtnBlue = new javax.swing.JButton();
        jBtnYellow = new javax.swing.JButton();
        jPnlCentre = new javax.swing.JPanel();
        jBtnStart = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemStart = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon Says");
        setPreferredSize(new java.awt.Dimension(800, 800));

        jpnlWindow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnlButtons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnGreen.setBackground(java.awt.Color.green);
        jBtnGreen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnGreenClicked(evt);
            }
        });
        jPnlButtons.add(jBtnGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 11, 200, 200));

        jBtnRed.setBackground(java.awt.Color.red);
        jBtnRed.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnRedClicked(evt);
            }
        });
        jPnlButtons.add(jBtnRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 229, 200, 213));

        jBtnBlue.setBackground(java.awt.Color.blue);
        jBtnBlue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnBlue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBlueClicked(evt);
            }
        });
        jPnlButtons.add(jBtnBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 460, 200, 200));

        jBtnYellow.setBackground(java.awt.Color.yellow);
        jBtnYellow.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnYellow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnYellowClicked(evt);
            }
        });
        jPnlButtons.add(jBtnYellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 229, 200, 213));

        jPnlCentre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnStart.setText("Start");
        jBtnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnStartMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPnlCentreLayout = new javax.swing.GroupLayout(jPnlCentre);
        jPnlCentre.setLayout(jPnlCentreLayout);
        jPnlCentreLayout.setHorizontalGroup(
            jPnlCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlCentreLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jBtnStart)
                .addGap(66, 66, 66))
        );
        jPnlCentreLayout.setVerticalGroup(
            jPnlCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCentreLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jBtnStart)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnlButtonsLayout = new javax.swing.GroupLayout(jPnlButtons);
        jPnlButtons.setLayout(jPnlButtonsLayout);
        jPnlButtonsLayout.setHorizontalGroup(
            jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPnlCentre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnGreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jBtnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlButtonsLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jBtnRed, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlButtonsLayout.setVerticalGroup(
            jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnYellow, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnRed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPnlCentre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jBtnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPnlButtons.add(jPnlCentre, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 229, 200, 213));

        jpnlWindow.add(jPnlButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 660, 670));

        jMenu1.setText("File");

        jMenuItemStart.setText("Start");
        jMenuItemStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStartActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemStart);
        jMenu1.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Plays the associated tone when the green button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnGreenClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnGreenClicked
    {//GEN-HEADEREND:event_jBtnGreenClicked
        // When green button clicked, play the related sound
        
        toneC.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(1);
            if(game.compareListSize())
            {
                game.compareInOutput();
            }
        }
    }//GEN-LAST:event_jBtnGreenClicked

    /**
     * Plays the associated tone when the red button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnRedClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnRedClicked
    {//GEN-HEADEREND:event_jBtnRedClicked
        // When red button clicked, play the related sound
        
        toneE.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(3);
            if(game.compareListSize())
            {
                game.compareInOutput();
            }
        }
    }//GEN-LAST:event_jBtnRedClicked

    /**
     * Plays the associated tone when the blue button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnBlueClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnBlueClicked
    {//GEN-HEADEREND:event_jBtnBlueClicked
        // When blue button clicked, play the related sound
        
        toneG.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(5);
            if(game.compareListSize())
            {
                game.compareInOutput();
            }
        }
    }//GEN-LAST:event_jBtnBlueClicked

    /**
     * Plays the associated tone when the yellow button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnYellowClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnYellowClicked
    {//GEN-HEADEREND:event_jBtnYellowClicked
        // When yellow button clicked, play the related sound
        
        toneB.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(7);
            if(game.compareListSize())
            {
                game.compareInOutput();
            }
                
        }
    }//GEN-LAST:event_jBtnYellowClicked

    
    private void jBtnStartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnStartMousePressed
        this.outputThread = new Thread(new Runnable() 
        {

            @Override
            public void run() 
            {
                    game.startGame();       
            }
        });
        outputThread.start();  

    }//GEN-LAST:event_jBtnStartMousePressed
    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemExitActionPerformed
    {//GEN-HEADEREND:event_jMenuItemExitActionPerformed
        // Exit when exit selected from menubar
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemStartActionPerformed
    {//GEN-HEADEREND:event_jMenuItemStartActionPerformed
        //Set state to playing, starting an instance of the game
        game.setState(GameState.PLAYING);
        gameHasChanged();
    }//GEN-LAST:event_jMenuItemStartActionPerformed

    
    
    
    // Handled in SimonSays Main()
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[])
//    {
//        /* Set the Nimbus look and feel */
//        <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SimonSaysGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SimonSaysGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SimonSaysGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SimonSaysGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        </editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//                new SimonSaysGUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBlue;
    private javax.swing.JButton jBtnGreen;
    private javax.swing.JButton jBtnRed;
    private javax.swing.JButton jBtnStart;
    private javax.swing.JButton jBtnYellow;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemStart;
    private javax.swing.JPanel jPnlButtons;
    private javax.swing.JPanel jPnlCentre;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel jpnlWindow;
    // End of variables declaration//GEN-END:variables

    @Override
    public void gameHasChanged() {
        //update();
        //Checks if the game is currently lost
        if(game.getState()==GameState.STARTED)
        {
            game.startGame();       
        }
        if(game.getState()==GameState.PLAYING)
        {
            this.outputThread = new Thread(new Runnable() 
            {

                @Override
                public void run() 
                {
                        
                        game.playGame();       
                }
            });
            outputThread.start(); 
        }
        if (game.getState()==GameState.GAMEOVER)
        {
            JOptionPane.showMessageDialog(null, "You have lost!", "Game Over!",JOptionPane.ERROR_MESSAGE);
            game.setState(GameState.STARTED);
        }
        if(game.getState()==GameState.WON)
        {           
            game.setState(GameState.PLAYING);
            gameHasChanged();
        }
    }
    
}
