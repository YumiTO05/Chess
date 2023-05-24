/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author yumi
 */
public class Game 
{
    
    protected static Color turn;
    
    protected ArrayList<Piece> blackArmy; 
    
    protected ArrayList<Piece> whiteArmy;
    
    protected static Board board;
    
    public static final int BLACK_PROMOTION_ROW = 7;
    
    public static final int WHITE_PROMOTION_ROW = 0;
    
    public static boolean blackLongCastlingOnGoing;
    
    public static boolean blackShortCastlingOnGoing;
    
    public static boolean whiteLongCastlingOnGoing;
    
    public static boolean whiteShortCastlingOnGoing;
    
    public static int pawnPromotionColumn;
    
    public static boolean blackPawnPromotionOnGoing;
    
    public static boolean whitePawnPromotionOnGoing;
    
    public static boolean epLastBlackMove;
    
    public static boolean epLastWhiteMove;
    
    public static boolean epBlackOnGoing;
    
    public static boolean epWhiteOnGoing;
    
    public static int epBlackPawnRow;
    
    public static int epWhitePawnRow;
    
    public static int epBlackPawnColumn;
    
    public static int epWhitePawnColumn;
    
    public Game()
    {
        
        turn = Color.WHITE;
        
        blackArmy = new ArrayList<>();
        
        whiteArmy = new ArrayList<>();
        
        board = new Board();
        
    }
    
    public void setup()
    {
        
        int iMaxValue = board.tiles.length - 1;
        
        for(int i = 0; i < board.tiles.length; i++)
        {
            
            int jMaxValue = board.tiles[i].length - 1;
            
            boolean iEqualsZero = i == 0;
            
            boolean iEqualsOne = i == 1;
            
            boolean iEqualsSix = i == iMaxValue - 1;
            
            boolean iEqualsSeven = i == iMaxValue;
            
            for(int j = 0; j < board.tiles[i].length; j++)
            {
                
                if((iEqualsZero && j == 0) || (iEqualsZero && j == jMaxValue))
                {
                    
                    Rook blackRook = new Rook(PieceType.ROOK, Color.BLACK);
                    
                    board.tiles[i][j].setPiece(blackRook);
                    
                    blackArmy.add(blackRook);
                    
                }
                
                if((iEqualsZero && j == 1) || (iEqualsZero && j == jMaxValue - 1))
                {
                    
                    Knight blackKnight = new Knight(PieceType.KNIGHT, Color.BLACK);
                    
                    board.tiles[i][j].setPiece(blackKnight);
                    
                    blackArmy.add(blackKnight);
                    
                }
                
                if((iEqualsZero && j == 2) || (iEqualsZero && j == jMaxValue - 2))
                {
                    
                    Bishop blackBishop = new Bishop(PieceType.BISHOP, Color.BLACK, board.tiles[i][j]);
                    
                    board.tiles[i][j].setPiece(blackBishop);
                    
                    blackArmy.add(blackBishop);
                }
                
                if(iEqualsZero && j == 3)
                {
                    
                    Queen blackQueen = new Queen(PieceType.QUEEN, Color.BLACK);
                    
                    board.tiles[i][j].setPiece(blackQueen);
                    
                    blackArmy.add(blackQueen);
                    
                }
                
                if(iEqualsZero && j == 4)
                {
                    
                    King blackKing = new King(PieceType.KING, Color.BLACK);
                    
                    board.tiles[i][j].setPiece(blackKing);
                    
                    blackArmy.add(blackKing);
                    
                }
                
                if(iEqualsOne)
                {
                    
                    Pawn blackPawn = new Pawn(PieceType.PAWN, Color.BLACK, this);
                    
                    board.tiles[i][j].setPiece(blackPawn);
                    
                    blackArmy.add(blackPawn);
                    
                }
                
                
                if((iEqualsSeven && j == 0) || (iEqualsSeven && j == jMaxValue))
                {
                    
                    Rook whiteRook = new Rook(PieceType.ROOK, Color.WHITE);
                    
                    board.tiles[i][j].setPiece(whiteRook);
                    
                    whiteArmy.add(whiteRook);
                    
                }
                
                if((iEqualsSeven && j == 1) || (iEqualsSeven && j == jMaxValue - 1))
                {
                    
                    Knight whiteKnight = new Knight(PieceType.KNIGHT, Color.WHITE);
                    
                    board.tiles[i][j].setPiece(whiteKnight);
                    
                    whiteArmy.add(whiteKnight);
                    
                }
                
                if((iEqualsSeven && j == 2) || (iEqualsSeven && j == jMaxValue - 2))
                {
                    
                    Bishop whiteBishop = new Bishop(PieceType.BISHOP, Color.WHITE, board.tiles[i][j]);
                    
                    board.tiles[i][j].setPiece(whiteBishop);
                    
                    whiteArmy.add(whiteBishop);
                }
                
                if(iEqualsSeven && j == 3)
                {
                    
                    Queen whiteQueen = new Queen(PieceType.QUEEN, Color.WHITE);
                    
                    board.tiles[i][j].setPiece(whiteQueen);
                    
                    whiteArmy.add(whiteQueen);
                    
                }
                
                if(iEqualsSeven && j == 4)
                {
                    
                    King whiteKing = new King(PieceType.KING, Color.WHITE);
                    
                    board.tiles[i][j].setPiece(whiteKing);
                    
                    whiteArmy.add(whiteKing);
                    
                }
                
                if(iEqualsSix)
                {
                    
                    Pawn whitePawn = new Pawn(PieceType.PAWN, Color.WHITE, this);
                    
                    board.tiles[i][j].setPiece(whitePawn);
                    
                    blackArmy.add(whitePawn);
                    
                }
                
            }
            
        }
        
    }
    
    public void clear()
    {
        
        for(int i = 0; i < board.tiles.length; i++)
        {
            
            for(int j = 0; j < board.tiles[i].length; j++)
            {
                
                board.tiles[i][j].setPiece(null);
                
            }
            
        }
                    
        
    }
    
    public static Board getBoard()
    {
        
        return board;
        
    }
    
    public static Color getTurn()
    {
        
        return turn;
        
    }
    
    public void removePieceFromArmy(Piece piece, Color color)
    {
        
        if(color == Color.BLACK)
        {
            
            blackArmy.remove(piece);
            
            return;
            
        }
        
        if(color == Color.WHITE)
        {
            
            whiteArmy.remove(piece);
            
        }
        
    }
    
    public void addPieceToArmy(Piece piece, Color color)
    {
        
        if(color == Color.BLACK)
        {
            
            blackArmy.add(piece);
            
        }
        
        if(color == Color.WHITE)
        {
            
            whiteArmy.add(piece);
            
        }
        
    }
    
    public boolean isMovedSourceValid(int row, int column)
    {
        
        if(board.tiles[row][column].getPiece() == null) return false;
        
        return board.tiles[row][column].getPiece().getColor() == turn;
        
    }
    
    public boolean processMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn)
    {
        
        Move move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
        
        resetCastlingVariables(move);
        
        resetPawnPromotionVariables(move);
        
        try
        {
            
            Piece piece = board.GetTile(sourceRow, sourceColumn).getPiece();
            
            piece.validateMove(move);
            
            move.wouldEndInKingCheck();
            
            piece.executeMove(move);
            
            if(turn == Color.BLACK) turn = Color.WHITE;
            
            if(turn == Color.WHITE) turn = Color.BLACK;
            
            return true;
            
        }
        catch(InvalidMoveException ime)
        {
            
            
        }
        
        return true;
        
    }
    
    public void validateMove(Move move) throws InvalidMoveException
    {
        
        Piece movingPiece = board.tiles[move.sourceRow][move.sourceColumn].getPiece();
        
        movingPiece.validateMove(move);
        
    }
            
    public void executeMove(Move move)
    {
        
        Tile sourceTile = board.tiles[move.sourceRow][move.sourceColumn];
        
        Tile targetTile = board.tiles[move.targetRow][move.targetColumn];
        
        Piece sourcePiece = sourceTile.getPiece();
        
        Piece targetPiece = targetTile.getPiece();
        
        sourcePiece.executeMove(move);
        
        if(targetPiece != null) 
        {
            
            if(targetPiece.getColor() == Color.BLACK) blackArmy.remove(targetPiece);
            
            if(targetPiece.getColor() == Color.WHITE) whiteArmy.remove(targetPiece);
            
        }
        
        targetTile.setPiece(sourcePiece);
        
        sourceTile.setPiece(null);
                
        
    }
    
    private void resetCastlingVariables(Move move)
    {
        
        Piece movingPiece = move.getPiece(move.sourceRow, move.sourceColumn);
        
        if(movingPiece != null && movingPiece.getColor() == Color.BLACK)
        {
            
            blackLongCastlingOnGoing = false; 
            
            blackShortCastlingOnGoing = false;
        
        }
        else if(movingPiece != null && movingPiece.getColor() == Color.WHITE )
        {
            
            whiteLongCastlingOnGoing = false;
            
            whiteShortCastlingOnGoing = false;
            
        }
        
    }
    
    private void resetPawnPromotionVariables(Move move)
    {
        
        Piece movingPiece = move.getPiece(move.sourceRow, move.sourceColumn);
        
        if(movingPiece != null && movingPiece.getColor() == Color.BLACK)
        {
            
            blackPawnPromotionOnGoing = false; 
        
        }
        else if(movingPiece != null && movingPiece.getColor() == Color.WHITE)
        {
            
            whitePawnPromotionOnGoing = false;
            
        }
        
    }
    
    private void resetEpVariables(Move move)
    {
        
        epBlackOnGoing = false;
        
        epWhiteOnGoing = false;
        
        if(turn == Color.BLACK) epLastWhiteMove = false;
        
        if(turn == Color.WHITE) epLastBlackMove = false;
        
    }
    
    
}
