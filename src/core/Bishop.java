/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.lang.Math;

/**
 *
 * @author yugon
 */
public class Bishop extends Piece
{
    
    boolean onWhite;
    
    public Bishop(PieceType type, Color color, Tile tile) 
    {
        
        super(type, color);
        
        onWhite = tile.getColor() == Color.WHITE;
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = true;
        
        if((move.board.GetTile(move.targetRow, move.targetRow).getColor() == Color.BLACK && this.onWhite) || move.board.GetTile(move.targetRow, move.targetRow).getColor() == Color.WHITE && !this.onWhite) validMoveDetected = false;
        
        if(!validMoveDetected)
        {
            
            throw new InvalidMoveException("Invalid Move Detected");
            
        }
        
        diagonalMove(move);
        
    }
    
    public static boolean diagonalMove(Move move)
    {
       
        int rowDelta = Math.abs(move.targetRow - move.sourceRow);
                
        int columnDelta = Math.abs(move.targetColumn - move.sourceColumn);
        
        if(rowDelta != columnDelta) return false;
        
        return !move.checkObstacles();
        
    }
    
    
}
