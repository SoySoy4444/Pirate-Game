import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Items
{
    private GreenfootImage image;
    
    /**
     * Act - do whatever the Shield wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // You can click on the shield only if you have a shield - you can't click on the one in the grid.
        if(Greenfoot.mouseClicked(this) && ((MainScreen)getWorld()).hasShield() == true)
        {
            String confirmation = Greenfoot.ask("Are you sure you want to use your shield? Type <yes >or <no>");
            if(confirmation.equals("yes"))
            {
                ((MainScreen)getWorld()).useShield();
            }
        }
    }
    
    public Shield()
    {
        getImage().scale(30, 30);
    }
    
    //overriding and implementing Items class's abstract method
    public String toString()
    {
        return "shield";
    }
    
    public String getItemDescription()
    {
        return "You got a shield!";
    }

    public void makeChanges()
    {
        ((MainScreen)getWorld()).setShield(true);
    }
}
