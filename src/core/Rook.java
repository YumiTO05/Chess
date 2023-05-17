/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yugon
 */
public class Rook extends Piece
{
    
    public Rook(PieceType type, Color color) 
    {
        
        super(type, color);
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = orthogonalMove(move);
        
        if(!validMoveDetected)
        {
            
            throw new InvalidMoveException("Invalid Move Detected - Rook");
            
        }
        
    }
    
    public static boolean orthogonalMove(Move move)
    {
        
        if(!(move.sourceColumn == move.targetColumn || move.sourceRow == move.targetRow)) return false;
        
        return !move.checkObstacles();
        
    }
    
}
