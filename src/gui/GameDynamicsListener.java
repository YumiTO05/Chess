/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author yumi
 */
public class GameDynamicsListener implements MouseListener, MouseMotionListener
{

    private final BoardPanel boardPanel;
    
    public GameDynamicsListener(BoardPanel boardpanel) 
    {
        
        boardPanel = boardpanel;
        
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        
        
        
    }

    @Override
    public void mousePressed(MouseEvent me) 
    {
        
        int x = me.getX();
        
        int y = me.getY();
        
        int row = y / boardPanel.TILE_DIMENSION;
        
        int column = x / boardPanel.TILE_DIMENSION;
        
        boardPanel.onMove(row, column);
        
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
        
        
        
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
        
        
        
    }

    @Override
    public void mouseExited(MouseEvent me) 
    {
        
        
        
    }

    @Override
    public void mouseDragged(MouseEvent me) 
    {
        
        
        
    }

    @Override
    public void mouseMoved(MouseEvent me) 
    {
        
        
        
    }
    
}
