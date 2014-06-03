package simonsays.gameModel;

import java.applet.*;
import java.net.*;

/**
 * Represents a sound audioClip object.
 * 
 * @AUTHOR Jaimes Booth & Sam McGarvey 
 * @version 16-03-14
 * 
 * Code derived from
 * http://www.dreamincode.net/forums/topic/14083-incredibly-easy-way-to-play-sounds/
 * http://stackoverflow.com/questions/557903/how-can-i-wait-for-a-java-sound-clip-to-finish-playing-back
 * https://katie.mtech.edu/classes/csci136/assign/dungeon/student/AudioFile.java
 */
public class MakeSound // Holds one audio file
{
    private URL audioPath; // Sound path
    private AudioClip audioClip; // Sound player
    
    /**
     * Creates an audioClip sound object.
     * 
     * @param filename The filename path of the audio to create an object from.
     */
    public MakeSound(String filename)
    {
        try
        {

            // Get the path to the soundfile
            audioPath = new URL(filename);
            
            // Load the Sound as an AudioClip i.e. load to memory.
            audioClip = Applet.newAudioClip(audioPath);
            
        }
        catch (MalformedURLException murle) 
        {
            
            System.out.println(murle);
            
        } 

    }
    
    /**
    * Plays the sound object once through.
    */
    public void playSound()
    {
        //System.out.println("playSoundOnce method reached");
        audioClip.play(); // Play only once
    }
    
    /**
    * 
    * Plays the sound object once through and pauses for 1500ms
    * allowing a 1000ms sound to finish playing before the program
    * progresses.
     * @param timeMultiplier
    */
    public void playNPause(int timeMultiplier)
    {
        int timeToSleep = 1200;
        audioClip.play(); // Play only once
        
        if(timeMultiplier < 7)
        {
            timeMultiplier *= 100;
            timeToSleep -= timeMultiplier;
        }
        else
        {
            timeToSleep = 600;      
        }        
        //Pause so sounds can play consecutively
        try 
        {
            Thread.sleep(timeToSleep);
            audioClip.stop();
            Thread.sleep(100);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
      
    }

    /**
     * Plays the sound object in a loop.
     */
    public void playSoundLoop()
    {
        //System.out.println("PlaySoundLoop method reached");
        audioClip.loop(); // Play
    }

    /**
     * Stops playback of the sound object.
     */
    public void stopSound()
    {
        audioClip.stop(); // Stop
    }
    
}
