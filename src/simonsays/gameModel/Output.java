package simonsays.gameModel;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.LinkedList;

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
    private Difficulty difficulty;
    
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
    public Output(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
    public void produceOutput()
    {
        //Calling the outputCountDown method to check how it prints
         if(firstRound)
         {
             outputCountDown();
             firstRound = false;
         }

         // Generate a random int (to associate with a random tone)
         int randomInt = randomIntGenerator.generateRandomInt();

         // Add random int to outputList
         outputList.add(randomInt);


         // Play and print outputList
         // 
         for (int element = 0; element < outputList.size(); element++)
         {
             if (outputList.get(element).equals(1))
             {
                 if(difficulty!=Difficulty.HARD)
                 {
                    System.out.println("1");
                 }
                 toneC.playNPause();

             }
             else if (outputList.get(element).equals(3))
             {
                 if(difficulty!=Difficulty.HARD)
                 {                 
                    System.out.println("3");
                 }
                 toneE.playNPause();
             }
             else if (outputList.get(element).equals(5))
             {
                 if(difficulty!=Difficulty.HARD)
                 {                 
                    System.out.println("5");
                 }
                 toneG.playNPause();
             }
             else if (outputList.get(element).equals(7))
             {
                 if(difficulty!=Difficulty.HARD)
                 { 
                    System.out.println("7");
                }
                 toneB.playNPause();
             }
             if(difficulty!=Difficulty.EASY)
             {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); 
             }
         }
          
    }
    
    /**
     * This is a method to print a countdown before the program begins printing
     * the game output
     */
    
    private void outputCountDown()
    {
        //This prints 16 new line characters and puts cursor at bottom of console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); 
        //Print out countdown dialogue
        System.out.println("The game will begin in...");
        //Iterates through a 3 second countdown
        for(int i=3; i>0 ; i--)
        {
            //Prints current iterator starting at 3, ending at 1
            System.out.println(i+"...");
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
     * Gets the outputlist of integers.
     * 
     * @return List<Integer> The output List of integers.
     */
    public List<Integer> getOutputList()
    {
        
        return outputList;
        
    }
    
    /**
     * Plays a welcome sound tone sequence.
     */
    public void playWelcomeSound()
    {
        
        // Play a welcome sound sequence
        toneG.playNPause();
        toneE.playNPause();
        toneB.playNPause();
        toneC.playNPause();
        
    }
    
    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
}
