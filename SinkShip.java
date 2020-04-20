import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SinkShip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SinkShip extends Items
{
    /**
     * Act - do whatever the SinkShip wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        getImage().scale(30, 30);
    }
    
    //overriding and implementing Items class's abstract method
    public String toString()
    {
        return "sink ship";
    }
    
    public String getItemDescription()
    {
        return "You sank a ship!";
    }
        
    public void makeChanges()
    {
    }
}
