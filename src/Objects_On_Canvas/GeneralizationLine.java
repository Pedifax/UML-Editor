package Objects_On_Canvas;

import java.awt.*;

public class GeneralizationLine extends Line{

    public GeneralizationLine(){}

    public GeneralizationLine(Point p1, Point p2){
        super(p1, p2);
    }

    @Override
    public void draw(Graphics g) {
        int arrowW = 10, arrowH = 10;
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;

        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - arrowW;
        double xn = xm;
        double ym = arrowH;
        double yn = -arrowH, x;
        double sin = dy/D;
        double cos = dx/D;

        x = xm*cos - ym*sin + p1.x;
        ym = xm*sin + ym*cos + p1.y;
        xm = x;
        x = xn*cos - yn*sin + p1.x;
        yn = xn*sin + yn*cos + p1.y;
        xn = x;

        int[] Xcoordinates = {p2.x, (int) xm, (int) xn};
        int[] Ycoordinates = {p2.y, (int) ym, (int) yn};

        g.fillPolygon(Xcoordinates, Ycoordinates, 3); //箭頭的三角形
    }
}
