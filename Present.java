import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Present here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Present extends Items
{
    /**
     * Act - do whatever the Present wants to do. This method is called whenever
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
        return "present";
    }
    
    public String getItemDescription()
    {
        return "Give a present to someone!";
    }
    
    public void makeChanges()
    {
    }
}
