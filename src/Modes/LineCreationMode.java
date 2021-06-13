package Modes;

import Factories.LineFactory;
import Objects_On_Canvas.Line;
import Objects_On_Canvas.Port;
import Objects_On_Canvas.Shape;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LineCreationMode extends Mode{

    private LineFactory factory;
    private Point p1 = new Point(); // starting point
    private Point p2 = new Point(); // ending point
    private ArrayList<Shape> shapes = canvas.getShapes();
    private Shape startingShape, endingShape;
    private Port startingPort, endingPort;

    public LineCreationMode(String type){
        statusReset();
        this.name = "LineCreationMode";
        this.type = type;
        factory = new LineFactory();
    }

    private void statusReset(){
        startingShape = null;
        endingShape = null;
        startingPort = null;
        endingPort = null;
    }

    @Override
    public void mousePressed(MouseEvent e){
        statusReset();
        p1 = e.getPoint();
        p1 = findValidPoint(p1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(p1 != null) {
            p2 = e.getPoint();
            Line line = factory.create(this.type, p1, p2);
            canvas.setTempLine(line);
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(p1 != null){
            p2 = findValidPoint(e.getPoint());

            if(p2 != null){
                Line newLine = factory.create(this.type, p1, p2);
                newLine.receivePorts(startingPort, endingPort);
                startingPort.receiveLine(newLine);
                endingPort.receiveLine(newLine);
                canvas.addShape(newLine);
                System.out.println("added a line from " + startingPort + " of " + startingShape + " to " + endingPort + " of " + endingShape);
            }
        }
        canvas.removeTempLine();
        canvas.repaint();
    }

    private Point findValidPoint(Point p){

        for (int i = shapes.size()-1; i >= 0; i--){  // Last In First Picked

            Shape shape = shapes.get(i);
            Port port = shape.CreateLine_hitCheck(p);

            if(port != null){  // port1 will always be "null" for a groupObj or a LineObj, or when p1 hit nothing
                if(startingShape == null){
                    startingShape = shape;
                    startingPort = port;
                }
                else{
                    if(shape != startingShape){
                        endingShape = shape;
                        endingPort = port;
                    }
                    else{
                        System.out.println("can't connect to the same object");
                        return null;
                    }
                }
                return port.getCenter();
            }
        }
        return null;
    }
}
