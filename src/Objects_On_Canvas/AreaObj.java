package Objects_On_Canvas;
import java.awt.*;
import java.util.ArrayList;

public class AreaObj extends Shape{
    protected String name;
    public Port[] ports = new Port[4];


    public AreaObj(){
        this.identity = "AreaObj";
    }

    @Override
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    protected void createPorts(){
        ports[0] = new Port(((p1.x + p2.x) / 2) - 6, p1.y - 12); // upper port
        ports[1] = new Port(p2.x, ((p1.y + p2.y) / 2) - 6);         // right port
        ports[2] = new Port(((p1.x + p2.x) / 2) - 6, p2.y);         // lower port
        ports[3] = new Port(p1.x - 12, ((p1.y + p2.y) / 2) - 6); // left  port
    }

    protected void showPorts(Graphics g){
        for(Port p : ports){
            p.draw(g);
        }
    }

    @Override
    public boolean hitCheck(Point p){
        if(p.x >= p1.x && p.x <= p2.x && p.y >= p1.y && p.y <= p2.y){
//            System.out.println("In AreaObj.hitCheck, it hits " + this);
            return true;
        }
        else {
//            System.out.println("doesn't hit " + this);
            return false;
        }
    }

    public Port CreateLine_hitCheck(Point p){  // return the nearest Port if p does hit this shape
        if (!hitCheck(p)){  // if p doesn't hit this AreaObj
            return null;
        }
        else {
//            System.out.println("in AreaObj.CreateLine_hitCheck, now finding the correct port...");
            Point middle = new Point();
            middle.x = (p1.x + p2.x) / 2;
            middle.y = (p1.y + p2.y) / 2;
            Point[] points = {new Point(p1.x, p1.y), new Point(p2.x, p1.y),
                    new Point(p2.x, p2.y), new Point(p1.x, p2.y)};

            for (int i = 0; i < points.length; i++) {
                Polygon triangle = new Polygon();
                int j = ((i + 1) % 4); //用%4來隨便選一個不是自己的0~3的數
                triangle.addPoint(points[i].x, points[i].y);
                triangle.addPoint(points[j].x, points[j].y);
                triangle.addPoint(middle.x, middle.y);

                if (triangle.contains(p)) {
//                    System.out.println("returning port" + i + " of " + this);
//                    System.out.println("location of this port: " + ports[i].point.getLocation());
                    return ports[i];
                }
            }
            System.out.println("No ports in neighborhood. Returning null.");
            return null;
        }
    }

    @Override
    public void translate(int dx, int dy){
        super.translate(dx, dy);
        for (Port p : ports) {
            p.translate(dx, dy);
        }

    }

    @Override
    public void rename(String name){
        this.name = name;
    }
}
