/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yumi
 */
public class Board 
{
    
    public static final int ROWS = 8;
    
    public static final int COLUMNS = 8;
    
    static final int LASTROW = ROWS - 1;
    
    static final int LASTCOLUMN = COLUMNS - 1;
    
    public Tile[][] tiles;
    
    public Board()
    {
        
        tiles = new Tile[ROWS][COLUMNS];
        
        for(int i = 0; i < tiles.length; i++)
        {
            boolean check = false;
            
            if(i % 2 == 0) check = true;
            
            for(int j = 0; j < tiles[i].length; j++)
            {
                
                if(check)
                {
                    
                    if(j % 2 == 0) tiles[i][j] = new Tile(i, j, Color.WHITE);
                    else tiles[i][j] = new Tile(i, j, Color.BLACK);
                    
                }
                else
                {
                    
                    if(j % 2 != 0) tiles[i][j] = new Tile(i, j, Color.WHITE);
                    else tiles[i][j] = new Tile(i, j, Color.BLACK);
                    
                }
                
            }
            
        }
        
    }
    
    /**
     *
     * @param row
     * @param column
     * @return
     * @throws IllegalArgumentException
     */
    public Tile GetTile(int row, int column) throws IllegalArgumentException
    {

        if((row < 0 || row > LASTROW ) || (column < 0 || column > LASTCOLUMN)) throw new IllegalArgumentException();

        return tiles[row][column];

    }  
     
}
