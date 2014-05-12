package simonsays.gameModel;

/**
 * Creates a set of gameTone objects to use in the SimonSays game.
 * 
 * Use this in the future to avoid code repetition in Input and Output classes.
 * Perhaps should be enum to associate tone, randomInt and keyPress?
 * 
 * @author Jaimes Booth & Sam McGarvey
 * @version 23/03/14
 */
public class GameTones
{
    // The four game tones
    final MakeSound toneC = new MakeSound("file:C4_262Hz_1S.wav");
    final MakeSound toneE = new MakeSound("file:E4_330Hz_1S.wav");
    final MakeSound toneG = new MakeSound("file:G4_392Hz_1S.wav");
    final MakeSound toneB = new MakeSound("file:B3_247Hz_1S.wav");
    
    public void GameTones()
    {
        
    }
    
    public void generateTone(MakeSound toneName)
    {
        
    }
     
}
