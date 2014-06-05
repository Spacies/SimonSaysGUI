
package simonsays.gameModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaimes Booth 1305390
 */
public class GameTest
{
    
    Game game;
    Output output;
    Input input;
    
    
    public GameTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        
        game = Game.getGameObject();
        game.setOutput(new Output(Difficulty.EASY));
        game.setInput(new Input(output,Difficulty.EASY));
        game.setState(GameState.STARTED);
    }
    
    @After
    public void tearDown()
    {
        
        game.setOutput(null);
        game.setInput(null); 
        game = null;
   
    }

    /**
     * Test of getGameObject method, of class Game.
     */
    @Test
    public void testGetGameObject()
    {
        System.out.println("getGameObject");
        
        Game result = Game.getGameObject();
        
        assertNotNull(result);
    }

    /**
     * Test of clone method, of class Game.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        
        try 
        {
            game.clone();
            fail("Expected CloneNotSupportedException");
        }
        catch(CloneNotSupportedException e)   
        {

        }

    }

    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGame()
    {
        System.out.println("startGame");

        game.startGame();
        
        assertEquals(game.getState(), GameState.PLAYING);

    }

    
    /**
     * Test of getScore method when score is less than 0.
     */
    @Test
    public void testGetScoreNotNegative()
    {
        System.out.println("getScore when score is 0");
        
        game.startGame();
        // Adds to output list via produceOutput()
        game.playGame();
        
        //Game instance = game;
        
        boolean expResult = true;
        boolean result = game.getScore()>=0;
        
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState()
    {
        System.out.println("getState");
        
        Game instance = game;

        GameState expResult = GameState.STARTED;
        
        GameState result = instance.getState();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of setState method, of class Game.
     */
    @Test
    public void testSetState()
    {
        System.out.println("setState");
        
        game.setState(GameState.PLAYING);
        
        assertEquals(game.getState(), GameState.PLAYING);

    }

    /**
     * Test of setDifficulty method, of class Game.
     */
    @Test
    public void testSetDifficulty()
    {
        System.out.println("setDifficulty");
        
        
        Difficulty difficulty = Difficulty.NORMAL;
        
        Game instance = game;
        
        instance.setDifficulty(difficulty);
        
        assertEquals(game.getDifficulty(), difficulty);

    }

    /**
     * Test of getInput method, of class Game.
     */
    @Test
    public void testGetInput()
    {
        System.out.println("getInput");
        //Game instance = game;

        Input result = game.getInput();
        assertNotNull(result);

    }

    /**
     * Test of getOutput method, of class Game.
     */
    @Test
    public void testGetOutput()
    {
        System.out.println("getOutput");
        //Game instance = this.game;

        Output result = game.getOutput();
        assertNotNull(result);

    }

    /**
     * Test of compareListSize method, of class Game.
     */
    @Test
    public void testCompareListSize()
    {
        System.out.println("compareListSize");
        Game instance = game;
        boolean expResult = true;
        boolean result = instance.compareListSize();
        assertEquals(expResult, result);

    }

    /**
     * Test of getHighscore method, of class Game.
     */
    @Test
    public void testGetHighscore()
    {
        System.out.println("getHighscore");
        Game instance = game;

        Highscore result = instance.getHighscore();
        assertNotNull(result);

    }
    
}
