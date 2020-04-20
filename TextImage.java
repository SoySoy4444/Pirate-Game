import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextImage extends Actor
{
    
    private boolean clicked;
    
    /**
     * Act - do whatever the TextImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
        {
            clicked = true;
        }
    }
    
    public TextImage(String text, int size, Color foreground, Color background)
    {
        clicked = false;
        setImage(new GreenfootImage(text, size, foreground, background));
    }
    
    public void setClicked(boolean clickStatus)
    {
        this.clicked = clickStatus;
    }
    
    public boolean isClicked()
    {
        return clicked;
    }
}
