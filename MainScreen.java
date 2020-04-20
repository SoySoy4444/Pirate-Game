import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Write a description of class MainScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MainScreen extends World
{
    static final String validRows = "ABCDEFG";
    static final String validCols = "1234567";
    
    private Grid grid;
    private ArrayList<String> enteredCoordinates;
    
    //Messages
    private TextImage itemMessage; //"You got $300! You got backstab! etc
    private final TextImage gameSavedConfirmation = new TextImage("Your game has been successfully saved!", 40, Color.RED, Color.BLUE);
    private TextImage gameMessage; //used for anything, like "You blocked your enemy with a shield!"
    
    //game Variables
    private int cash;
    private boolean hasShield;
    private boolean hasMirror;
    private int bankAmount;
    
    private Items backstab = new Backstab();
    private Items bank = new Bank();
    private Items chooseNextSquare = new ChooseNextSquare();
    private Items doubleScore = new DoubleScore();
    private Items lostAtSea = new LostAtSea();
    private Items mirror = new Mirror();
    private Items present = new Present();
    private Items shield = new Shield();
    private Items rob = new Rob();
    private Items sinkShip = new SinkShip();
    private Items sneakPeek = new SneakPeek();
    private Items swapScore = new SwapScore();
    
    //Game icons displayed on the main screen
    private TextImage cashIcon;
    private TextImage bankIcon;

    //Hitlist
    public static final int DELAY_AMOUNT = 100;
    
    //Clickable Buttons
    private final TextImage enterCoordinateButton = new TextImage("Enter coordinate", 20, Color.RED, Color.BLUE);;
    private final TextImage saveGameButton = new TextImage("Save game", 20, Color.RED, Color.BLUE);
    //private TextImage hitListButton;

    private Shield shieldIcon;
    private Mirror mirrorIcon;
    
    //What Happened Screen
    private final TextImage whatHappenedButton = new TextImage("What happened?", 20, Color.RED, Color.BLUE); //clickable button
    private final TextImage exitWhatHappened = new TextImage("Exit", 20, Color.RED, Color.BLUE);
    private final Image whatHappened = new Image("whatHappenedBackground.png", 800, 600); //Black background actor
    private final Image robbed = new Image("robbed.png", 100, 100);
    private final Image backstabbed = new Image("backstabbed.png", 100, 100);
    private final Image wipedOut = new Image("wipedOut.png", 100, 100);
    private final Image swapped = new Image("swapped.png", 100, 100);
    private final Image gifted = new Image("gifted.png", 100, 100);

    /**
     * Constructor for objects of class MainScreen.
     * 
     */
    //if game is being continued from another game - called by TitleScreen
        
    public MainScreen(Grid grid, ArrayList enteredCoordinates, int cash, boolean hasShield, boolean hasMirror, int bankAmount, boolean loadingSavedGame)
    {
        super(800, 600, 1);
        
        this.grid = grid;
        this.enteredCoordinates = enteredCoordinates;
        this.cash = cash;
        this.hasShield = setShield(hasShield);
        this.hasMirror = setMirror(hasMirror);
        this.bankAmount = bankAmount;
        
        //Buttons
        addObject(enterCoordinateButton, 200, 200);
        addObject(saveGameButton, 50, 50);
        addObject(whatHappenedButton, getWidth()/2, 550);
        
        //Icons
        cashIcon = new TextImage("Cash: " + Integer.toString(cash), 20, Color.RED, Color.BLUE);
        addObject(cashIcon, 750, 20);
        
        addObject(grid, getWidth()/2, getHeight()/2);
        grid.printGrid();
        
        //if this game is building loaded from a saved game, it needs to delete the icons
        if(loadingSavedGame == true)
        {
            for(int i = 0; i < enteredCoordinates.size(); i++)
            {
                String coordinate = (String) enteredCoordinates.get(i);
                int row = grid.getRowFromAlpha(coordinate.charAt(0));
                int col = grid.getColFromNum(coordinate.charAt(1));
                removeObject(grid.getElement(row, col));
            }
            
            //Add bank
            bankIcon = new TextImage("Bank: " + Integer.toString(bankAmount), 20, Color.RED, Color.BLUE);
            addObject(bankIcon, 750, 50);
        }
    } 
    
    //if new game - called by SetupScreen
    public MainScreen(Grid grid)
    {    
        //Start a new game with a new grid, no cash, no bank, no shield, no mirror, no bank
        this(grid, new ArrayList<>(), 0, false, false, 0, false);
    }
    
    public void act()
    {
        if(enterCoordinateButton.isClicked() || Greenfoot.isKeyDown("space"))
        {    
            String coordinate;
            enterCoordinateButton.setClicked(false);
            
            //input validation
            do {
                coordinate = Greenfoot.ask("What did your teacher call out? ");
            } while(coordinate.length() != 2 || validRows.indexOf(coordinate.charAt(0)) == -1 || validCols.indexOf(coordinate.charAt(1)) == -1 || enteredCoordinates.contains(coordinate) == true);
            int row = grid.getRowFromAlpha(coordinate.charAt(0));
            int col = grid.getColFromNum(coordinate.charAt(1));
            
            enteredCoordinates.add(coordinate);
            itemMessage = new TextImage(grid.getElement(row, col).getItemDescription(), 20, Color.RED, Color.BLUE);
            
            addObject(itemMessage, getWidth()/2, 100);  
            
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(itemMessage);
            
            grid.getElement(row, col).makeChanges();   //add Cash, backstabbed, sank, etc.
            removeObject(grid.getElement(row, col));
        }
        
        
        if(whatHappenedButton.isClicked() || Greenfoot.isKeyDown("escape"))
        {
            whatHappenedButton.setClicked(false);
            
            addObject(whatHappened, getWidth()/2, getHeight()/2); //black background
            addObject(exitWhatHappened, getWidth()/2, 500); //Exit button
            
            addObject(robbed, 200, 200);
            addObject(swapped, 200, 400);
            addObject(backstabbed, 600, 200);
            addObject(gifted, 600, 400);
            addObject(wipedOut, getWidth()/2, getHeight()/2);
        }
        
        if(exitWhatHappened.isClicked())
        {
            closeWhatHappened();
            exitWhatHappened.setClicked(false);
        }
        
        if(robbed.isClicked())
        {
            robbed.setClicked(false);
            //Greenfoot.playSound("wipedout.mp3");
            gameMessage = new TextImage("You got robbed :(", 20, Color.RED, Color.BLUE);
            addObject(gameMessage, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameMessage);
            
            cash = 0;
            updateCashIcon();
            closeWhatHappened();
        }
        
        if(swapped.isClicked())
        {
            swapped.setClicked(false);
            //Greenfoot.playSound("swapped.mp3");
            gameMessage = new TextImage("Your score got swapped!", 20, Color.RED, Color.BLUE);
            addObject(gameMessage, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameMessage);
            boolean notFound = true;
            int opponentScore = 0;
            do {
                try {
                    opponentScore = Integer.parseInt(Greenfoot.ask("How much cash did your opponent have? "));
                    notFound = false;
                } catch (NumberFormatException e) {
                    notFound = true;
                    e.printStackTrace();
                }
            } while(notFound == true);
            cash = opponentScore;
            updateCashIcon();
            closeWhatHappened();
        }
        
        if(backstabbed.isClicked())
        {
            backstabbed.setClicked(false);
            //Greenfoot.playSound("backstabbed.mp3");
            gameMessage = new TextImage("You got backstabbed! You miss a turn.", 20, Color.RED, Color.BLUE);
            addObject(gameMessage, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameMessage);
            
            closeWhatHappened();
        }
        
        if(gifted.isClicked())
        {
            gifted.setClicked(false);
            
            //Greenfoot.playSound("gifted.mp3");
            gameMessage = new TextImage("You received $1000!", 20, Color.RED, Color.BLUE);
            addObject(gameMessage, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameMessage);
            
            cash += 1000;
            updateCashIcon();
            closeWhatHappened();
        }
        
        if(wipedOut.isClicked())
        {
            wipedOut.setClicked(false);
            Greenfoot.playSound("wipedout.mp3");
            gameMessage = new TextImage("You got wiped out :(", 20, Color.RED, Color.BLUE);
            addObject(gameMessage, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameMessage);
            
            cash = 0;
            updateCashIcon();
            closeWhatHappened();
            

        }
        
        if(enteredCoordinates.size() == 39) //if all 49 squares have been called
        {
            enteredCoordinates.add("DUMMY TO EXIT IF");
            Greenfoot.setWorld(new gameOverScreen(bankAmount + cash));
        }
        
        if(saveGameButton.isClicked())
        {
            saveGameButton.setClicked(false);
            saveGame();
            addObject(gameSavedConfirmation, getWidth()/2, getHeight()/2);
            Greenfoot.delay(DELAY_AMOUNT);
            removeObject(gameSavedConfirmation);
        }
    }

    private void closeWhatHappened()
    {
        removeObject(exitWhatHappened);
        removeObject(whatHappened);
        
        removeObject(robbed);
        removeObject(backstabbed);
        removeObject(swapped);
        removeObject(gifted);
        removeObject(wipedOut);
    }
    
    public void saveGame()
    {
        //Neeeds to save the grid as well. Hitlist too? Nah. Maybe entered coordinates too? Maybe.
        String gameData = cash + "\n" + hasShield + "\n" + hasMirror + "\n" + bankAmount + "\n" + enteredCoordinates + "\n" + Arrays.deepToString(grid.getGrid());
        
        try {
            File fileObject = new File("saved_game.txt"); //Creates a new file if it doesn't exist
            if(fileObject.length() == 0) // file is empty, this is the first saved game.
            {
                writeFile(gameData, fileObject, true);
            } else {
                writeFile(gameData, fileObject, false);
            }
            
        } catch (IOException e){ //IOException is a superclass of FilenNotFoundException, so this covers it too
            System.out.println("Error appending file! Check recentscores.txt exists and is writable.");
            e.printStackTrace();
        }
    }
    
    private void writeFile(String s, File f, boolean appendFile) throws IOException {
        FileWriter myWriter = new FileWriter(f, appendFile); //true argument indicates we are appending instead of overwriting
        myWriter.write(s);
        myWriter.close();
    }    
    
    public void updateCashIcon() 
    {
        removeObject(cashIcon);
        cashIcon = new TextImage("Cash: " + Integer.toString(cash), 20, Color.RED, Color.BLUE);
        addObject(cashIcon, 750, 20);
    }
    
    //setter for cash - used for SwapScore, LostAtSea
    public void setCash(int newCash)
    {
        cash = newCash;
        updateCashIcon();
    }
    
    // used for Robbed
    public void increaseCash(int amount)
    { 
        cash += amount;
        updateCashIcon();
    }
    
    public void doubleScore()
    {
        cash *= 2;
        updateCashIcon();
    }
    
    public void bankScore()
    {
        bankAmount = cash;
        bankIcon = new TextImage("Bank: " + Integer.toString(bankAmount), 20, Color.RED, Color.BLUE);
        addObject(bankIcon, 750, 50);
    }
    
    public boolean hasShield()
    {
        return hasShield;
    }
    
    //setter for shield
    public boolean setShield(boolean shieldStatus)
    {
        hasShield = shieldStatus;
        if(hasShield == true)
        {
            shieldIcon = new Shield();
            addObject(shieldIcon, 770, 100);
        }
        return hasShield;
    }
    
    public void useShield()
    {
        hasShield = false;
        gameMessage = new TextImage("You blocked your opponent with your shield!", 20, Color.RED, Color.BLUE);
        addObject(gameMessage, getWidth()/2, getHeight()/2);
        Greenfoot.delay(DELAY_AMOUNT);
        removeObject(gameMessage);
        removeObject(shieldIcon);
    }
    
    public boolean hasMirror()
    {
        return hasMirror;
    }
    
    //setter for mirror
    public boolean setMirror(boolean mirrorStatus)
    {
        hasMirror = mirrorStatus;
        
        if(hasMirror == true)
        {
            mirrorIcon = new Mirror();
            addObject(mirrorIcon, 770, 160);
        }
        return hasMirror;
    } 
    
    public void useMirror()
    {
        hasMirror = false;
        gameMessage = new TextImage("You reflected your opponent's attack!", 20, Color.RED, Color.BLUE);
        addObject(gameMessage, getWidth()/2, getHeight()/2);
        Greenfoot.delay(DELAY_AMOUNT);
        removeObject(gameMessage);
        removeObject(mirrorIcon);
    }
}
