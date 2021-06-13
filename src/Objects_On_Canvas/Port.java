package Objects_On_Canvas;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Port {
    public static final int WIDTH = 12;
    public static final int HEIGHT =  12;
    public Point point;
    private ArrayList<Line> lines;

    public Port(){
        lines = new ArrayList<>();
    }

    public Port(int x, int y){
        this();
        point = new Point(x, y);
    }
    public Port(Point p){
        this();
        point = new Point(p);
    }

    public Point getCenter(){
        return new Point((point.x + WIDTH/2), (point.y + HEIGHT/2));
    }

    public void draw(Graphics g){
        g.setColor(new Color(0x92FFFFFF, true));
        g.fillRect(point.x, point.y, WIDTH, HEIGHT);
        g.setColor(new Color(0xC4FFFFFF, true));
        g.drawRect(point.x, point.y, WIDTH, HEIGHT);
        g.setColor(Color.white);
    }

    public void receiveLine(Line line){
        lines.add(line);
    }

    public void translate(int dx, int dy){
        point.x += dx;
        point.y += dy;

        for (Line line : lines) {
            line.translate(dx, dy, this);
        }
    }


}


