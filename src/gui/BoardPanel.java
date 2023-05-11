/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import core.Board;
import core.Color;
import core.Game;
import core.Piece;
import core.PieceType;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yumi
 */
public class BoardPanel extends JPanel
{
    
    public static final int TILE_DIMENSION = 100;
    
    JPanel main;
    
    JPanel[][] tiles;
    
    Game game;
    
    GameDynamicsListener listener;
    
    int sourceRow, sourceColumn;
    
    boolean moveIsOnGoing;
    
    public BoardPanel(Game game)
    {
        
        super(new BorderLayout());
        
        this.game = game;
        
        this.moveIsOnGoing = false;
        
        game.setup();
        
        initializeLayout();
        
        initializeGame();
        
    }
    
    public java.awt.Color determineTileColor(int row, int column)
    {
        
        Color tileColor = game.getBoard().GetTile(row, column).getColor();
        
        if(tileColor == Color.BLACK) return java.awt.Color.DARK_GRAY;
        
        return java.awt.Color.WHITE;
        
    }
    
    public void highlightSourceTile(int row, int column, java.awt.Color color)
    {
        
        tiles[row][column].setBackground(color);
        
    }
    
    public void clearPiece(int row, int column)
    {
        
        tiles[row][column].removeAll();
        
        tiles[row][column].updateUI();
        
    }
    
    public void drawPiece(int row, int column, PieceType pieceType, Color color)
    {
        
        String iconName = "/icons/";
        
        if(color == Color.WHITE) iconName += "W";
        
        if(color == Color.BLACK) iconName += "B";
        
        if(pieceType == PieceType.BISHOP) iconName += "B";
        
        if(pieceType == PieceType.KING) iconName += "K";
        
        if(pieceType == PieceType.KNIGHT) iconName += "K";
        
        if(pieceType == PieceType.PAWN) iconName += "P";
        
        if(pieceType == PieceType.QUEEN) iconName += "Q";
        
        if(pieceType == PieceType.ROOK) iconName += "R";
        
        iconName += ".png";
        
        System.out.println(iconName);
        
        URL url = getClass().getResource(iconName);
        
        Image image = new ImageIcon(url).getImage();
        
        image = image.getScaledInstance(TILE_DIMENSION, TILE_DIMENSION, Image.SCALE_SMOOTH);
        
        JLabel label = new JLabel(new ImageIcon(image));
        
        tiles[row][column].removeAll();
        
        tiles[row][column].add(label);
        
        tiles[row][column].updateUI();
        
    }
    
    private void initializeGame()
    {
        
        for(int i = 0; i < Board.ROWS; i++)
        {
            
            for(int j = 0; j < Board.COLUMNS; j++)
            {
                
                Piece piece = game.getBoard().GetTile(i, j).getPiece();
                
                if(piece != null) drawPiece(i, j, piece.getType(), piece.getColor());
                
            }
            
        }
        
    }
    
    private void initializeLayout()
    {
        
        main = new JPanel(new GridLayout(Board.ROWS, Board.COLUMNS));
        
        main.setBounds(0, 0, TILE_DIMENSION * Board.ROWS, TILE_DIMENSION * Board.COLUMNS);
        
        setPreferredSize(new Dimension(800, 800));
        
        add(main, BorderLayout.CENTER);
        
        listener = new GameDynamicsListener(this);
        
        addMouseListener(listener);
        
        addMouseMotionListener(listener);
        
        tiles = new JPanel[Board.ROWS][Board.COLUMNS];
        
        for(int i = 0; i < Board.ROWS; i++)
        {
            
            for(int j = 0; j < Board.COLUMNS; j++)
            {
                
                tiles[i][j] = new JPanel(new GridLayout(1,1));
                
                tiles[i][j].setPreferredSize(new Dimension(TILE_DIMENSION, TILE_DIMENSION));
                
                tiles[i][j].setSize(new Dimension(TILE_DIMENSION, TILE_DIMENSION));
                
                tiles[i][j].setBackground(determineTileColor(i, j));
                
                tiles[i][j].setVisible(true);
                
                main.add(tiles[i][j]);
                
            }
            
        }
        
        
    }
    
    public void onMove(int row, int column)
    {
        
        if(!moveIsOnGoing)
        {
            
            if(game.isMovedSourceValid(row, column))
            {
                
                moveIsOnGoing = true;
                
                highlightSourceTile(row, column, java.awt.Color.GREEN);
                
                sourceRow = row;
                
                sourceColumn = column;
                
            }
            else
            {
                
                moveIsOnGoing = false;
                
                processMove(sourceRow, sourceColumn, row, column);
                
                highlightSourceTile(row, column, determineTileColor(row, column));
                
            }
            
        }
        
    }
    
    public void processMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn)
    {
        
        boolean isMoveProcessed = game.processMove(sourceRow, sourceColumn, targetRow, targetColumn);
        
        if(isMoveProcessed)
        {
            
            Piece movedPiece = game.getBoard().GetTile(targetRow, targetColumn).getPiece();
            
            clearPiece(sourceRow, sourceColumn);
            
            drawPiece(targetRow, targetColumn, movedPiece.getType(), movedPiece.getColor());
            
        }
        
    }
    
    
    
}
