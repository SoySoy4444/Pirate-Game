import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class SetupScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SetupScreen extends World
{
    private static Map<Integer,Integer> cashValueNum = new HashMap<>();
    static {
        cashValueNum.put(5000, 1);
        cashValueNum.put(3000, 2);
        cashValueNum.put(1000, 10);
        cashValueNum.put(200, 24);
    }
    
    static final int NUM_ROWS = 7;
    static final int NUM_COLS = 7;
    static final String validRows = "ABCDEFG";
    static final String validCols = "1234567";
    
    private ArrayList<String> enteredCoordinates = new ArrayList<String>();
    private ArrayList<String> availableCoordinates = new ArrayList<>(Arrays.asList(
    "A1", "A2", "A3", "A4", "A5", "A6", "A7",
    "B1", "B2", "B3", "B4", "B5", "B6", "B7",
    "C1", "C2", "C3", "C4", "C5", "C6", "C7",
    "D1", "D2", "D3", "D4", "D5", "D6", "D7",
    "E1", "E2", "E3", "E4", "E5", "E6", "E7",
    "F1", "F2", "F3", "F4", "F5", "F6", "F7",
    "G1", "G2", "G3", "G4", "G5", "G6", "G7"
    ));
    
    private Grid grid;
    private TextImage customButton;
    private TextImage semiCustomButton;
    private TextImage randomButton;
    private TextImage addNextItemButton;
    private TextImage removeItemButton;
    private TextImage randomFillButton;
    private TextImage proceedToGameButton;
    
    private final Image soundIcon = new Image("soundIcon.png", 50, 50); //Black background actor
    private final Image soundScreen = new Image("soundScreenBackground.png", 800, 600); //Black background actor
    private final TextImage exitSoundScreen = new TextImage("Exit", 30, Color.RED, Color.BLUE);
    private final TextImage soundPlayButton = new TextImage("Play", 30, Color.RED, Color.BLUE);
    private final TextImage soundPauseButton = new TextImage("Pause", 30, Color.RED, Color.BLUE);
    private final TextImage soundStopButton = new TextImage("Stop", 30, Color.RED, Color.BLUE);
    private final GreenfootSound setupBGM = new GreenfootSound("setupBGM.mp3");
    
    private boolean itemFill;
    private boolean cashFill;
    
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
    
    private ArrayList<Items> itemsToBeAdded = new ArrayList<>(Arrays.asList(backstab, bank, chooseNextSquare,
    doubleScore, lostAtSea, mirror, present, shield, rob, sinkShip, sneakPeek, swapScore));
    
    /**
     * Constructor for objects of class SetupScreen.
     * 
     */
    public SetupScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        
        //Create and add the grid
        grid = new Grid(NUM_ROWS, NUM_COLS);
        
        addObject(grid, getWidth()/2, getHeight()/2);
        
        //initialise the Add next item button - initially hidden, displayed when setup option selected
        addNextItemButton = new TextImage("Add next item", 20, Color.RED, Color.BLUE);
        
        //initialise the remove button - initially hidden, displayed when setup option selected
        removeItemButton = new TextImage("Remove", 20, Color.RED, Color.BLUE);
        
        //initialise the random fill button
        randomFillButton = new TextImage("Setup randomly", 20, Color.RED, Color.BLUE);
        
        //add the setup option buttons
        customButton = new TextImage("Custom", 20, Color.RED, Color.BLUE);
        addObject(customButton, 200, 200);
        
        semiCustomButton = new TextImage("Semi-custom", 20, Color.RED, Color.BLUE);
        addObject(semiCustomButton, 400, 200);
       
        randomButton = new TextImage("Random", 20, Color.RED, Color.BLUE);
        addObject(randomButton, 600, 200);
        
        //add the proceed to main screen button (initially hidden, shown when all squares filled)
        proceedToGameButton = new TextImage("Proceed to Game", 20, Color.RED, Color.BLUE);
        
        addObject(soundIcon, 50, 50); //black background
    }
    
    /**
     * Press space OR press button to add new item (custom & semiCustom)
     * Press backspace OR press buton to delete item (custom & semiCustom)
     */
    public void act()
    {      
        //SOUND CONTROLS /////////////
        if(soundIcon.isClicked())
        {
            soundIcon.setClicked(false);
            addObject(soundScreen, getWidth()/2, getHeight()/2); //black background
            addObject(exitSoundScreen, getWidth()/2, 500);
            addObject(soundPlayButton, getWidth()/2, 200);
            addObject(soundPauseButton, getWidth()/2, 250);
            addObject(soundStopButton, getWidth()/2, 400);
        }
        
        if(exitSoundScreen.isClicked())
        {
            exitSoundScreen.setClicked(false);
            removeObject(soundScreen);
            removeObject(exitSoundScreen);
            removeObject(soundPlayButton);
            removeObject(soundStopButton);
            removeObject(soundPauseButton);
        }
        
        if(soundPlayButton.isClicked())
        {
            soundPlayButton.setClicked(false);
            setupBGM.play();
        }
        
        if(soundPauseButton.isClicked())
        {
            soundPauseButton.setClicked(false);
            setupBGM.pause();
        }
        
        if(soundStopButton.isClicked())
        {
            soundStopButton.setClicked(false);
            setupBGM.stop();
        }
        //////////////
        
        if(customButton.isClicked())
        {
            customButton.setClicked(false);
            changeButtons(true, true);
        }
        
        if(semiCustomButton.isClicked())
        {
            semiCustomButton.setClicked(false);
            changeButtons(true, false);
        }
        
        if(randomButton.isClicked())
        {
            randomButton.setClicked(false);
            changeButtons(false, false);
        }
        
        //for custom and semiCustom
        if(addNextItemButton.isClicked() || Greenfoot.isKeyDown("space"))
        {
            addNextItemButton.setClicked(false);
            setUpItem();
        }
        
        if(proceedToGameButton.isClicked())
        {
            proceedToGameButton.setClicked(false);
            //System.out.println(Arrays.deepToString(grid.getGrid()));
            Greenfoot.setWorld(new MainScreen(grid));
        }
        
        //random Fill
        if(randomFillButton.isClicked())
        {
            randomFillButton.setClicked(false);
            randomlyFillItems();
            randomlyFillCash();
            removeObject(randomFillButton);
            addObject(proceedToGameButton, 600, 200);
        }
        
        if(removeItemButton.isClicked() || Greenfoot.isKeyDown("backspace"))
        {
            removeItemButton.setClicked(false);
            String coordinate;
            do {
                coordinate = Greenfoot.ask("Enter the coordinates of the item you would like to remove: ");
            }
            while(coordinate.length() != 2 || validRows.indexOf(coordinate.charAt(0)) == -1 || validCols.indexOf(coordinate.charAt(1)) == -1 || enteredCoordinates.contains(coordinate) == false);
            itemsToBeAdded.add(grid.getElement(grid.getRowFromAlpha(coordinate.charAt(0)), grid.getColFromNum(coordinate.charAt(1))));
            enteredCoordinates.remove(enteredCoordinates.indexOf(coordinate));
            availableCoordinates.add(coordinate);

            removeObject(grid.getElement(grid.getRowFromAlpha(coordinate.charAt(0)), grid.getColFromNum(coordinate.charAt(1))));
        }
        
        //custom and semiCustom
        if (itemsToBeAdded.size() == 0) //if all items have been set up, now time for cash
        {
            removeObject(addNextItemButton);
            removeObject(removeItemButton);
            
            itemsToBeAdded.add(new Mirror()); //DUMMY LINE: Deactivate this if loop.
            if(cashFill == true) //custom fill
            {
                setUpCash();
                addObject(proceedToGameButton, 600, 200);
            }
            else //semiCustom fill
            {
                randomlyFillCash();
                addObject(proceedToGameButton, 600, 200);
            }
        }
    }
    
    //Removes the setup buttons
    //Add the "addNextItemButton" and Remove button
    //Initialise the itemFill and cashFill variables
    private void changeButtons(boolean itemFill, boolean cashFill)
    {
        removeObject(customButton);
        removeObject(semiCustomButton);
        removeObject(randomButton);
        
        if(itemFill)
        {
            addObject(addNextItemButton, 400, 300);
            addObject(removeItemButton, 100, 500);
        }
        else 
        {
            addObject(randomFillButton, 400, 300);
        }
        
        //initialise the itemFill and cashFill  instance variables now
        this.itemFill = itemFill;
        this.cashFill = cashFill;
    }
    
    //for random
    private void randomlyFillItems()
    {
        String coordinate;
        int row;
        int col;
        // put your code here
        for(int i = 0; i < itemsToBeAdded.size(); i++)
        {
            coordinate = availableCoordinates.get(Greenfoot.getRandomNumber(availableCoordinates.size()));
            row = grid.getRowFromAlpha(coordinate.charAt(0));
            col = grid.getColFromNum(coordinate.charAt(1));
            
            grid.setElement(row, col, itemsToBeAdded.get(i));
            grid.addItem(itemsToBeAdded.get(i), row, col);
            availableCoordinates.remove(availableCoordinates.indexOf(coordinate));
        }
    }

    //for custom
    private void setUpCash()
    {
        String coordinate;
        for(int value: cashValueNum.keySet())
        {
            for(int i = 0; i < cashValueNum.get(value); i++)
            {
                //if value is 200, no need to ask the user for input each time, just fill it out
                if(value == 200)
                {
                    for(int r = 0; r < grid.getGrid().length; r++)
                    {
                        for(int c = 0; c < grid.getGrid()[r].length; c++)
                        {
                            if(grid.getElement(r, c) == null) //if the location is empty
                            {
                                Cash cash = new Cash(200, "200");
                                grid.setElement(r, c, new Cash(200, "200"));
                                grid.addItem(cash, r, c);
                            }
                        }
                    }
                } else {
                    
                    Cash cash = new Cash(value, Integer.toString(value));
                    do {
                        coordinate = Greenfoot.ask("Where would you like to place " + cash.toString() + " #" + Integer.toString(i+1) + "?");
                    }
                    while(coordinate.length() != 2 || validRows.indexOf(coordinate.charAt(0)) == -1 || validCols.indexOf(coordinate.charAt(1)) == -1 || enteredCoordinates.contains(coordinate) == true);
                    
                    int row = grid.getRowFromAlpha(coordinate.charAt(0));
                    int col = grid.getColFromNum(coordinate.charAt(1));
                    
                    grid.setElement(row, col, cash);
                    grid.addItem(cash, row, col);
                    enteredCoordinates.add(coordinate);
                    availableCoordinates.remove(availableCoordinates.indexOf(coordinate));
                }
            }
        }
    }
    
    //for semiCustom and random
    private void randomlyFillCash()
    {        
        for(int value: cashValueNum.keySet()) //for each bank note value ($5000, $3000, $1000, $200)
        {
            for(int i = 0; i < cashValueNum.get(value); i++) //for each individual note)
            {
                String coordinate = availableCoordinates.get(Greenfoot.getRandomNumber(availableCoordinates.size()));
                int row = grid.getRowFromAlpha(coordinate.charAt(0));
                int col = grid.getColFromNum(coordinate.charAt(1));
                
                Cash cash = new Cash(value, Integer.toString(value));
                grid.setElement(row, col, cash);
                grid.addItem(cash, row, col);
                
                availableCoordinates.remove(availableCoordinates.indexOf(coordinate));
            }
        }
    }
    
    //for custom and semiCustom
    private void setUpItem()
    {
        String coordinate;
        int row;
        int col;
        
        //Input validation - will keep asking until user enters acceptable coordinate.
        do {
            coordinate = Greenfoot.ask("Where would you like to place " + itemsToBeAdded.get(0).toString() + "?");
            row = grid.getRowFromAlpha(coordinate.charAt(0));
            col = grid.getColFromNum(coordinate.charAt(1));
        }
        while(coordinate.length() != 2 || validRows.indexOf(coordinate.charAt(0)) == -1 || validCols.indexOf(coordinate.charAt(1)) == -1 || enteredCoordinates.contains(coordinate) == true);
        
        grid.setElement(grid.getRowFromAlpha(coordinate.charAt(0)), grid.getColFromNum(coordinate.charAt(1)), itemsToBeAdded.get(0));
        grid.addItem(grid.getElement(row, col), row, col); //add to the world
        
        enteredCoordinates.add(coordinate); //add the current coordinate to already entered coordinates
        availableCoordinates.remove(availableCoordinates.indexOf(coordinate));
        itemsToBeAdded.remove(0); //remove the item from the list of items that need to be entered
    }
}