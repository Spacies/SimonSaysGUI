package simonsays.gameModel;

import java.util.Random;

/**
 * Creates a random tone to output to the Simon Says Game.
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 23/03/14
 * @modified 24/03/14
 *  generateRandomInt returns int values of 1, 3, 5, or 7.
 */
public class RandomInt
{
    
    /**
     * The RandomInt Constructor method.
     */
    public RandomInt()
    {
        
            
    }
    
    /**
     * Generates a random int corresponding to a tone (1, 3, 5 or 7).
     * 
     * @return int The random integer to return.
     */
    public int generateRandomInt()
    {
        // Create a random number generator
        Random randomGenerator = new Random();
        
        int iRandomInt = 0;
        
        // Repeat until a random 1, 3, 5 or 7 is acheived
        while(!(iRandomInt == 1 || iRandomInt == 3 || iRandomInt == 5 ||
                iRandomInt == 7))
        {
        
            // Generate one random integer between 1 and 7.
            // Add 1 to generated number because range min (0) is inclusive
            // while max (6) is exclusive.
            iRandomInt = randomGenerator.nextInt(7) + 1;
        
        }
        
        //System.out.println("Random int generated is: " + iRandomInt);
        
        return iRandomInt;
               
    }
       
}
