package Objects_On_Canvas;

import java.awt.Point;
import java.awt.Graphics;

public class Shape {
    public String identity;   // 每一個物件都這樣用，就能夠在hit時用這個field來判斷到底是打到誰了
    // 如果是class，就 String identity = "class_object
    public Point p1, p2; // p1 := the point at the upper left corner. p2 := the point at the lower right corner.
    public boolean isSelected = false;

    public Shape(){
        this.p1 = new Point();
        this.p2 = new Point();
    }

    public void rename(String name){} // percolated up method. Should only be implemented in AreaObj class. (Percolated up method)
    public void draw(Graphics g){}
    public void setSelected(boolean isSelected){}  // percolated up mehtod
    public boolean hitCheck(Point p){
        return false;
    }
    public Port CreateLine_hitCheck(Point p){ return null; }

    public void addToGroup(Shape s){} // percolated up method
    public void ungroup(){} // percolated up method

    public void translate(int dx, int dy){
        p1.x += dx;
        p1.y += dy;
        p2.x += dx;
        p2.y += dy;
    }
}
