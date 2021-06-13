package Objects_On_Canvas;

import java.awt.*;

public class AssociationLine extends Line{

    public AssociationLine(){
    }

    public AssociationLine(Point p1, Point p2){
        super(p1, p2);
    }

    @Override
    public void draw(Graphics g){
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
