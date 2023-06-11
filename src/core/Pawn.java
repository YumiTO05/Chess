/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yumi
 */
public class Pawn extends Piece
{
    
    boolean firstMove;
    
    private Game game;
    
    boolean enPassantCapture;
    
    public Pawn(PieceType type, Color color, Game game) 
    {
        
        super(type, color);
        
        this.firstMove = true;
        
        this.game = game;
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected;
        
        validMoveDetected = tryDoubleAdvanceMove(move);
        
        if(validMoveDetected)
        {
            
            updateEp(move);
            
            executeMove(move);
            
            return;
            
        }
        
        validMoveDetected = trySingleAdvanceMove(move);
        
        if(validMoveDetected)
        {
            
            executeMove(move);
            
            return;

        }
        
        validMoveDetected = tryDiagonalCapture(move);
        
        if(validMoveDetected)
        {
            
            executeMove(move);
            
            return;
            
        }
        
        throw new InvalidMoveException("Invalid Move Detected");
        
    }
    
    private boolean tryForwardMove(Move move, int offset)
    {
        
        if(move.getPiece(move.targetRow, move.targetColumn) != null) return false;
        
        if(move.sourceColumn != move.targetColumn) return false;
        
        if(this.getColor() == Color.BLACK && move.targetRow != move.sourceRow + offset) return false;
        
        return !(this.getColor() == Color.WHITE && move.targetRow != move.sourceRow - offset);
        
    }
    
    private boolean trySingleAdvanceMove(Move move)
    {
        
        return tryForwardMove(move, 1);
        
    }
    
    private boolean tryDoubleAdvanceMove(Move move)
    {
        
        if(!this.firstMove) return false;
        
        if(move.checkObstacles()) return false;
        
        return tryForwardMove(move, 2);
        
    }
    
    @Override
    public boolean validateCapture(Move move)
    {
        
        try 
        {
            
            super.validateMove(move);
            
            if(move.targetColumn != move.sourceColumn - 1 || move.targetColumn != move.sourceColumn + 1) throw new InvalidMoveException("Invalid Pawn Capture");
            
            return true;
            
        } 
        catch(InvalidMoveException ime) 
        {
            
            ime.printStackTrace();
            
            return false;
            
        }
        
    }
    
    @Override
    public void executeMove(Move move)
    {
        
        super.executeMove(move);
        
        if(this.firstMove) this.firstMove = !this.firstMove;
        
        if(this.getColor() == Color.WHITE && move.targetRow == 0)
        {
            
            Game.pawnPromotionColumn = move.targetColumn;
            
            Game.whitePawnPromotionOnGoing = true;
            
        }
         if(this.getColor() == Color.BLACK && move.targetRow == Board.LASTROW)
         {
             
             Game.pawnPromotionColumn = move.targetColumn;
             
             Game.blackPawnPromotionOnGoing = true;
             
         }
            
        
    }
    
    public boolean tryDiagonalCapture(Move move)
    {

        
        int rowOffset = move.targetRow - move.sourceRow;
        
        int columnOffset = move.targetColumn - move.sourceColumn;
        
        if(move.checkPiecePresence(move.targetRow, move.targetColumn))
        {
            
            return true;
                    
        }
        else if((this.game.turn == Color.WHITE && move.targetColumn == Game.epBlackPawnColumn) || (this.game.turn == Color.BLACK && move.targetColumn == Game.epWhitePawnColumn))
        {
            
            enPassantCapture = tryEnpassantCapture(move);
            
            if(enPassantCapture) return true;
            
        }
        
        return false;
        
    }
    
    private void updateEp(Move move)
    {
        
        if(this.game.turn == Color.BLACK)
        {
            
            Game.epLastBlackMove = true;
            
            Game.epBlackPawnRow = move.targetRow;
            
            Game.epBlackPawnColumn = move.targetColumn;
            
        }
        
        if(this.game.turn == Color.WHITE)
        {
            
            Game.epLastWhiteMove = true;
            
            Game.epWhitePawnRow = move.targetRow;
            
            Game.epWhitePawnColumn = move.targetColumn;
            
        }
        
    }
    
    private boolean tryEnpassantCapture(Move move)
    {
        
        enPassantCapture = false;
        
        Piece capturedPiece = null;
        
        if(this.game.turn == Color.BLACK && Game.epLastWhiteMove)
        {
            
            if(move.sourceRow == Game.epWhitePawnRow && Math.abs(move.sourceColumn - Game.epWhitePawnColumn) == 1)
            {
                
                Game.epBlackOnGoing = true;
                
                capturedPiece = move.getPiece(Game.epWhitePawnRow, Game.epWhitePawnColumn);
                
                move.setPiece(Game.epWhitePawnRow, Game.epWhitePawnColumn, null);
                
                enPassantCapture = true;
                
            }
            
        }
        else if(this.game.turn == Color.WHITE && Game.epLastBlackMove)
        {
            
            if(move.sourceRow == Game.epBlackPawnRow && Math.abs(move.sourceColumn - Game.epBlackPawnColumn) == 1)
            {
                
                Game.epWhiteOnGoing = true;
                
                capturedPiece = move.getPiece(Game.epBlackPawnRow, Game.epBlackPawnColumn);
                
                move.setPiece(Game.epBlackPawnRow, Game.epBlackPawnColumn, null);
                
                enPassantCapture = true;
                
            }
            
        }
        
        if(enPassantCapture) game.removePieceFromArmy(capturedPiece, this.game.turn);
        
        return enPassantCapture;
        
    }
    
    
    
}
