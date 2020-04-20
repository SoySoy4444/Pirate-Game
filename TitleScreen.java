import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
// Continue game function
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList; //save the game states
import java.util.Arrays;
//


/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static final int NUM_ROWS = 7;
    static final int NUM_COLS = 7;
    private final TextImage startButton = new TextImage("Start", 50, Color.RED, Color.BLUE);
    private final TextImage howToPlayButton = new TextImage("How to Play", 20, Color.RED, Color.BLUE);
    private final TextImage continueGameButton = new TextImage("Continue Game", 40, Color.RED, Color.BLUE);
    
    
    private int cash;
    private int bankAmount;
    private boolean shield;
    private boolean mirror;
    private Grid grid;
    private ArrayList<String> enteredCoordinatesArray;
    
    private TextImage savedMessage;
    
    public void act()
    {
        
        //Greenfoot.setWorld(new gameOverScreen(10000)); //testing the game over screen
        if(startButton.isClicked())
        {
            startButton.setClicked(false);
            Greenfoot.setWorld(new SetupScreen());
        }
        
        if(howToPlayButton.isClicked())
        {
            howToPlayButton.setClicked(false);
            Greenfoot.setWorld(new HowToPlayScreen());
        }
        
        if(continueGameButton.isClicked())
        {
            continueGameButton.setClicked(false);
            File savedGame = new File("saved_game.txt");
            
            if(savedGame.exists())
            {
                savedMessage = new TextImage("Loading saved file...", 50, Color.RED, Color.BLUE);
                addObject(savedMessage, getWidth()/2, getHeight()/2);
                Greenfoot.delay(120);
                
                loadGame();
                Greenfoot.setWorld(new MainScreen(grid, enteredCoordinatesArray, cash, shield, mirror, bankAmount,true));
            } else {
                savedMessage = new TextImage("Could not find saved game!", 50, Color.RED, Color.BLUE);
                addObject(savedMessage, getWidth()/2, getHeight()/2);
                Greenfoot.delay(120);
                removeObject(savedMessage);
            }
        }
    }
    
    public TitleScreen()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        addObject(startButton, getWidth()/2, 400);
        addObject(howToPlayButton, getWidth()/2, 550);
        addObject(continueGameButton, getWidth()/2, 500);
        addObject(new TextImage("The Pirate Game", 80, Color.RED, Color.BLUE), getWidth()/2, 100);
    }
    
    public void loadGame()
    {
        ArrayList<String> states = new ArrayList<String>();
        try {
            File myObj = new File("saved_game.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                states.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        cash = Integer.parseInt(states.get(0));
        shield = Boolean.parseBoolean(states.get(1));
        mirror = Boolean.parseBoolean(states.get(2));
        bankAmount = Integer.parseInt(states.get(3));
        
        String enteredCoordinates = states.get(4).substring(1, states.get(4).length()-1); //remove [ and ]
        enteredCoordinates = enteredCoordinates.replaceAll("\\s",""); //remove all spaces
        String str[] = enteredCoordinates.split(","); //split by comma
        enteredCoordinatesArray = new ArrayList<>(Arrays.asList(str));
        
        String gridString = states.get(5).substring(0, states.get(5).length()-2); //remove ]] at the end
        gridString = gridString.replace("[", ""); //remove all spaces
        String str2[] = gridString.split("],");
        grid = new Grid(NUM_ROWS, NUM_COLS);
        for(int i = 0; i < str2.length; i++){
            str2[i]=str2[i].trim();//ignoring all extra space if the string s1[i] has
            String singleElement[]=  str2[i].split(", ");//separating each array by ", "
    
            for(int j = 0; j < singleElement.length; j++){
                //System.out.println(singleElement[j]);
                switch(singleElement[j]) {
                    case "$200":
                    grid.setElement(i, j, new Cash(200, "200"));
                    break;
                    
                    case "$1000":
                    grid.setElement(i, j, new Cash(1000, "1000"));
                    break;
                    
                    case "$3000":
                    grid.setElement(i, j, new Cash(3000, "3000"));
                    break;
                    
                    case "$5000":
                    grid.setElement(i, j, new Cash(5000, "5000"));
                    break;
                    
                    case "mirror":
                    grid.setElement(i, j, new Mirror());
                    break;                    
                    
                    case "shield":
                    grid.setElement(i, j, new Shield());
                    break;   
                    
                    case "bank":
                    grid.setElement(i, j, new Bank());
                    break;
                    
                    case "backstab":
                    grid.setElement(i, j, new Backstab());
                    break;
                    
                    case "choose next square":
                    grid.setElement(i, j, new ChooseNextSquare());
                    break;
                    
                    case "double score":
                    grid.setElement(i, j, new DoubleScore());
                    break;
                    
                    case "lost at sea":
                    grid.setElement(i, j, new LostAtSea());
                    break;                     
                    
                    case "present":
                    grid.setElement(i, j, new Present());
                    break;
                    
                    case "rob":
                    grid.setElement(i, j, new Rob());
                    break;
                    
                    case "sneak peek":
                    grid.setElement(i, j, new SneakPeek());
                    break;                    
                    
                    case "sink ship":
                    grid.setElement(i, j, new SinkShip());
                    break;                    
                    
                    case "swap score":
                    grid.setElement(i, j, new SwapScore());
                    break;                    
                    
                    default:
                    System.out.println(singleElement[j] + "Not found! $200 added instead");
                    grid.setElement(i, j, new Cash(200, "200"));
                }
            }
        }
    }
}
