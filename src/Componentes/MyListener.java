
package Componentes;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MyListener implements MouseListener, MouseMotionListener{

    private final Control cl;
    private int lastX;
    private int lastY;
    public MyListener(Control cl) {
        this.cl=cl;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        int difX=e.getX()-lastX;
        int difY=e.getY()-lastY;
        this.lastX=e.getX();
        this.lastY=e.getY();
        this.cl.moveImage(difX, difY);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        this.lastX=e.getX();
        this.lastY=e.getY();
        this.cl.setCursorCanvas(Cursor.MOVE_CURSOR);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        this.cl.setCursorCanvas(Cursor.HAND_CURSOR);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
