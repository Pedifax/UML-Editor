package Objects_On_Canvas;

import java.awt.*;

public class CompositionLine extends Line{

    public CompositionLine(){

    }

    public CompositionLine(Point p1, Point p2){
        super(p1, p2);
    }

    @Override
    public void draw(Graphics g){
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        int dx = p2.x - p1.x, dy = p2.y - p1.y;
        double D = Math.sqrt(dx*dx + dy*dy);
        int diamondW = 10, diamondH = 10;
        double xm = D - diamondW, xn = xm, ym = diamondH, yn = -diamondH, x;
        double sin = dy/D, cos = dx/D;

        x = xm*cos - ym*sin + p1.x;
        ym = xm*sin + ym*cos + p1.y;
        xm = x;

        x = xn*cos - yn*sin + p1.x;
        yn = xn*sin + yn*cos + p1.y;
        xn = x;

        double xq = (diamondH*2/D)*p1.x + ((D-diamondH*2)/D)*p2.x;
        double yq = (diamondH*2/D)*p1.y + ((D-diamondH*2)/D)*p2.y;

        int[] xpoints = {p2.x, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {p2.y, (int) ym, (int) yq, (int) yn};
        g.fillPolygon(xpoints, ypoints, 4);

    }
}
