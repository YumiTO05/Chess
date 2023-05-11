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
    
    public void wouldEndInKingCheck()
    {
        
        
        
    }
    
    public boolean isTargetOccupiedByAlly(int row, int column)
    {
        
        Piece piece = board.GetTile(row, column).getPiece();
        
        return board.GetTile(this.sourceRow, this.sourceColumn).getPiece().getColor() == piece.getColor();
        
    }
    
    public boolean isKingInCheck(int kingRow, int kingColumn, Color kingColor)
    {
        
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
