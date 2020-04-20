import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WhatHappened here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Image extends Actor
{
    /**
     * Act - do whatever the WhatHappened wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage image;
    private boolean clicked;
    
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
        {
            clicked = true;
        }
    }    
    
    public Image(String filename, int x, int y)
    {
        image = new GreenfootImage(filename);
        image.scale(x, y);
        setImage(image);
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
