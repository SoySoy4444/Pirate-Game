import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Money here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cash extends Items
{
    /**
     * Act - do whatever the Money wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int value;
    private GreenfootImage image;
    
    public void act() 
    {
        // Add your action code here.
    } 
    
    //Constructor for 200, 1000, 3000 and 5000 cash
    public Cash(int value, String filename)
    {
        this.value = value;
        this.image = new GreenfootImage(filename + ".png"); //file names will be two-hundred.png, one-thousand.png, etc...
        image.scale(30, 30); //scale down to 30 by 30 pixels
        setImage(image);
    }
    
    //overriding and implementing Items class's abstract method
    public String toString()
    {
        return "$" + Integer.toString(value);
    }
    
    public String getItemName()
    {
        return "cash";
    }
    
    public String getItemDescription()
    {
        return "You got $" + value + "!";
    }

    public int getValue()
    {
        return value;
    }
    
    
    public void makeChanges()
    {        
        ((MainScreen)getWorld()).increaseCash(value);
    }
}
