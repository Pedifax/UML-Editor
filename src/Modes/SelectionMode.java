package Modes;

import Objects_On_Canvas.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectionMode extends Mode{

    private Point p1 = new Point(); // starting point
    private Point p2 = new Point(); // ending point
    private ArrayList<Shape> shapes = canvas.getShapes();
    private Shape theChosenOne;
    int dx, dy;

    public SelectionMode(){
        this.name = "SelectionMode";
        this.type = "doesn't need a type";
    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvas.refresh();
        p1 = e.getPoint();
        System.out.println(p1.toString());
        theChosenOne = findChosenShape(p1); // may be a Shape or a Group object
        if (theChosenOne != null) {
            canvas.setSelectedShape(theChosenOne);
        }
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ///////////// 2 parts : drag to create a rectangle area to select objects; Drag a selected object /////////////
        p2 = e.getPoint();
        dx = e.getX() - p1.x;  // dx := difference in x axis
        dy = e.getY() - p1.y;

        // PART 1: drag to create a rectangle area to select objects
        if (theChosenOne == null){
            if(dx > 0 && dy > 0){
                canvas.setDraggedArea(p1.x, p1.y, dx, dy);
            }
            else if(dx > 0 && dy < 0){
                canvas.setDraggedArea(p1.x, p1.y + dy, dx, -dy);
            }
            else if(dx < 0 && dy < 0){
                canvas.setDraggedArea(p1.x + dx, p1.y  + dy, -dx, -dy);
            }
            else{
                canvas.setDraggedArea(p1.x + dx, p1.y, -dx, dy);
            }
            ArrayList<Shape> ThoseChosenOnes = findChosenShape(canvas.getDraggedArea());
            canvas.setSelectedShape(ThoseChosenOnes);
        }

        // PART 2. Drag a selected object
        else{
            theChosenOne.translate(dx, dy);

            /// These 2 lines of codes let the LAST DRAGGED object be at the top of the canvas,
            //  always getting the mouseEvent when overlapping with other objects
            canvas.getShapes().remove(theChosenOne);
            canvas.addShape(theChosenOne);

            p1 = p2;
        }
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.resetDraggedArea();
        canvas.repaint();
    }

    private Shape findChosenShape(Point p){

        for (int i = shapes.size()-1; i >= 0; i--){  // Last In First Selected

            Shape shape = shapes.get(i);
            if(shape.hitCheck(p)){
                return shape;
            }
        }
        return null;
    }

    private ArrayList<Shape> findChosenShape(Rectangle draggedArea) {

        ArrayList<Shape> res = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (draggedArea.contains(shape.p1) && draggedArea.contains(shape.p2)) {
//                System.out.println("drag selected " + shape); // ok
                res.add(shape);
            }
        }
        return res;
    }
}
