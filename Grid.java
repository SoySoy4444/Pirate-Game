/**
 * An example of a method - replace this comment with your own
 *
 * @param  y  a sample parameter for a method
 * @return    the sum of x and y
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grid extends Actor
{
    /**
     * Act - do whatever the Grid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    static final String validRows = "ABCDEFG";
    static final String validCols = "1234567";
    
    private Items[][] grid;
    private GreenfootImage image;
    
    public void act() 
    {
        // Add your action code here
        setImage(image); //should this go in the constructor or the act()?
    }
    
    public Grid(int NUM_ROWS, int NUM_COLS)
    {
        grid = new Items[NUM_ROWS][NUM_COLS];
        image = new GreenfootImage("empty_grid.png");
    }
    
    public Items getElement(int row, int col)
    {
        return grid[row][col];
    }
    
    public void setElement(int row, int col, Items item)
    {
        grid[row][col] = item;
    }
    
    public Items[][] getGrid()
    {
        return grid;
    }
    
    //Two helper methods to convert Strings of the form "A3" to ints 1 and 3
    public int getRowFromAlpha(char alpha)
    {
        return validRows.indexOf(alpha);
    }
    
    public int getColFromNum(char num)
    {
        return validCols.indexOf(num);
    }
    
    public void printGrid()
    {
        //use the grid private variable to print
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                addItem(grid[i][j], i, j);
            }
        }
    }
    
    //This method not used but necessary?
    /*
    public void printGrid(ArrayList<Items> itemsList, ArrayList<String> coordinatesList)
    {
        for(Items item: itemsList) {
            for(String coordinate: coordinatesList) {
                int row = getRowFromAlpha(coordinate.charAt(0));
                int col = getColFromNum(coordinate.charAt(1));
                addItem(item, row, col);
            }
        }
    }
    */
    
    public void addItem(Items item, int row, int col)
    {
        //TODO: Calculate the position of the element from the coordinate
        int x = 265 + (45*row);
        int y = 165 + (45*col);
        
        getWorld().addObject(item, x, y);
    }
}
