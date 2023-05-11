/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yugon
 */
public class Queen extends Piece
{
    
    public Queen(PieceType type, Color color) 
    {
        
        super(type, color);
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = Rook.orthogonalMove(move) || Bishop.diagonalMove(move);
        
        if(!validMoveDetected)
        {
            
            throw new InvalidMoveException("Invalid Move Detected");
            
        }
        
    }
    
    
}
