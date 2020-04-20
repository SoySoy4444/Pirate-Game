import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChooseNextSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseNextSquare extends Items
{
    /**
     * Act - do whatever the ChooseNextSquare wants to do. This method is called whenever
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
        return "choose next square";
    }
    
    public String getItemDescription()
    {
        return "You can choose the next square!";
    }
    
    public void makeChanges()
    {
    }
}
