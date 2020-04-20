import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bank extends Items
{
    /**
     * Act - do whatever the Bank wants to do. This method is called whenever
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
        return "bank";
    }
    
    public String getItemDescription()
    {
        return "Your cash is safe in the bank!";
    }
    
    public void makeChanges()
    {
        ((MainScreen)getWorld()).bankScore();
        ((MainScreen)getWorld()).setCash(0); //once score is banked, score should be 0
    }
}
