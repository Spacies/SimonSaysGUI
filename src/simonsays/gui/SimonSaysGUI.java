
package simonsays.gui;

import simonsays.gameModel.Game;
import simonsays.gameModel.GameState;
import simonsays.gameModel.MakeSound;

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
public class SimonSaysGUI extends javax.swing.JFrame
{
    final Game game;

    /**
     * Creates new form SimonSaysGUINoImage
     */
    public SimonSaysGUI(Game game)
    {
        this.game = game;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jpnlWindow = new javax.swing.JPanel();
        jPnlButtons = new javax.swing.JPanel();
        jBtnGreen = new javax.swing.JButton();
        jBtnRed = new javax.swing.JButton();
        jBtnBlue = new javax.swing.JButton();
        jBtnYellow = new javax.swing.JButton();
        jPnlCentre = new javax.swing.JPanel();
        jlblFeedback = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemStart = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnGreen.setBackground(java.awt.Color.green);
        jBtnGreen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGreen.setIgnoreRepaint(true);
        jBtnGreen.setOpaque(true);
        jBtnGreen.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBtnGreenClicked(evt);
            }
        });

        jBtnRed.setBackground(java.awt.Color.red);
        jBtnRed.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRed.setIgnoreRepaint(true);
        jBtnRed.setOpaque(true);
        jBtnRed.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBtnRedClicked(evt);
            }
        });

        jBtnBlue.setBackground(java.awt.Color.blue);
        jBtnBlue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnBlue.setOpaque(true);
        jBtnBlue.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBtnBlueClicked(evt);
            }
        });

        jBtnYellow.setBackground(java.awt.Color.yellow);
        jBtnYellow.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnYellow.setOpaque(true);
        jBtnYellow.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBtnYellowClicked(evt);
            }
        });

        jPnlCentre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlblFeedback.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFeedback.setText("Feedback");

        javax.swing.GroupLayout jPnlCentreLayout = new javax.swing.GroupLayout(jPnlCentre);
        jPnlCentre.setLayout(jPnlCentreLayout);
        jPnlCentreLayout.setHorizontalGroup(
            jPnlCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCentreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlCentreLayout.setVerticalGroup(
            jPnlCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCentreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnRed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPnlCentre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jBtnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnlWindowLayout = new javax.swing.GroupLayout(jpnlWindow);
        jpnlWindow.setLayout(jpnlWindowLayout);
        jpnlWindowLayout.setHorizontalGroup(
            jpnlWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlWindowLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jPnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jpnlWindowLayout.setVerticalGroup(
            jpnlWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWindowLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        getContentPane().add(jpnlWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 780));

        jMenu1.setText("File");

        jMenuItemStart.setText("Start");
        jMenuItemStart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemStartActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemStart);
        jMenu1.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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
        MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
        toneC.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
            game.getInput().getInputList().add(1);
    }//GEN-LAST:event_jBtnGreenClicked

    /**
     * Plays the associated tone when the red button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnRedClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnRedClicked
    {//GEN-HEADEREND:event_jBtnRedClicked
        // When red button clicked, play the related sound
        final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
        toneE.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
            game.getInput().getInputList().add(3);
    }//GEN-LAST:event_jBtnRedClicked

    /**
     * Plays the associated tone when the blue button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnBlueClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnBlueClicked
    {//GEN-HEADEREND:event_jBtnBlueClicked
        // When blue button clicked, play the related sound
        final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
        toneG.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
            game.getInput().getInputList().add(5);
    }//GEN-LAST:event_jBtnBlueClicked

    /**
     * Plays the associated tone when the yellow button is pressed and adds the 
     * input to the input list if the game is playing.
     * @param evt The mouse click to listen for
     */
    private void jBtnYellowClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnYellowClicked
    {//GEN-HEADEREND:event_jBtnYellowClicked
        // When yellow button clicked, play the related sound
        final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");
        toneB.playSound();
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
            game.getInput().getInputList().add(7);
    }//GEN-LAST:event_jBtnYellowClicked

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemExitActionPerformed
    {//GEN-HEADEREND:event_jMenuItemExitActionPerformed
        // Exit when exit selected from menubar
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemStartActionPerformed
    {//GEN-HEADEREND:event_jMenuItemStartActionPerformed
        //Set state to playing, starting an instance of the game
        game.setState(GameState.PLAYING);
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
    private javax.swing.JButton jBtnYellow;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemStart;
    private javax.swing.JPanel jPnlButtons;
    private javax.swing.JPanel jPnlCentre;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel jlblFeedback;
    private javax.swing.JPanel jpnlWindow;
    // End of variables declaration//GEN-END:variables
}