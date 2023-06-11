/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.Objects;

/**
 *
 * @author yumi
 */
public abstract class Piece 
{
    
    protected PieceType type;
    
    protected Color color;
    
    public Piece(PieceType type, Color color)
    {
        
        this.type = type;
        
        this.color = color;
        
    }
    
    public PieceType getType()
    {
        
        return this.type;
        
    }
    
    public Color getColor()
    {
        
        return this.color;
        
    }
    
    @Override
    public String toString()
    {
        
        return "PieceType: " + this.type + "\nPiece color: " + this.color;
        
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object)
    {
        
        Piece piece = (Piece) object;
        
        return getClass() == object.getClass() && this.type == piece.getType() && this.color == piece.getColor();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + Objects.hashCode(this.color);
        return hash;
    }
    
    public void validateMove(Move move) throws InvalidMoveException 
    {
        
        if(move.sourceRow == move.targetRow && move.sourceColumn == move.targetColumn)
        {
            
            throw new InvalidMoveException("Piece has not moved from his original position");
            
        }
        
        if(move.isTargetOccupiedByAlly(move.targetRow, move.targetColumn))
        {
            
            throw new InvalidMoveException("Piece would end on an Ally");
            
        }
        
    }
    
    public void executeMove(Move move)
    {
        
        
        
    }
    
    public boolean validateCapture(Move move)
    {
        
        try
        {
            
            validateMove(move);
            
            return true;
            
        }catch(InvalidMoveException ime)
        {
            
            return false;
            
        }    
        
        
    }
    
}
