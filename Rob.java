import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rob here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rob extends Items
{
    /**
     * Act - do whatever the Rob wants to do. This method is called whenever
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
        return "rob";
    }
    
    public String getItemDescription()
    {
        return "You robbed someone!";
    }
    
    public void makeChanges()
    {
        int amount = Integer.parseInt(Greenfoot.ask("How much did you rob from your opponent? "));
        ((MainScreen)getWorld()).increaseCash(amount);
    }
}
