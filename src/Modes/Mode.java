package Modes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import Display.MyCanvas;

public class Mode implements MouseListener, MouseMotionListener {

    public String name, type;

    public void showInfo(){
        System.out.println("---------- " + name + ", type: " + type + " ----------");
    }

    MyCanvas canvas = MyCanvas.getInstance();

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}


    // don't need these atm
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
