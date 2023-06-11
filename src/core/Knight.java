/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yugon
 */
public class Knight extends Piece
{
    
    public Knight(PieceType type, Color color) 
    {
        
        super(type, color);
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = checkMove(move);
        
        if(!validMoveDetected)
        {
            
            throw new InvalidMoveException("Invalid move detected");
            
        }
        
    }
    
    private boolean checkMove(Move move)
    {
        
        System.out.println(Math.abs(move.sourceRow - move.targetRow) + "&&" + Math.abs(move.sourceColumn - move.targetColumn) + "||" + Math.abs(move.sourceRow - move.targetRow) + "&&" + Math.abs(move.sourceColumn - move.targetColumn));
        
        return (Math.abs(move.sourceRow - move.targetRow) == 2 && Math.abs(move.sourceColumn - move.targetColumn) == 1) || (Math.abs(move.sourceRow - move.targetRow) == 1 && Math.abs(move.sourceColumn - move.targetColumn) == 2);
    
    }
        
    
    
    
}
