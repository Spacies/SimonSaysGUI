package simonsays.gameModel;

import java.util.LinkedList;
import java.util.List;
import simonsays.gui.SimonSaysGUI;

/**
 * This is a class that handles the input and the various rules involved    
 * with accepting the input in the SimonSays game
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 22/03/2014
 * @modified 24/03/14
 *  Added getInput()
 * @modified 25/03/14
 *  Commented out handleInput()
 * @modified 05/05/14 Jaimes
 *  Added user prompt to receiveInput(), receiveInputTest()
 */
public class Input {
    // The input list of integers representing responses to the
    // game questions.
    private List<Integer> inputList;
    private Output output;
    private Difficulty difficulty; 
    private Game game;
    private SimonSaysGUI gui;
    
    // The four game tones
    final MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
    final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
    final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
    final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");    

    /**
     * Creates a Simon Says Input object based on specified output and difficulty.
     * @param output The output to associate with this input
     */
    public Input(Output output)
    {
        
        this.output = output;
        this.inputList = new LinkedList<>();
        //receiveInputTest();
        
        game = Game.getGameObject(); // Game is a singleton
        gui = SimonSaysGUI.getSingletonSimonSaysGUI(game);
        
    }
  
    
    /**
     * Gets the input list of integers.
     * 
     * @return List<Integer> The input List of integers.
     */
    public List<Integer> getInputList()
    {
        
        return inputList;
        
    }
    
    /**
     * Adds input to input list and then checks if input matches output.
     * @param input The integer representing an input to add to the list
     */
    public void addInput(int input)
    {
        // add input
        this.inputList.add(input);
        
        //If output list and input list have equal size compare lists
        if(game.compareListSize())
        {
            game.compareInOutput();
        }
            
    }
    
    /**
     * Checks if the specified input is correct.
     * @param input The specified input represented by an integer
     * @return inputMatch True if input matches output, false otherwise
     */
    public boolean inputCorrect(int input)
    {
        boolean inputMatch = false;
        
        // Check the input list against the output list (reduced to input size)
        for (int i = 0; i <= (inputList.size() - 1); ++i )
        {
            inputMatch = false;
            
            if (output.getOutputList().get(i).equals(inputList.get(i)))
                inputMatch = true;  
        }
        
        //System.out.println("inputMatch=" + inputMatch);
        return inputMatch;
    }
    
} 


