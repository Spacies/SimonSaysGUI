
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
        output = game.getOutput();
        input = game.getInput();
        
    }
    
    @After
    public void tearDown()
    {
        
        game = null;
        output = null;
        input = null;
        
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

//    /**
//     * Test of startGame method, of class Game.
//     */
//    @Test
//    public void testStartGame()
//    {
//        System.out.println("startGame");
//        Game instance = null;
//        instance.startGame();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of playGame method, of class Game.
//     */
//    @Test
//    public void testPlayGame()
//    {
//        System.out.println("playGame");
//        Game instance = null;
//        instance.playGame();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }



//    /**
//     * Test of getScore method when score is greater than 0.
//     */
//    @Test
//    public void testGetScorePositive()
//    {
//        System.out.println("getScore");
//        
//        game.startGame();
//        
//        // Adds to output list via produceOutput()
//        game.playGame();
//        
////        game.getOutput().getOutputList().add(1);
////        game.getInput().getInputList().add(1);
//        output.getOutputList().add(1);
//        input.getInputList().add(1);
//
//        //Game instance = game;
//        
//        int expResult = 1;
//        int result = game.getScore();
//        
//        assertEquals(expResult, result);
//
//    }
    
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
        
        int expResult = 0;
        int result = game.getScore();
        
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

        GameState expResult = GameState.PLAYING;
        
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
        Game instance = game;

        Input result = instance.getInput();
        assertNotNull(result);

    }

    /**
     * Test of getOutput method, of class Game.
     */
    @Test
    public void testGetOutput()
    {
        System.out.println("getOutput");
        Game instance = game;

        Output result = instance.getOutput();
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
        boolean expResult = false;
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
