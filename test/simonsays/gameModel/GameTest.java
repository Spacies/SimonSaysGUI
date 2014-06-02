
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
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getGameObject method, of class Game.
     */
    @Test
    public void testGetGameObject()
    {
        System.out.println("getGameObject");
        Game expResult = null;
        Game result = Game.getGameObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Game.
     */
    @Test
    public void testClone() throws Exception
    {
        System.out.println("clone");
        Game instance = null;
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGame()
    {
        System.out.println("startGame");
        Game instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playGame method, of class Game.
     */
    @Test
    public void testPlayGame()
    {
        System.out.println("playGame");
        Game instance = null;
        instance.playGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareInOutput method, of class Game.
     */
    @Test
    public void testCompareInOutput()
    {
        System.out.println("compareInOutput");
        Game instance = null;
        instance.compareInOutput();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore()
    {
        System.out.println("getScore");
        Game instance = null;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGameEventListener method, of class Game.
     */
    @Test
    public void testAddGameEventListener()
    {
        System.out.println("addGameEventListener");
        GameEventListener listener = null;
        Game instance = null;
        instance.addGameEventListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeGameEventListener method, of class Game.
     */
    @Test
    public void testRemoveGameEventListener()
    {
        System.out.println("removeGameEventListener");
        GameEventListener listener = null;
        Game instance = null;
        instance.removeGameEventListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState()
    {
        System.out.println("getState");
        Game instance = null;
        GameState expResult = null;
        GameState result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Game.
     */
    @Test
    public void testSetState()
    {
        System.out.println("setState");
        GameState state = null;
        Game instance = null;
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDifficulty method, of class Game.
     */
    @Test
    public void testSetDifficulty()
    {
        System.out.println("setDifficulty");
        Difficulty difficulty = null;
        Game instance = null;
        instance.setDifficulty(difficulty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInput method, of class Game.
     */
    @Test
    public void testGetInput()
    {
        System.out.println("getInput");
        Game instance = null;
        Input expResult = null;
        Input result = instance.getInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class Game.
     */
    @Test
    public void testGetOutput()
    {
        System.out.println("getOutput");
        Game instance = null;
        Output expResult = null;
        Output result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareListSize method, of class Game.
     */
    @Test
    public void testCompareListSize()
    {
        System.out.println("compareListSize");
        Game instance = null;
        boolean expResult = false;
        boolean result = instance.compareListSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighscore method, of class Game.
     */
    @Test
    public void testGetHighscore()
    {
        System.out.println("getHighscore");
        Game instance = null;
        Highscore expResult = null;
        Highscore result = instance.getHighscore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
