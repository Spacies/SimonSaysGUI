
package simonsays.gameModel;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Simon Says highscore database.
 * @author Jaimes Booth 1305390
 * @version 20/05/14
 */
public class HighscoreTest
{
    
    Highscore highscore;
    
    public HighscoreTest()
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
        highscore = new Highscore();
        
    }
    
    @After
    public void tearDown()
    {
        
        highscore = null;
    }

    /**
     * Test of highscoreExists method, of class Highscore.
     * Table should be created when 
     */
    @Test
    public void testHighscoreExists()
    {
        System.out.println("highscoreExists");

        boolean expectedResult = true;
        boolean result = highscore.highscoreExists();
        assertEquals(expectedResult, result);

    }

    /**
     * Test of createHighscoreTable method, of class Highscore.
     */
    @Test
    public void testCreateHighscoreTable()
    {
        System.out.println("createHighscoreTable");

        highscore.createHighscoreTable();
        
        boolean expectedResult = true;
        boolean result = highscore.highscoreExists();
        
        assertEquals(expectedResult, result);
    }

    /**
     * Test of getHighscoreResultSet method, of class Highscore.
     */
    @Test
    public void testGetHighscoreResultsetNotEmpty()
    {
        System.out.println("getHighscoreResultset");
        
        ResultSet expResult = null;
        ResultSet result = highscore.getHighscoreResultSet();
        
        assertNotNull(result);
    }

//    /**
//     * Test of printHighscore method, of class Highscore.
//     */
//    @Test
//    public void testPrintHighscoreProducesOutput()
//    {
//        System.out.println("printHighscore");
//
//        highscore.printHighscore();
//        
//        assertNotNull(highscore.printHighscore());
//
//    }

    /**
     * Test of checkIfHighscore method, of class Highscore.
     */
    @Test
    public void testCheckIfHighscoreIsAHighscore()
    {
        System.out.println("CheckIfHighscoreIsAHighscore");
        
        int newScore = 10;

        boolean expectedResult = true;
        boolean result = highscore.checkIfHighscore(newScore);
        
        assertEquals(expectedResult, result);

    }
    
    /**
     * Test of checkIfHighscore method, of class Highscore.
     */
    @Test
    public void testCheckIfZeroIsHighscore()
    {
        System.out.println("checkIfZeroIsHighscore");
        
        int newScore = 0;

        boolean expResult = false;
        boolean result = highscore.checkIfHighscore(newScore);
        
        assertEquals(expResult, result);

    }

//    /**
//     * Test of insertHighscore method, of class Highscore.
//     */
//    @Test
//    public void testInsertHighscore()
//    {
//        System.out.println("insertHighscore");
//        
//        String handle = "test";
//        int newScore = 1;
//
//        highscore.insertHighscore(handle, newScore);
//
//    }
    
}
