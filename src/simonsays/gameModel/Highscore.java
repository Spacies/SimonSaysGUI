
package simonsays.gameModel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generates and manipulates a database of highscores for SimonSays
 * @author Jaimes Booth 1305390 & Sam McGarvey
 * @version 01/05/14
 * @modified 05/05/14 Jaimes
 *  Debugged the methods to a working state.
 * @modified 07/06/14 Jaimes
 *  New score now inserts correctly into Highscore
 *  Prettied up the highscore print out
 */
public class Highscore
{
    
    Connection conn = null;
    String url = "jdbc:derby://localhost:1527/SimonSaysDB";  //url of the DB host
    String username = "simonsays";  //your DB username
    String password = "simonsays";   //your DB password

    
    /**
     * Constructor of the Highscore class. Connects to the SimonSays database.
     */
    public Highscore()
    {
        try
        {
            // Creates instance of a Connection object
            conn = DriverManager.getConnection(url, username, password);
            
            // Notify of DB connection
            //System.out.println(url + " connected...");

        }
        catch(SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    
    //Currently not used
//    /**
//     * Connects to the SimonSays database.
//     */
//    public void connectSimonSaysDB()
//    {
//        try
//        {
//            // Creates instance of a Connection object
//            conn = DriverManager.getConnection(url, username, password);
//            
//            // Notify of DB connection
//            System.out.println(url + " connected...");
//
//
//        }
//        catch(SQLException ex) 
//        {
//            System.err.println("SQLException: " + ex.getMessage());
//        }
//    }

    /**
     * Checks whether a highscore table exists
     * @return boolean True if highscore table exists, false otherwise.
     */
    public boolean highscoreExists()
    {
        boolean highscoreExists = false;
        
        //System.out.println("Checking if HS exists");
        
        try
        {

            // Create a variable for the table name
            String newTable = "Highscore";

            // Get the connection metadata
            DatabaseMetaData dbmd = conn.getMetaData();

            // Query the metadata for the table name highscore
            // enter result in resultSet.
            ResultSet rs = dbmd.getTables(null, null, newTable.toUpperCase(), null);
            
            // Check if the result set contains a reference to a (highscore) table
            if(rs.next())
            {
                // table exists
                highscoreExists = true;
            } 
            
            rs.close();
            
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
//        if (highscoreExists)
//            System.out.println("highscore table exists");
//        else
//            System.out.println("highscore table does not exist");
        
        return highscoreExists;
        
    }

    /**
     * Creates a new table called Highscore in SimonSaysDB and inserts 
     * 10 empty records into the table. 
     */
    public void createHighscoreTable()
    {
        
        //System.out.println("Reached creaeHighscoreTable");

        try
        {
            
            // links the statements to the specified database connection (conn)
            Statement statement = conn.createStatement();

            
            // Create a variable for the table name
            String newTable = "Highscore";


            // Better alternative code to check if table exists
            //http://stackoverflow.com/questions/5866154/how-to-create-table-if-it-doesnt-exist-using-derby-db

            // Get the connection metadata
            DatabaseMetaData dbmd = conn.getMetaData();

            // Retrieves a description of the tables available in the given catalog.
            // getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
            // http://docs.oracle.com/javase/7/docs/api/java/sql/DatabaseMetaData.html
            //ResultSet rs = dbmd.getTables(null, "MYSCHEMA", "MYTABLE", null);
            ResultSet rs = dbmd.getTables(null, null, newTable.toUpperCase(), null);
            
            // Check if the resultset does not contain a table with the specified name.
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html#next()
            if(!rs.next())
            {
                // Doesn't contain table so create one

                // Creates table with specified columns
                String sqlCreateTable = "CREATE TABLE " + newTable + 
                        " (Rank INT, Name VARCHAR(3), Score INT)";

                statement.executeUpdate(sqlCreateTable);

                //System.out.println("Highscore table created");

                String sqlInsert = "INSERT INTO " + newTable + " VALUES " +
                        "(1, 'SAM', 0), " + // 1
                        "(2, 'ACE', 0), " + // 2
                        "(3, 'SAM', 0), " + // 3
                        "(4, 'ACE', 0), " + // 4
                        "(5, 'SAM', 0), " + // 5
                        "(6, 'ACE', 0), " + // 6
                        "(7, 'SAM', 0), " + // 7
                        "(8, 'ACE', 0), " + // 8
                        "(9, 'SAM', 0), " + // 9
                        "(10, 'ACE', 0) ";  // 10
                

                statement.executeUpdate(sqlInsert);

                //System.out.println("Empty records inserted into Highscore table");
                
                rs.close();

            } 
            else
            {
                //System.out.println("table already exists");
            }
            
            //rs.close();
            
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    /**
     * Queries Name and Score from the Highscore table.
     * Holds the query result in a ResultSet object (rs), 
     * and returns the object. 
     * @return rs The result statement of the query
     */
    public ResultSet getHighscoreResultset()
    {

        ResultSet rs = null;
        
        try 
        {
            
            // Create a statement object to use on this connection
            // make it updatable.
            //http://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            
            String sqlQuery =
            "SELECT * " +
            "FROM HIGHSCORE ";
            
            rs = statement.executeQuery(sqlQuery);
            
            
            //System.out.println("Highscore resultSet created");
            
            // Don't want to close the result set as it is returned
            // to where this method was called from
            //rs.close();
            
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return(rs);

    }

    
    /**
     * Prints getHighscore() result set.
     */
    public void printHighscore()
    {
   
        try 
        {

            ResultSet highscoreResultSet = getHighscoreResultset();
            
            System.out.println("HIGHSCORES:");
            System.out.println("Rank    Name    Score");
            System.out.println("*********************");
            
            // While there are records in the result set of the table
            while( highscoreResultSet.next() )
            {
                // Get the row values by specifying the columns
                int rank = highscoreResultSet.getInt("Rank");
                String name = highscoreResultSet.getString("Name");
                int score = highscoreResultSet.getInt("Score");
                
                // Print out the highscores with tabs seperating values for 
                // alignment
                System.out.println(rank + "\t" + name + "\t" + score);
                
            }            
            
            highscoreResultSet.close();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Checks whether the specified score is a highscore
     * @param newScore The integer value of the score.
     * @return True if a highscore, false if not
     */
    public boolean checkIfHighscore(int newScore)
    {
        boolean highscore = false;
        
        ResultSet tenthScoreResultSet;
        
        try 
        {
            // Connect to DB
            //connectSimonSaysDB();
            
            Statement statement = conn.createStatement();
            
            String sqlQuery =
            "SELECT * " +
            "FROM Highscore " +
            "WHERE Rank = 10";
            
            tenthScoreResultSet = statement.executeQuery(sqlQuery);
            
            
            //System.out.println("TenthHighscore resultSet created");
            
            int tenthHighscore = 0;
            
            // Get the row values by specifying the columns
            if (tenthScoreResultSet.next())
                tenthHighscore =  tenthScoreResultSet.getInt("Score");
            
            //System.out.println("Do I get to here?");
        
            // is score greater than #10 then newscore is a highscore?
            if (newScore > tenthHighscore)
            {
                highscore = true;
            }
            
            //System.out.println("Got through checkIfHighscore()");
            tenthScoreResultSet.close();
            
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException in checkIfHighscore(): " + ex.getMessage());
        }
        
        
        return highscore;
    }
    

    /**
     * Inserts a specified new score into the appropriate
     * position in the highscore database, and displaces the
     * following values down the ranks.
     * 
     * @param handle The player's name abbreviated to three characters
     * @param newScore The value of the new score
     * 
     */
    public void insertHighscore(String handle, int newScore)
    {
        
        try 
        {

            /* Insert new highscore into the highscore table */
            
            // Get the highscore result set
            ResultSet highscoreRS = getHighscoreResultset();
            
            // Move the cursor to the insert row.
            // The insert row is a special 
            // row associated with an updatable result set. It is essentially 
            // a buffer where a new row can be constructed by calling the 
            // updater methods prior to inserting the row into the result set
            highscoreRS.moveToInsertRow();
            
            int newRank = 11; // Dummy rank for new highscore
            
            // Update the current row attributes
            highscoreRS.updateInt("Rank", newRank);
            highscoreRS.updateString("Name", handle);
            highscoreRS.updateInt("Score", newScore);

            // Insert the contents of the insert row into the database.
            highscoreRS.insertRow();
            
            //System.out.println("New score inserted");
            
            // Close the result set to prevent concurrence errors
            highscoreRS.close();
            
            
            /* Create temporary table to store the sorted top ten */

            // Create a variable for the table name
            String newTable = "TempTable";

            // Get the connection metadata
            DatabaseMetaData dbmd = conn.getMetaData();

            // Get a result set of the specified table from the 
            // database metadata
            ResultSet dbmdRS = dbmd.getTables(null, null, newTable.toUpperCase(), null);

            // Create a stament object associated with the database connection
            // to pass sql statements to and from the database
            //Statement statement = conn.createStatement();
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            
            // Check if the "tempTable" table exists
            if(!dbmdRS.next())
            {
                // Doesn't contain table so create one

                // Create a new table with the specified columns
                String sqlCreateTable = "CREATE TABLE " + newTable + 
                        " (Rank INT, Name VARCHAR(3), Score INT)";

                // Execute the SQL to the DB
                statement.executeUpdate(sqlCreateTable);

                //System.out.println("tempTable table created");
            }
            
            // Close the result set to prevent concurrence errors
            dbmdRS.close();
            
            
            /* Sort highscore table and copy the top ten scores to tempTable */
            
            // Sort table by score
            //System.out.println("Sorting table by score");
            String sqlQuery =   "SELECT * " +
                                "FROM Highscore " +
                                "ORDER BY Score DESC";

//            statement = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE, 
//                    ResultSet.CONCUR_UPDATABLE);
            
            // Get the result set of the sorted highscore table by executing
            // the SQL query.
            ResultSet highscoreSortedRS = statement.executeQuery(sqlQuery);
            
            //System.out.println("highscore table sorted");
            
            // Update row ranks to represent score ranks
            newRank = 1;
            
            // Create new statement for working with tempTable
            // while keeping old statement open (highscore result set)
            Statement tempTableStatement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);

            // While there are records in the result set of the sorted 
            // highscore table and the ten highscores have not been
            // copied to the temporary table
            while(highscoreSortedRS.next() && newRank < 11)
            {
                // Get this row's Name attribute from Highscore table
                String name = (String)highscoreSortedRS.getObject("Name");
                
                // Get this row's Score attribute from Highscore table
                int score = highscoreSortedRS.getInt("Score");
            
                // Copy this row from Highscore into tempTable
                // Replacing rank with current row iteration
                String sqlInsert = "INSERT INTO " + newTable + " VALUES " +
                        "(" + newRank + ", '" + name + "' ," + score + ")";
                
                // Execute the SQL Update to the DB 
                tempTableStatement.executeUpdate(sqlInsert);

                // Increase the row rank for next iteration
                //System.out.println("Rank " + newRank + " updated");
                ++newRank;
                
            }
            
            // Close the result set & statement to prevent concurrence errors
            highscoreSortedRS.close();
            tempTableStatement.close();
            
            
            /* Rename temporary table to Highscore */
            // Drop rows with ranks > 10 by replacing original highscore table
            // with temporary table
            //System.out.println("Deleting original Highscore Table");
            
            // Delete the Highscore table
            String sqlDrop =   "DROP Table Highscore ";
            
            // Execute the SQL to delete the Highscore table
            statement.executeUpdate(sqlDrop);
            //System.out.println("Highscore Table deleted");
            
            // Rename temporary table to Highscore, resurrecting the phoenix 
            // from the flames
            //System.out.println("Renaming tmp table");
            
            // Rename temp to Highscore
            String sqlUpdate =   "RENAME TABLE TempTable TO Highscore";
            
            // Execute the SQL to Rename the temp table
            statement.executeUpdate(sqlUpdate);
            
            //System.out.println("temp table renamed");   
            
            // Close the statement to prevent concurrence errors
            statement.close();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}


