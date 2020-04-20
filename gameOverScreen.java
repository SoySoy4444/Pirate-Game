import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//File handling
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

//Date
import java.time.LocalDate; //For recent scores
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Write a description of class gameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gameOverScreen extends World
{
    private int score;
    private String messageText;
    private TextImage message;
    private String currentFormattedDate;
    private ArrayList<String> recentScores;

    /**
     * Constructor for objects of class gameOverScreen.
     * 
     */
    //mainScreen calls this constructor once game is over.
    //Greenfoot.setWorld(new gameOverScreen(score));
    public gameOverScreen(int score)
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        this.score = score;
        
        //Displaying game over message depending on score
        messageText = (score >= 10000) ? "Wow, you're a pirate!" : ( (score >= 5000) ? "Good job!" : "Still a baby pirate!");
        message = new TextImage(messageText, 50, Color.RED, Color.BLUE);
        addObject(message, getWidth()/2, 200);
        
        //Unformatted Date: 2020-04-12
        LocalDate currentUnformattedDate = LocalDate.now();
        //System.out.println(currentUnformattedDate);

        //Formatted Date: 12/04/2020
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        currentFormattedDate = currentUnformattedDate.format(myFormatObj);
        //System.out.println(currentFormattedDate);
        
        //Read all new scores into the recentScores arraylist.
        recentScores = new ArrayList<String>();
        try {
            File myObj = new File("recentscores.txt");
            Scanner fileReader = new Scanner(myObj);
            while (fileReader.hasNextLine()) { //add all lines into the recentScores array
                recentScores.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error finding recent scores! Check recentscore.txt exists.");
            e.printStackTrace();
        }
        
        //Add today's game stats to the file
        try {
            File fileObject = new File("recentscores.txt"); //Creates a new file if it doesn't exist
            appendFile("\nGame #" + (recentScores.size()+1) + ": " + score + "\t" + currentFormattedDate, fileObject);
        } catch (IOException e){ //IOException is a superclass of FilenNotFoundException, so this covers it too
            System.out.println("Error appending file! Check recentscores.txt exists and is writable.");
            e.printStackTrace();
        }
        
        //Read all new scores into the recentScores arraylist.
        ArrayList<String> recentScoresIncludingToday = new ArrayList<String>();
        try {
            File myObj = new File("recentscores.txt");
            Scanner fileReader = new Scanner(myObj);
            while (fileReader.hasNextLine()) { //add all lines into the recentScores array
                recentScoresIncludingToday.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error finding recent scores! Check recentscore.txt exists.");
            e.printStackTrace();
        }
        
        //display the 5 most recent scores onto the world
        int yLocation = 0;
        final int lineSpacing = 30;
        if(recentScoresIncludingToday.size() > 5)
        {
            for(int i = recentScoresIncludingToday.size()-1; i > recentScoresIncludingToday.size() - 6; i--) {
                String scoreText = recentScoresIncludingToday.get(i);
                TextImage scoreLine = new TextImage(scoreText, 20, Color.RED, Color.BLUE);
                addObject(scoreLine, getWidth()/2, 400 + (lineSpacing * yLocation));
                yLocation++;
            }
        } else {
            for(int i = recentScoresIncludingToday.size()-1; i >= 0; i--) {
                String scoreText = recentScoresIncludingToday.get(i);
                TextImage scoreLine = new TextImage(scoreText, 20, Color.RED, Color.BLUE);
                addObject(scoreLine, getWidth()/2, 400 + (lineSpacing * yLocation));
                yLocation++;
            }
        }
        
        //Since this game is over, all gameLogs can be deleted
        File savedGame = new File("saved_game.txt");
        if(savedGame.exists()) {
            savedGame.delete();
        }
    }
    
    private void appendFile(String s, File f) throws IOException {
        FileWriter myWriter = new FileWriter(f, true); //true argument indicates we are appending instead of overwriting
        myWriter.write(s);
        myWriter.close();
    }
}