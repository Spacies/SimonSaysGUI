package simonsays.gameModel;

import java.util.List;
import java.util.Scanner;
import simonsays.SimonSays;
import simonsays.gui.SimonSaysGUI;

/**
 * This is the class that knows the Simon Says game rules and enforces these
 * rules
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 17/03/2014
 * @modified 24/03/2014
 *  Modified game to use game states
 *  Added compareInOutput()
 * @modified 05/04/13 Jaimes
 *  Added calls to Highscore in Game() and hasLost()
 * @modified 08/04/13 Jaimes
 *  Added confirmation prompt when entering highscore in hasLost()
 *  Added confirmation prompt before exit
 * @modified 14/05/13 Jaimes
 *  Added getGameState()
 *  Call to GUI in Game()
 * 
 */
public class Game 
{    
    
    private GameState state;
    public Output output;
    private Input input;
    private int menuInput;
    private boolean firstRound = true;
    private Highscore highscore = new Highscore();
    private Difficulty difficulty = Difficulty.NORMAL;
    private SimonSaysGUI gui;
    
    
    /**
     * A new instance of Simon Says that begins producing output and 
     * accepting input
     */ 
    public Game() 
    {
        state = GameState.STARTED;
        output = new Output(difficulty);
    }    
    
    public void startGame()
    {
        if(state==GameState.STARTED)
        {
            state = GameState.PLAYING;
            //gui = SimonSays.getGUIInstance();
        }
        playGame();
        
    }
    
    private void playGame()
    {
        gui = SimonSaysGUI.getSingletonSimonSaysGUI(this);
        //Create an instance of the output and input 
        if(firstRound)
        {
            //Create a new output object during first round
            this.output = new Output(difficulty);  
            //Set first round to false so new output is not created again
            firstRound = false;
        }
        if(output.getOutputList().size()>3)
        {
            difficulty = Difficulty.HARD;
            output.setDifficulty(difficulty);
        }
        //Begins printing relevant output and playing corresponding tones
        output.produceOutput(gui);              
        //gui.buttonOutput(output.getOutputList());

        //Creates an input option passing the current output as a parameter
        this.input = new Input(output, difficulty);
        //Calculates whether the user has matched input or not
        //if(output.getOutputList().size()==input.getInputList().size())
        //{
        //    boolean inputCorrect = compareInOutput();
        //Conditional statement to be triggered once game is reported 
        //as being lost
        //    if(!inputCorrect)
                //Changes game state to gameover when game lost
        //        state=GameState.GAMEOVER;
        //}
    }
    
    /**
    * Compares the game's input with the game's output. If they don't
    * correlate, game ends.
    * @return listsMatch true if the lists match, otherwise false.
    */
    protected boolean compareInOutput()
    {
        
        // Get game's output list.
        List<Integer> outputList = output.getOutputList();
        // Get game's input list.
        List<Integer> inputList = input.getInputList();
        
        //Temporary statements for printing input and output as sense check
        //System.out.println("InputList = " + inputList);
        //System.out.println("OutputList = " + outputList);
        
        //Creates an initialises a boolean variable to compare output
        boolean listsMatch = true;

        // Compare input and output string
        for (int element = 0; element < outputList.size(); element++)
        {
            // If not the same then end the game.
            if (!(inputList.get(element).equals(outputList.get(element))))
            {
                listsMatch = false;               
            }
        }        
        return listsMatch;
    }  

    /**
     * Gets the current game state of this game object
     * @return state The game's current game state
     */
    public GameState getState()
    {
        return this.state;
    }

    /**
     * Gets this game's input object
     * @return input The game's input object
     */
    public Input getInput()
    {
        return this.input;
    }
    
    public Output getOutput()
    {
        return this.output;
    }
    
    
    
    
    
    
    /*
    private void runGame(){
        // Check highscore table exists
        if (!highscore.highscoreExists())
            // Create highscore table if it doesn't exist
            highscore.createHighscoreTable();
        
        while(state!=GameState.QUIT)
        {
            //Create a user interface object 
            UserInterface cui = new UserInterface();

            //This loop contains code for producing a CUI menu and handling user
            //selections. This loop continues until the state is explicitly changed 
            //to either 'PLAYING' or 'QUIT'.
            while(state==GameState.STARTED)
            {
                runMenu(cui);
            }
            //This loop continues producing output and receiving input until the
            //state is explicitly changed to 'GAMEOVER'.
            while(state==GameState.PLAYING)
            {
                playGame();
            }   
            //This loop will perform any operations required upon the game being 
            //lost
            while(state==GameState.GAMEOVER)
            {
                cui.printDivider();
                hasLost();

            }
        }
            
    }
    
    private void runMenu(UserInterface cui)
    {
        //Call cui method for generating menu
          menuInput = cui.generateMenu();
          //Perform relevant action upon user menu selection
          //1 - Start Game, 2 - Print instructions, 3 - Print settings,
          //4 - High scores, 5 - Exit game               
          if(menuInput == 1)
          {
              //Set state to playing, starting an instance of the game
              state=GameState.PLAYING;      
          }
          if(menuInput == 2)
          {
              //Print a divider and instructions placeholder
              cui.printDivider();
              cui.printInstructions();
          }
          if(menuInput == 3)
          {
              //Print a divider and settings placeholder
              cui.printDivider();
              difficulty = cui.changeSettings(difficulty);
          }
          if(menuInput == 4)
          {
              //Print a divider and a high scores place holder
              cui.printDivider();
              // If highscore table doesn't exist
              if (!highscore.highscoreExists())
              {
                  //System.out.println("Table doesn't exist");
                  //Create the highscore table
                  highscore.createHighscoreTable();
              }
              // Print the highscore table
              highscore.printHighscore();
          }
          if(menuInput == 5)
          {
              // Prompt to confirm player wants to quit
              System.out.println("Are you sure you want to exit the game?");
              System.out.println("Enter 5 to confirm exit, anything else to return to main menu.");
              System.out.print(">");
              Scanner inputScanner = new Scanner(System.in);
              String inputPrompt = inputScanner.next();
              // Check whether the player wants to quit
              if (!inputPrompt.equals("5"))
                  // Doesn't want to quit, return to main menu
                  state = GameState.STARTED;
              else
                  //Change state to quit, terminating the program
                  state=GameState.QUIT;

        }
    }
    

    
    public void hasLost()
    {      
        //A message to be printed to indicate the game was lost
        System.out.println("Sorry, you have lost the game!");
        //Sets variable to true so new output object is generated if user starts
        //another instance of the game from the menu
        firstRound = true;
        //This will change the game state so that the game returns to the menu
        //state=GameState.STARTED;
        
        // Check if the finalScore is a highscore
        // finalScore = the final output list length - 1 with 0 as a minimum.
        int finalScore;
        List<Integer> outputList = output.getOutputList();
        finalScore = outputList.size() - 1;
        if (finalScore < 0)
            finalScore = 0;
        
        // If finalScore is a highscore
        if (highscore.checkIfHighscore(finalScore))
        {
            
            boolean entryCancelled = false;
            
            // Get input
            Scanner scanner = new Scanner(System.in);
            
            // Prompt to confirm highscore entry
            System.out.println("");
            System.out.println("Congratulations, you have made the top ten!");
            System.out.println("Enter 5 to cancel to the main menu, anything else to continue entering your name.");
            // User input prompt
            System.out.print(">");
            
            String confirmHandleEntry = (String)scanner.next();

            // Check if player gas cancelled highscore entry
            if (confirmHandleEntry.equals("5"))
                // highscore entry cancelled
                entryCancelled = true;

            // Start with handle unconfirmed to trigger highscore entry prompt loop
            boolean handleConfirmed = false;
            // Start with an invalid string to trigger the handle entry prompt loop.
            String handle = "Invalid";

            // Repeat while handle is unconfirmed and entry has not been cancelled
            while (!handleConfirmed && !entryCancelled)
            {

                // Ask for handle

                // Check whether handle is three characters in length  
                // If not, prompt the user for input again.                
                while (handle.length() != 3)
                {

                    //Prompt for correct handle length
                    System.out.println("");
                    System.out.println("Please enter your Handle. Exactly three characters please.");
                    // User input prompt
                    System.out.print(">");

                    handle = scanner.next();

                }

                //Confirm handle with player
                System.out.println("");
                System.out.println("Do you want to enter \"" + handle + "\" as your entry into the highscores?" );
                System.out.println("Please enter 1 to confirm, 5 to cancel to the main menu or anything else to re-enter your handle.");
                // User input prompt
                System.out.print(">");
                
                confirmHandleEntry = (String)scanner.next();

                // Check if user has confirmed entry, canceled entry
                // or wishes to re-enter handle.
                if (confirmHandleEntry.equals("1"))
                    handleConfirmed = true;
                else if (confirmHandleEntry.equals("5"))
                {
                    entryCancelled = true;
                    //handleConfirmed = true;
                }
                else
                    // Re-enter handle
                    // Reset to trigger incorrect handle loop
                    handle = "invalid";

            } 

            // Check that highscore entry has npt been cancelled by player
            if (!entryCancelled)
            {
                // highscore entry confirmed
                // Handle confirmed.
                // Handle passed the 3 character check.
                // Add handle and score to the highscore table
                highscore.insertHighscore(handle, finalScore);
                System.out.println("");
                System.out.println(handle + ", you have been immortalized on the "
                        + "highscore table with a score of " + finalScore);
            }
        }
        state=GameState.STARTED;
    }
    */
    
  

    
    
}
