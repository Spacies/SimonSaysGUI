package simonsays.gameModel;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.LinkedList;
import simonsays.gui.SimonSaysGUI;

/**
 * This is a class that handles the output and the various rules involved
 * in producing appropriate output for the SimonSays game
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 20/03/14
 * 
 * @modified 25/03/14 Added random output generation and store to a linkedList
 *  in constructor.
 */
public class Output
{
    private boolean firstRound = true;
    private MakeSound keySound; // The sound made when a key is pressed.
    
    
    // The four game tones
    final MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
    final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
    final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
    final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");
    
    // The output list of integers representing tones to play as
    // game questions.
    private List<Integer> outputList = new LinkedList<>();
    
    // An object that randomly returns int values of 1, 3, 5 or 7 when called.
    private RandomInt randomIntGenerator = new RandomInt();
    
    /**
     * Creates output to the Simon Says game.
     */
    public Output()
    {
    }
    
    public void produceOutput(SimonSaysGUI gui)
    {
        //outputCountDown();
        // Generate a random int (to associate with a random tone)
        int randomInt = randomIntGenerator.generateRandomInt();
        // Add random int to outputList
        outputList.add(randomInt);
        gui.buttonOutput(outputList);
    }
    
    
    /**
     * Gets the outputlist of integers.
     * 
     * @return List<Integer> The output List of integers.
     */
    public List<Integer> getOutputList()
    {
        
        return outputList;
        
    }   
}
