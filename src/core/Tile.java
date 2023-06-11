/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yumi
 */
public class Tile 
{
    
    private int row;
    
    private int column;
    
    private Piece piece;
    
    private Color color;
    
    public Tile(int row, int column, Color color)
    {
        
        this.row = row;
        
        this.column = column;
        
        this.piece = null;
        
        this.color = color;
                
    }
            
    
    public void setPiece(Piece piece)
    {
        
        this.piece = piece;
        
    }
    
    
    public Piece getPiece()
    {
        
        return this.piece;
        
    }
    
    public Color getColor()
    {
        
        return this.color;
        
    }        
    
}
