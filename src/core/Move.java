/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author yumi
 */
public class Move 
{
    
    protected int sourceRow;
    
    protected int sourceColumn;
    
    protected int targetRow;
    
    protected int targetColumn;
    
    protected Board board;
    
    protected Game game;
    
    public Move(int sourceRow, int sourceColumn, int targetRow, int targetColumn)
    {
        
        this.sourceRow = sourceRow;
        
        this.sourceColumn = sourceColumn;
        
        this.targetRow = targetRow;
        
        this.targetColumn = targetColumn;
        
        board = new Board();
        
    }
    
    public Piece getPiece(int row, int column)
    {
        
        return board.GetTile(row, column).getPiece();
        
    }
    
    public void setPiece(int row, int column, Piece piece)
    {
        
        board.GetTile(row, column).setPiece(piece);
        
    }
    
    public boolean onTarget(int row, int column)
    {
        
        return row == this.targetRow && column == this.targetColumn;
        
    }
    
    public boolean checkPiecePresence(int row, int column)
    {
        
        return board.GetTile(row, column).getPiece() != null;
        
    }
    
    public void wouldEndInKingCheck() throws InvalidMoveException
    {
        
        Piece movingPiece = board.tiles[this.sourceRow][this.sourceColumn].getPiece();
        
        Piece targetPiece = board.tiles[this.targetRow][this.targetColumn].getPiece();
        
        board.tiles[this.sourceRow][this.sourceColumn].setPiece(null);
        
        board.tiles[this.targetRow][this.targetColumn].setPiece(movingPiece);
        
        int kingRow = 0;
        
        int kingColumn = 0;
        
        for(int i = 0; i < board.tiles.length; i++)
        {
            
            for(int j = 0; j < board.tiles[i].length; j++)
            {
                
                Piece pieceSelected = (Piece) board.tiles[i][j].getPiece();
                
                if(pieceSelected != null && pieceSelected.getType() == PieceType.KING && pieceSelected.getColor() == game.getTurn())
                {
                    
                    kingRow = i; 
                    
                    kingColumn = j;
                    
                }
                
            }
            
        }
        
        boolean isCheck = isKingInCheck(kingRow, kingColumn, Game.getTurn());
        
        board.tiles[this.sourceRow][this.sourceColumn].setPiece(movingPiece);
        
        board.tiles[this.targetRow][this.targetColumn].setPiece(targetPiece);
        
        if(isCheck)
        {
            
            throw new InvalidMoveException("King will end in check");
            
        }
        
    }
    
    public boolean isTargetOccupiedByAlly(int row, int column)
    {
        
        Piece piece = board.GetTile(row, column).getPiece();
        
        return piece != null && board.GetTile(this.sourceRow, this.sourceColumn).getPiece().getColor() == piece.getColor();
        
    }
    
    public boolean isKingInCheck(int kingRow, int kingColumn, Color kingColor)
    {
        
        Color opponentColor = null;
        
        if(kingColor == Color.WHITE) opponentColor = Color.BLACK;
        else opponentColor = Color.WHITE;
        
        for(int i = 0; i < board.tiles.length; i++)
        {
            
            for(int j = 0; j < board.tiles[i].length; j++)
            {
                
                Move actualMove = new Move(i, j, kingRow, kingColumn);
                
                Piece selectedPiece = (Piece) board.tiles[i][j].getPiece();
                
                if(selectedPiece != null && selectedPiece.getColor() == opponentColor && selectedPiece.getType() != PieceType.KING)
                {
                    
                    try
                    {
                        
                        selectedPiece.validateMove(actualMove);
                        
                        return true;
                        
                    }
                    catch(InvalidMoveException ime)
                    {
                        
                        
                        
                    }
                    
                }
                
            }
            
        }
        
        return false;
        
    }
    
    public boolean checkObstacles()
    {
        
        int row = sourceRow;
        
        int column = sourceColumn;
        
        boolean rowGrows = sourceRow < targetRow;
        
        boolean columnGrows = sourceColumn < targetColumn;
        
        while(!onTarget(row, column))
        {
            
            if(row != targetRow)
            {
                
                if(rowGrows) row++;
                else row--;
                
            }
            
            if(column != targetColumn)
            {
                                
                if(columnGrows) column++;
                else column--;
                
            }
            
            if(onTarget(row, column)) return false;
            
            if(board.GetTile(row, column).getPiece() != null) return true;
            
        }
        
        return false;
        
    }
    
}
