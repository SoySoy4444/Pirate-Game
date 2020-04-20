import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Items here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class Items extends Actor
{

    public void act() 
    {
        // Add your action code here.
    }
   
    //Used in setup screen when asking where the user wants to place the items
    public abstract String toString();
    
    //Used in main screen when describing what happened to the user
    public abstract String getItemDescription();
    

    public abstract void makeChanges();
}
