package Objects_On_Canvas;

import java.awt.Point;

public class Line extends Shape{

    protected Port port1, port2;

    public Line(){}

    public Line(Point p1 ,Point p2){
        this.p1 = p1;
        this.p2 = p2;
        this.identity = "Line";
    }

    public void receivePorts(Port port1, Port port2) {
        this.port1 = port1;
        this.port2 = port2;
    }

    public void translate(int dx, int dy, Port correctPort){  // overloading translate()
        if(correctPort == port1){
            p1.translate(dx, dy);
        }
        else if (correctPort == port2){
            p2.translate(dx, dy);
        }
    }

    @Override
    public void translate(int dx, int dy){} // this won't be called
    @Override
    public void setSelected(boolean isSelected){} // Lines won't be selected for our current SPEC.

}
