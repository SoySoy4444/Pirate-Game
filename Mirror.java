import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mirror here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mirror extends Items
{
    private GreenfootImage image;
    /**
     * Act - do whatever the Mirror wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this) && ((MainScreen)getWorld()).hasMirror() == true)
        {
            String confirmation = Greenfoot.ask("Are you sure you want to use your mirror? Type <yes >or <no>");
            if(confirmation.equals("yes"))
            {
                ((MainScreen)getWorld()).useMirror();
            }
        }
    }
    
    public Mirror()
    {
        getImage().scale(30, 30);
    }
    
    //overriding and implementing Items class's abstract method
    public String toString()
    {
        return "mirror";
    }
    
    public String getItemDescription()
    {
        return "You got a mirror!";
    }
    
    public void makeChanges()
    {
        ((MainScreen)getWorld()).setMirror(true);
    }
}
