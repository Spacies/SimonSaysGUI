
package simonsays.gui;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import simonsays.gameModel.Difficulty;
import simonsays.gameModel.Game;
import simonsays.gameModel.GameEventListener;
import simonsays.gameModel.GameState;
import simonsays.gameModel.MakeSound;

/**
 * Represents a Graphical User Interface for Simon Says.
 * @author Jaimes Booth 1305390 & Sam McGarvey
 * @version 12/05/14
 * @modified 14/05/14
 *  Basic four coloured button GUI.
 *  Added mouse click event listeners on buttons to play tones and add to input
 *  list.
 * @modified 18/05/14 Jaimes
 *  Added start and exit to menu bar, title to window frame.
 * @modified 22/05/14 Jaimes
 *  Added instruction dialogue
 * @modified 30/05/14 Jaimes
 *  Added highscore dialogue
 * @modified 02/06/14 Jaimes
 *  Set highscore dialogue text area read only
 * @modified 03/06/14 Jaimes
 *  Added exit confirmation dialogue: exitConfirmation().
 *  Position highscore input dialogue relative to GUI frame
 *  Display score based on getScore()
 *  Added slow down for button press display
 *  Countdown timer in display label
 * @modified 04/06/14 Jaimes
 *  Moved input / output comparison from button events to Input addInput() 
 */
public class SimonSaysGUI extends javax.swing.JFrame implements GameEventListener
{
    final Game game;
    final MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
    final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
    final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
    final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");
    private static SimonSaysGUI gui; // static to enforce singleton
    Thread outputThread;
    String highscoreHandle = "";
    
    // Custom button colours
    final Color green = new Color(80, 190, 100);
    final Color red = new Color(190, 30, 30);
    final Color blue = new Color(60, 180, 255);
    final Color yellow = new Color(230, 240, 0);
    
    // Custom bright button colours, for use when button is selected.
    final Color greenBright = new Color(160, 255, 200);
    final Color redBright = new Color(255, 60, 60);
    final Color blueBright = new Color(120, 255, 255);
    final Color yellowBright = new Color(255, 255, 0);
    
    
    /**
     * Creates new SimonSaysGUINoImage GUI frame. Only one instance of this 
     * object can exist at one time (singleton).
     */
    private SimonSaysGUI(Game game)
    {
        this.game = game;
        
        // Add this gui as a listener for game
        game.addGameEventListener(this);
        
        // Build the GUI components
        initComponents();   
        //update();

    }
    
    /**
     * Gets this GUI object related 
     * @param game The game object to use as a parameter if the gui is constructed.
     * @return This GUI object.
     */
    public static synchronized SimonSaysGUI getSingletonSimonSaysGUI(Game game)
    {
        if(gui == null)
        {
            gui = new SimonSaysGUI(game);
        }
        return gui;
    }
    
    /**
     * Disable this objects clone method so that it can be duplicated (Singleton)
     * @return Nothing returned as clone can not be created.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
    
    /**
     * Updates the GUI elements of the SimonSays Game.
     */
    public void update()
    {
        //jBtnGreen.setBackground(Color.GREEN);
        jBtnGreen.setBackground(green);    
        jBtnRed.setBackground(red);
        jBtnBlue.setBackground(blue);
        jBtnYellow.setBackground(yellow);
        //Sets start button to enabled by default
        jBtnStart.setEnabled(true);
        //Sets start button to disabled if game is in any state except "Started"
        if(game.getState()!=GameState.STARTED)
        {
            jBtnStart.setEnabled(false);
        }
        if((game.getState()==GameState.PLAYING)&&(game.getOutput()!=null))
        {
            // Display current score in the Display label
            jlblDisplay.setText("" + game.getScore());
        }   
    }
    
    /**
     * Iterates through the output list and plays the associated tones,
     * flashing the associated buttons.
     * @param outputList The list of integers representing output
     */
    public void buttonOutput(List<Integer> outputList)
    {
        // Display a countdown in the display label
        displayCountdown();
        
        //Iterates through the output list passed as arguement
        for (int element = 0; element < outputList.size(); element++)
        {
            //Checks value at each index and produces appropriate tone/button flash
            update();
            Difficulty currentDifficulty = game.getDifficulty();
            
            if(outputList.get(element).equals(1))
            {            
                if(currentDifficulty != Difficulty.HARD)
                {
                    jBtnGreen.setBackground(greenBright);
                }
                toneC.playNPause(outputList.size(),currentDifficulty);
                //jBtnGreen.setBackground(green);         
            }
            else if(outputList.get(element).equals(3))
            {
                if(currentDifficulty != Difficulty.HARD)
                {
                    jBtnRed.setBackground(redBright); 
                }
                toneE.playNPause(outputList.size(),currentDifficulty);
                //jBtnRed.setBackground(red);
            }
            else if(outputList.get(element).equals(5))
            {
                if(currentDifficulty != Difficulty.HARD)
                {
                    jBtnBlue.setBackground(blueBright);
                }
                toneG.playNPause(outputList.size(),currentDifficulty);
                //jBtnBlue.setBackground(blue);
            }   
            else if(outputList.get(element).equals(7))
            {
                if(currentDifficulty != Difficulty.HARD)
                {
                    jBtnYellow.setBackground(yellowBright);
                }
                toneB.playNPause(outputList.size(),currentDifficulty);
                //jBtnYellow.setBackground(yellow);
            }  
            update();         
        }
    }
    
    /**
     * Prints a countdown to the display label.
     */
    private void displayCountdown()
    {
        String dot = "";
        
        //Iterates through a 4 second countdown
        for(int i=4; i>0 ; i--)
        {
            
            //Prints current iterator starting at 3, ending at 1
            jlblDisplay.setText(i+dot);
            
            dot += ".";
            
            //Pauses the program for a second before continuing
            try 
            {
                //Alternate code for sleeping thread. Intelligible time units
                TimeUnit.SECONDS.sleep(1);
            } 
            catch(InterruptedException ex) {
                //Handles any exceptions cause by interrupting the thread above
                Thread.currentThread().interrupt();
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

        jDialogSettings = new javax.swing.JDialog();
        jPanelSettings = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonEasy = new javax.swing.JRadioButton();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonHard = new javax.swing.JRadioButton();
        buttonGroupDifficulty = new javax.swing.ButtonGroup();
        jDialogHighScoreEntry = new javax.swing.JDialog();
        jPanelHighScore = new javax.swing.JPanel();
        jLabelHighscoreQuery = new javax.swing.JLabel();
        jPanelHighscoreContainer = new javax.swing.JPanel();
        jTextHighscoreHandle = new javax.swing.JTextField();
        jButtonHighscoreSubmit = new javax.swing.JButton();
        jpnlWindow = new javax.swing.JPanel();
        jPnlButtons = new javax.swing.JPanel();
        jBtnGreen = new javax.swing.JButton();
        jBtnRed = new javax.swing.JButton();
        jBtnBlue = new javax.swing.JButton();
        jBtnYellow = new javax.swing.JButton();
        jPnlCentre = new javax.swing.JPanel();
        jBtnStart = new javax.swing.JButton();
        jlblDisplay = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemStart = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSettings = new javax.swing.JMenuItem();
        jMenuItemHighscores = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMnuInstructions = new javax.swing.JMenuItem();

        jDialogSettings.setMaximumSize(new java.awt.Dimension(300, 200));
        jDialogSettings.setModal(true);
        jDialogSettings.setPreferredSize(new java.awt.Dimension(275, 195));
        jDialogSettings.pack();

        jPanelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings:"));
        jPanelSettings.setAlignmentX(200.0F);
        jPanelSettings.setAlignmentY(200.0F);
        jPanelSettings.setMaximumSize(new java.awt.Dimension(350, 300));
        jPanelSettings.setMinimumSize(new java.awt.Dimension(250, 75));
        jPanelSettings.setPreferredSize(new java.awt.Dimension(295, 90));

        jLabel1.setText("Difficulty:");

        jRadioButtonEasy.setText("Easy");
        jRadioButtonEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEasyActionPerformed(evt);
            }
        });

        jRadioButtonNormal.setSelected(true);
        jRadioButtonNormal.setText("Normal");
        jRadioButtonNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNormalActionPerformed(evt);
            }
        });

        jRadioButtonHard.setText("Hard");
        jRadioButtonHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSettingsLayout = new javax.swing.GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelSettingsLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jRadioButtonEasy)))
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonNormal)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonHard)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanelSettingsLayout.setVerticalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonEasy)
                    .addComponent(jRadioButtonHard)
                    .addComponent(jRadioButtonNormal))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jDialogSettings.getContentPane().add(jPanelSettings, java.awt.BorderLayout.CENTER);

        jDialogSettings.getAccessibleContext().setAccessibleName("Settings");

        buttonGroupDifficulty.add(jRadioButtonEasy);
        buttonGroupDifficulty.add(jRadioButtonNormal);
        buttonGroupDifficulty.add(jRadioButtonHard);

        jDialogHighScoreEntry.setMaximumSize(new java.awt.Dimension(290, 175));
        jDialogHighScoreEntry.setMinimumSize(new java.awt.Dimension(285, 171));
        jDialogHighScoreEntry.setModal(true);
        jDialogHighScoreEntry.setResizable(false);
        jDialogHighScoreEntry.pack();

        jPanelHighScore.setBorder(javax.swing.BorderFactory.createTitledBorder("Highscore"));
        jPanelHighScore.setMaximumSize(new java.awt.Dimension(287, 172));
        jPanelHighScore.setMinimumSize(new java.awt.Dimension(285, 170));
        jPanelHighScore.setPreferredSize(new java.awt.Dimension(286, 171));

        jLabelHighscoreQuery.setText("Please enter a three character handle:");

        jPanelHighscoreContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextHighscoreHandle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextHighscoreHandle.setMinimumSize(new java.awt.Dimension(45, 20));
        jTextHighscoreHandle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextHighscoreHandleKeyPressed(evt);
            }
        });
        jPanelHighscoreContainer.add(jTextHighscoreHandle, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 0, 70, -1));

        jButtonHighscoreSubmit.setText("OK");
        jButtonHighscoreSubmit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHighscoreSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHighscoreSubmitActionPerformed(evt);
            }
        });
        jPanelHighscoreContainer.add(jButtonHighscoreSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 31, -1, -1));

        javax.swing.GroupLayout jPanelHighScoreLayout = new javax.swing.GroupLayout(jPanelHighScore);
        jPanelHighScore.setLayout(jPanelHighScoreLayout);
        jPanelHighScoreLayout.setHorizontalGroup(
            jPanelHighScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHighScoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHighScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHighscoreQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jPanelHighscoreContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelHighScoreLayout.setVerticalGroup(
            jPanelHighScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHighScoreLayout.createSequentialGroup()
                .addComponent(jLabelHighscoreQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelHighscoreContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );

        jDialogHighScoreEntry.getContentPane().add(jPanelHighScore, java.awt.BorderLayout.CENTER);

        jDialogHighScoreEntry.setLocationRelativeTo(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon Says");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnlWindow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnlButtons.setBackground(new java.awt.Color(36, 36, 36));
        jPnlButtons.setToolTipText("");
        jPnlButtons.setPreferredSize(new java.awt.Dimension(650, 650));

        jBtnGreen.setBackground(new java.awt.Color(80, 190, 100));
        jBtnGreen.setActionCommand("green");
        jBtnGreen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGreen.setOpaque(true);
        jBtnGreen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnGreenActionPerformed(evt);
            }
        });

        jBtnRed.setBackground(new java.awt.Color(190, 30, 30));
        jBtnRed.setActionCommand("red");
        jBtnRed.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRed.setOpaque(true);
        jBtnRed.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnRedActionPerformed(evt);
            }
        });

        jBtnBlue.setBackground(new java.awt.Color(60, 180, 255));
        jBtnBlue.setActionCommand("blue");
        jBtnBlue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnBlue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBlue.setOpaque(true);
        jBtnBlue.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnBlueActionPerformed(evt);
            }
        });

        jBtnYellow.setBackground(new java.awt.Color(230, 240, 0));
        jBtnYellow.setActionCommand("yellow");
        jBtnYellow.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnYellow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnYellow.setOpaque(true);
        jBtnYellow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnYellowActionPerformed(evt);
            }
        });

        jPnlCentre.setBackground(new java.awt.Color(190, 190, 190));
        jPnlCentre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPnlCentre.setPreferredSize(new java.awt.Dimension(200, 200));
        jPnlCentre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnStart.setText("Start");
        jBtnStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStartActionPerformed(evt);
            }
        });
        jPnlCentre.add(jBtnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jlblDisplay.setBackground(new java.awt.Color(153, 0, 0));
        jlblDisplay.setFont(jlblDisplay.getFont().deriveFont(jlblDisplay.getFont().getSize()+17f));
        jlblDisplay.setForeground(java.awt.Color.red);
        jlblDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblDisplay.setText("0");
        jlblDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jlblDisplay.setOpaque(true);
        jPnlCentre.add(jlblDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 9, 184, 100));

        javax.swing.GroupLayout jPnlButtonsLayout = new javax.swing.GroupLayout(jPnlButtons);
        jPnlButtons.setLayout(jPnlButtonsLayout);
        jPnlButtonsLayout.setHorizontalGroup(
            jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jBtnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jBtnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPnlCentre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jBtnRed, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jBtnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlButtonsLayout.setVerticalGroup(
            jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlButtonsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jBtnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPnlCentre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnRed, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBtnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnlWindow.add(jPnlButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 660));

        getContentPane().add(jpnlWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 666));

        jMenu1.setText("File");

        jMenuItemStart.setText("Start");
        jMenuItemStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStartActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemStart);
        jMenu1.add(jSeparator1);

        jMenuItemSettings.setText("Settings");
        jMenuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSettingsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSettings);

        jMenuItemHighscores.setText("Highscores");
        jMenuItemHighscores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHighscoresActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemHighscores);
        jMenu1.add(jSeparator2);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMnuInstructions.setText("Instructions");
        jMnuInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuInstructionsSelected(evt);
            }
        });
        jMenu2.add(jMnuInstructions);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Starts the exit confirmation process when exit is selected from the 
     * menu bar.
     * @param evt The "exit" selection event to listen for.
     */
    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemExitActionPerformed
    {//GEN-HEADEREND:event_jMenuItemExitActionPerformed
        // Bring up exit confirmation dialogue
        confirmExit();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    /**
     * Starts the game when start is selected from the menu.
     * @param evt The menu selection event to listen for.
     */
    private void jMenuItemStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemStartActionPerformed
    {//GEN-HEADEREND:event_jMenuItemStartActionPerformed
        //Starts game when button is pressed
        game.startGame();
    }//GEN-LAST:event_jMenuItemStartActionPerformed

    /**
     * Starts the game from the menu bar.
     * @param evt The menu item action to listen for
     */
    private void jBtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStartActionPerformed
        //Starts game when button is pressed
        game.startGame();
    }//GEN-LAST:event_jBtnStartActionPerformed

    /**
     * Plays the associated tone and adds the associated number to the input list.
     * @param evt The button action to listen for
     */
    private void jBtnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGreenActionPerformed
        // When green button clicked, play the related sound
        toneC.playSound();
        
        // Slow down button pressed display
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimonSaysGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(1);
        } 
        
    }//GEN-LAST:event_jBtnGreenActionPerformed

    /**
     * Plays the associated tone and adds the associated number to the input list.
     * @param evt The button action to listen for
     */
    private void jBtnYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnYellowActionPerformed
        // When yellow button clicked, play the related sound
        toneB.playSound();
        
        // Slow down button pressed display
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimonSaysGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(7);           
        }   
   
    }//GEN-LAST:event_jBtnYellowActionPerformed

    /**
     * Plays the associated tone and adds the associated number to the input list.
     * @param evt The button action to listen for
     */
    private void jBtnBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBlueActionPerformed
        // When blue button clicked, play the related sound
        
        toneG.playSound();
        
        // Slow down button pressed display
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimonSaysGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(5);
        }
    }//GEN-LAST:event_jBtnBlueActionPerformed

    /**
     * Plays the associated tone and adds the associated number to the input list.
     * @param evt The button action to listen for
     */
    private void jBtnRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRedActionPerformed
        // When red button clicked, play the related sound
        
        toneE.playSound();
        
        // Slow down button pressed display
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimonSaysGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // If the game is PLAYING register it as input
        if (game.getState() == GameState.PLAYING)
        {
            game.getInput().addInput(3);
        }
        

    }//GEN-LAST:event_jBtnRedActionPerformed

    /**
     * Displays the settings dialogue when selected from the menu.
     * @param evt The menu action to listen for
     */
    private void jMenuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSettingsActionPerformed
       if(game.getState()!=GameState.PLAYING)
       {
            displaySettings();
       }
    }//GEN-LAST:event_jMenuItemSettingsActionPerformed

    /**
     * Sets the difficulty to Easy.
     * @param evt The action event to listen for
     */
    private void jRadioButtonEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEasyActionPerformed
        game.setDifficulty(Difficulty.EASY);
    }//GEN-LAST:event_jRadioButtonEasyActionPerformed

    /**
     * Sets the difficulty to Normal.
     * @param evt The action event to listen for
     */
    private void jRadioButtonNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNormalActionPerformed
       game.setDifficulty(Difficulty.NORMAL);
    }//GEN-LAST:event_jRadioButtonNormalActionPerformed

    /**
     * Sets the difficulty to Hard.
     * @param evt The action event to listen for
     */
    private void jRadioButtonHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHardActionPerformed
        game.setDifficulty(Difficulty.HARD);
    }//GEN-LAST:event_jRadioButtonHardActionPerformed

    /**
     * Adds player's handle to the highscore table in Simon Says DB when ok is
     * pressed.
     * @param evt The action event to listen for
     */
    private void jButtonHighscoreSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHighscoreSubmitActionPerformed
        highscoreHandle = jTextHighscoreHandle.getText();
        game.getHighscore().insertHighscore(highscoreHandle, game.getScore());
        jDialogHighScoreEntry.setVisible(false);
    }//GEN-LAST:event_jButtonHighscoreSubmitActionPerformed

    /**
     * Makes sure the string entered as a handle is not longer than 3 characters.
     * @param evt The text entry event to listen for
     */
    private void jTextHighscoreHandleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextHighscoreHandleKeyPressed
        if(jTextHighscoreHandle.getText().length()>=3)
        {
            jTextHighscoreHandle.setText(jTextHighscoreHandle.getText().substring(0, 2));

        }             
    }//GEN-LAST:event_jTextHighscoreHandleKeyPressed

    /**
     * Displays the highscore dialog when selected from the menu.
     * @param evt The menu action to listen for
     */
    private void jMenuItemHighscoresActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemHighscoresActionPerformed
    {//GEN-HEADEREND:event_jMenuItemHighscoresActionPerformed
        // Display message dialogue with highscores
        displayHighscores();
    }//GEN-LAST:event_jMenuItemHighscoresActionPerformed
                                    

    /**
     * Displays the instructional dialogue when selected from the help menu.
     * @param evt The action event to listen for
     */
    private void jMnuInstructionsSelected(java.awt.event.ActionEvent evt)                                          
    {                                              

        // Display instructional dialogue
        displayInstructions();
    }                                         

    
    /**
     * Displays an instructional dialogue text.
     */
    public void displayInstructions()
    {
        
        String instructionText = ("Welcome to Simon Says! \n" +
                "A musical game of memory. \n" +
                "\n" +
                "Correctly reproduce the tone sequence. \n" +
                "Additional output is added until you make an error."
                );
        
        // Create instruction icon
        ImageIcon icon = createImageIcon("/simonsays/images/simon.jpg",
                "a vintage electronic game");
        
        //custom title, no icon
        JOptionPane.showMessageDialog(this,
            instructionText,
            "Instructions",
            JOptionPane.INFORMATION_MESSAGE,
            icon);

    }
    
    
    /** 
     * Returns an ImageIcon, or null if the path was invalid. 
     * @param path The path that contains the image file.
     * @param description The description of the image for the visually impaired.
     * http://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
     * @return The created image icon
     */
    protected ImageIcon createImageIcon(String path, String description) 
    {
        ImageIcon icon = null;
        
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) 
        {
            icon =  new ImageIcon(imgURL, description);
        } 
        else 
        {
            System.err.println("Couldn't find file: " + path);
        }
        
        return icon;
    }
    
    
    /**
     * Displays a highscore dialogue text.
     */
    public void displayHighscores()
    {
        
        // Get a String represenation of the highscore table
        String highscoreString = game.getHighscore().getHighscoreString();
        
        // Create highscore icon
        ImageIcon icon = createImageIcon("/simonsays/images/musicTrophy.jpg",
                "a highscore trophy");
        
        // Create text area for display in dialogue
        JTextArea highscoreTextArea = new JTextArea(highscoreString);
        
        // Set dialogue text area read only
        highscoreTextArea.setEditable(false);
        
        // Display highscore dialogue box
        // custom title, custom icon
        JOptionPane.showMessageDialog(this,
            highscoreTextArea,
            "Highscores",
            JOptionPane.INFORMATION_MESSAGE,
            icon);
        
    }
    
    
    /**
     * Displays confirmation dialogue before exiting the application. 
     */
    public void confirmExit()
    {
        
        Object[] options = {"Exit",
                    "Cancel"};
        int n = JOptionPane.showOptionDialog(this,
            "Are you sure you want to exit Simon Says?",
            "Exit Simon Says?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[1]); //default button title
        
        if (n == 0)
            // Exit when exit confirmed from dialog
            System.exit(0);
 
    }
    
    
    public void displaySettings()
    {
            jDialogSettings.setVisible(rootPaneCheckingEnabled);  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDifficulty;
    private javax.swing.JButton jBtnBlue;
    private javax.swing.JButton jBtnGreen;
    private javax.swing.JButton jBtnRed;
    private javax.swing.JButton jBtnStart;
    private javax.swing.JButton jBtnYellow;
    private javax.swing.JButton jButtonHighscoreSubmit;
    private javax.swing.JDialog jDialogHighScoreEntry;
    private javax.swing.JDialog jDialogSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelHighscoreQuery;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemHighscores;
    private javax.swing.JMenuItem jMenuItemSettings;
    private javax.swing.JMenuItem jMenuItemStart;
    private javax.swing.JMenuItem jMnuInstructions;
    private javax.swing.JPanel jPanelHighScore;
    private javax.swing.JPanel jPanelHighscoreContainer;
    private javax.swing.JPanel jPanelSettings;
    private javax.swing.JPanel jPnlButtons;
    private javax.swing.JPanel jPnlCentre;
    private javax.swing.JRadioButton jRadioButtonEasy;
    private javax.swing.JRadioButton jRadioButtonHard;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextField jTextHighscoreHandle;
    private javax.swing.JLabel jlblDisplay;
    private javax.swing.JPanel jpnlWindow;
    // End of variables declaration//GEN-END:variables

    /**
     * Adjusts the GUI in reaction to notification of changes in the model.
     */
    @Override
    public void gameHasChanged() {
        //update();
        //Checks if the game is in started state
        if(game.getState()==GameState.STARTED)
        {
            //If the game is started, then begin the game
            //jlblDisplay.setText("Current Score: 0");
            jlblDisplay.setText("0");
            //game.startGame();       
        }
        //Checks if game is in playing state
        if(game.getState()==GameState.PLAYING)
        {
            //Creates a seperate thread to run the game play on
            this.outputThread = new Thread(new Runnable() 
            {

                @Override
                public void run() 
                {
                        //Starts playing the game on the new thread
                        game.playGame();       
                }
            });
            //Starts the thread
            outputThread.start(); 
        }
        //Checks if the game is in game over state
        if (game.getState()==GameState.GAMEOVER)
        {
            //jlblDisplay.setText("CurrentScore: 0");
            jlblDisplay.setText("0");

            //Shows a temporary dialog box stating loss
            JOptionPane.showMessageDialog(this,
                    "You have lost!",
                    "Game Over!",
                    JOptionPane.ERROR_MESSAGE);
            //Puts the game back into the started state.
            int score = game.getScore();
            if(game.getHighscore().checkIfHighscore(score))
            {

                // Centre highscore entry dialogue window to application frame
                // Set here because setting in "Custom code" area does not have
                // desired effect
                jDialogHighScoreEntry.setLocationRelativeTo(this);
                
                jDialogHighScoreEntry.setVisible(rootPaneCheckingEnabled);
                
                //display highscore dialogue
                displayHighscores();
                
            }
            game.setState(GameState.STARTED);
            //gameHasChanged();
        }
        //Checks to see if the game is in won state
        if(game.getState()==GameState.WON)
        {                      
            //Sets the game state back to playing
            game.setState(GameState.PLAYING);
            //Notifies change listner that game is playing to resume the game
            gameHasChanged();
        }
        //Calls the update method to update the UI
        update();
    }
    
}
