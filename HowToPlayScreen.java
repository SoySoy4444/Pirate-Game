import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HowToPlayScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HowToPlayScreen extends World
{

    private TextImage backButton;
    
    /**
     * Constructor for objects of class HowToPlayScreen.
     * 
     */
    
    public void act()
    {
        //Greenfoot.setWorld(new TitleScreen());
        if(backButton.isClicked())
        {
            backButton.setClicked(false);
            Greenfoot.setWorld(new TitleScreen());
        }
    }
    
    public HowToPlayScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        backButton = new TextImage("Back", 20, Color.RED, Color.BLUE);
        addObject(backButton, getWidth()/2, 400);
    }
}
