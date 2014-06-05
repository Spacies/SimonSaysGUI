package simonsays.gameModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * @modified 05/04/14 Jaimes
 *  Added calls to Highscore in Game() and hasLost()
 * @modified 08/04/14 Jaimes
 *  Added confirmation prompt when entering highscore in hasLost()
 *  Added confirmation prompt before exit
 * @modified 14/05/14 Jaimes
 *  Added getGameState()
 *  Call to GUI in Game()
 * @modified 19/05/14 Jaimes
 *  Refactored Game class to a singleton pattern
 * 
 */
public class Game 
{    
    
    private GameState state;
    private Output output;
    private Input input;
    private int menuInput;
    private boolean firstRound;
    final Highscore highscore;
    private Difficulty difficulty = Difficulty.NORMAL;
    private SimonSaysGUI gui;
    private final Set<GameEventListener> eventListeners;
    private static Game game;
    
    
    /**
     * A new instance of Simon Says that begins producing output and 
     * accepting input. Only one instance of the game object can be
     * instantiated (singleton)
     */ 
    private Game() 
    {
        //this.highscore = new Highscore();
        highscore = Highscore.getHighscoreObject();

        //eventListeners = new HashSet<GameEventListener>();
        // Stores a list of game changes
        eventListeners = new HashSet<>();
        state = GameState.STARTED;

    }   
    
    
    /**
     * Gets the current Game object, creates one if it does not exist.
     * @return game This Game object
     */
    public static synchronized Game getGameObject()
    {
        
        if (game == null) 
        { 
            game = new Game(); 
        } 
        
        return game;
        
    }
    
    
    /**
     * Overide the clone method to prevent cloning of this singleton
     * object.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException 
    {
       throw new CloneNotSupportedException();
    }

    /**
     * Starts a game of Simon Says.
     */
    public void startGame()
    {
        // Check if highscore table exists
        if (!highscore.highscoreExists())
        {
            // Create highscore table if it doesn't exist
            highscore.createHighscoreTable();
        }
        // Check the game is in the started state
        if(state==GameState.STARTED)
        {
            
            state = GameState.PLAYING;
            output = null;
            firstRound = true;
            //gui = SimonSays.getGUIInstance();
        }  
        
        // Notify the GUI of changes
        notifyListenersOfGameChange();
        
    }
    
    
    /**
     * The rules of the game when playing.
     */
    public void playGame()
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
        // Change difficulty after 10 rounds
        if(output.getOutputList().size()>10)
        {
            difficulty = Difficulty.HARD;
            
        }
        output.setDifficulty(difficulty);
        //Begins printing relevant output and playing corresponding tones
        output.produceOutput(gui);              

        //Creates an input option passing the current output as a parameter
        this.input = new Input(output, difficulty);
    }
    
    /**
    * Compares the game's input with the game's output. If they don't
    * correlate, game ends.
    */
    public void compareInOutput()
    {
        
        // Check the input list against the output list (reduced to input size)
        for (int i = 0; i <= (input.getInputList().size() - 1); ++i )
        {

            // If the input and output do not match, then the game ends
            if (!(output.getOutputList().get(i).equals(input.getInputList().get(i))))
            {
                this.state = GameState.GAMEOVER;   
                firstRound = true; 
            }
        }
        
        notifyListenersOfGameChange();       
    } 
    

    /**
    * Calculates the game's score based on game's current output length. 
    */
    public int getScore()
    {
        
        int score;
        
        // Score = The final output list length - 1; 0 as a minimum.
        List<Integer> outputList = output.getOutputList();
        
        score = outputList.size() - 1;
        
        // Can not have score less than 0
        if (score < 0)
            score = 0;   
        
        //notifyListenersOfGameChange(); 
        
        return score;
           
    } 
    
     /**
     * Adds a listener for game change events.
     * @param listener the listener to add
     */
    public void addGameEventListener(GameEventListener listener)
    {
        eventListeners.add(listener);
    }

    /**
     * Removes a listener for game change events.
     * @param listener the listener to remove
     */
    public void removeGameEventListener(GameEventListener listener)
    {
        eventListeners.remove(listener);
    }

    /**
     * Notify all world change listeners.
     */
    private void notifyListenersOfGameChange()
    {
        for (GameEventListener listener : eventListeners) 
        {
            listener.gameHasChanged();
        }
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
     * Sets the games state.
     * @param state The state to change the game to.
     */
    public void setState(GameState state)
    {
        this.state = state;
    }
    
    /**
     * Sets the game state to the specified difficulty
     * @param difficulty The state to set the game's difficulty to.
     */
    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
        /**
     * Gets the game difficulty
     * @return The games difficulty
     */
    public Difficulty getDifficulty()
    {
        return this.difficulty;
    }
    
    /**
     * Gets this game's input object
     * @return input The game's input object
     */
    public Input getInput()
    {
        return this.input;
    }
    
    /**
     * Gets the game's output object.
     * @return This game's output object
     */
    public Output getOutput()
    {
        return this.output;
    }
    
    /**
     * Compares the size of the input and output list.
     * @return True if sizes are equal, false otherwise.
     */
    public boolean compareListSize()
    {
        return output.getOutputList().size()==input.getInputList().size();
    }
    
    /**
     * Gets the game's highscore object.
     * @return The game's highscore object.
     */
    public Highscore getHighscore()
    {
        return highscore;
    }
 
}
