package simonsays.gameModel;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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

    public Input(Output output, Difficulty difficulty)
    {
        
        this.output = output;
        this.difficulty = difficulty;
        this.inputList = new LinkedList<>();
        //receiveInputTest();
        
        game = Game.getGameObject(); // Game is a singleton
        gui = SimonSaysGUI.getSingletonSimonSaysGUI(game);
        
    }
 

//    /**
//     * An alternative input method that uses a scanner for input instead of a buffered reader.
//     */
//    private void receiveInputTest() 
//    {
//        //Declares and initialises a scanner
//        Scanner inputScanner = new Scanner(new InputStreamReader(System.in));
//        //Decclares and initalises a variable for storing input from scanner.
//        int input = 0;
//        //Prints statement requesting input.
//        System.out.println("Please repeat the output:");
//        // User input prompt
//        System.out.print(">");
//        //A loop conditional on user input being the same length as user output
//        while (inputList.size()!=output.getOutputList().size())
//        {         
//            //Checks if input is integer
//            if(inputScanner.hasNextInt())
//            {   
//                //Stores integer to input variable
//                input = inputScanner.nextInt();    
//            }
//            else
//            {
//                //Prints statement requesting valid input
//                System.out.println("Please enter a valid input");
//                //Moves scanner to next line to avoid infinite loop
//                inputScanner.nextLine();
//            }
//            // Check for press of button and play appropriate tone.
//            // Need to press enter to pass input.
//            // Add entered input to inputList.
//            if (input == 1)
//            {
//                toneC.playNPause();
//                inputList.add(1);
//                
//            }
//            else if (input == 3)
//            {
//                toneE.playNPause();
//                inputList.add(3);
//            }
//            else if (input == 5)
//            {
//                toneG.playNPause();
//                inputList.add(5);
//            }
//            else if (input == 7)
//            {
//                toneB.playNPause();
//                inputList.add(7);
//            }
//            else
//            {
//                 System.out.println("Please enter a valid input (1, 3, 5 or 7");               
//            }
//        }
//        //Temporary means of clearing screen within the netbeans IDE
//        if(input!=0)
//        {
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
//            + "\n\n\n");
//        }
//    }    
    
    /**
     * Gets the input list of integers.
     * 
     * @return List<Integer> The input List of integers.
     */
    public List<Integer> getInputList()
    {
        
        return inputList;
        
    }
    
    public void addInput(int input)
    {
        this.inputList.add(input);
    }
    
} 


