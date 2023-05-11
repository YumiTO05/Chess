/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import core.Color;
import core.PieceType;
import javax.swing.JFrame;

/**
 *
 * @author yumi
 */
public class GameFrame extends JFrame
{
    
    BoardPanel boardPanel;
    
    core.Game game;
    
    public GameFrame()
    {
        
        game = new core.Game();
        
        boardPanel = new BoardPanel(this.game);
        
        add(boardPanel);
        
        setResizable(false);
        
        pack();
        
        setVisible(true);
        
    }
    
}
