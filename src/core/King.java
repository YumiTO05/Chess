/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

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
        
        boolean validMove = false;
        
        Color kingColor = this.getColor();
        
        int castlingRow;
        
        boolean ObstacleDetected;
        
        if(this.firstMove)
        {
            
            if(kingColor == Color.WHITE) castlingRow = 0;
            else castlingRow = 7;
            
            if(move.targetRow == castlingRow)
            {
                
                if(move.targetColumn == 2)
                {
                    
                    Piece rookPiece = move.getPiece(castlingRow, 0);
                    
                    if(rookPiece != null && rookPiece.getType() == PieceType.ROOK && rookPiece.getColor() == this.getColor())
                    {
                        
                        ObstacleDetected = move.checkPiecePresence(castlingRow, 1);
                        
                        ObstacleDetected &= move.checkPiecePresence(castlingRow, 2);
                        
                        ObstacleDetected &= move.checkPiecePresence(castlingRow, 3);
                        
                        if(!ObstacleDetected)
                        {
                            
                            if(kingColor == Color.BLACK) Game.blackLongCastlingOnGoing = true;
                            else Game.whiteLongCastlingOnGoing = true;
                            
                            Piece rook = move.getPiece(3, 0);
                            
                            move.setPiece(castlingRow, 3, rook);
                            
                            move.setPiece(castlingRow, 0, null);
                            
                            validMove = true;
                            
                        }
                        
                    }
                    
                }
                else if(move.targetColumn == 6)
                {
                    
                    Piece rookPiece = move.getPiece(castlingRow, Board.LASTCOLUMN);
                    
                    if(rookPiece != null && rookPiece.getType() == PieceType.ROOK && rookPiece.getColor() == this.getColor())
                    {
                        
                        ObstacleDetected = move.checkPiecePresence(castlingRow, 5);
                        
                        ObstacleDetected &= move.checkPiecePresence(castlingRow, 6);
                        
                        if(!ObstacleDetected)
                        {
                            
                            if(kingColor == Color.BLACK) Game.blackShortCastlingOnGoing = true;
                            else Game.whiteShortCastlingOnGoing = true;
                            
                            Piece rook = move.getPiece(castlingRow, Board.LASTCOLUMN);
                            
                            move.setPiece(castlingRow, 5, rook);
                            
                            move.setPiece(castlingRow, Board.LASTCOLUMN, null);
                            
                            validMove = true;
                            
                        }
                        
                    }
                    
                }
                
            }
            
        }
        
        return validMove;
        
    }
    
    @Override
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        super.validateMove(move);
        
        boolean validMoveDetected = false;
        
        if(!move.isKingInCheck(move.sourceRow, move.sourceColumn, this.color))
        {
            
            validMoveDetected = tryCastling(move);
            
            executeMove(move);
            
        }
        
        validMoveDetected |= trySingleStepMove(move);
        
        if(!validMoveDetected) throw new InvalidMoveException("Invalid King Move");
        
    }
    
}
