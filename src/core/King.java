/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.lang.Math;

/**
 *
 * @author yumi
 */
public class King extends Piece
{
    
    boolean firstMove;
    
    public King(PieceType type, Color color) 
    {
        
        super(type, color);
        
        this.firstMove = true;
        
    }
    
    @Override
    public void executeMove(Move move)
    {
        
        super.executeMove(move);
        
        if(this.firstMove) this.firstMove = !this.firstMove;
        
    }
    
    public boolean trySingleStepMove(Move move)
    {
        
        if(Math.abs(move.targetRow - move.sourceRow) > 1 || Math.abs(move.targetColumn - move.sourceColumn) > 1) return false;
        
        return true;
        
    }
    
    public boolean tryCastling(Move move)
    {
        
        return false;
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = false;
        
        if(!move.isKingInCheck(move.sourceRow, move.sourceColumn, this.color))
        {
            
            validMoveDetected = tryCastling(move);
            
        }
        
        validMoveDetected |= trySingleStepMove(move);
        
        if(!validMoveDetected) throw new InvalidMoveException("Invalid King Move");
        
    }
    
}
