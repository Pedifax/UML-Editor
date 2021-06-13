package Objects_On_Canvas;

import Display.MyCanvas;

import java.awt.*;
import java.util.ArrayList;

public class Group extends Shape{
    MyCanvas canvas = MyCanvas.getInstance();
    private ArrayList<Shape> shapeList;
    private final int PERIPHERY = Port.WIDTH;

    public Group(){
        shapeList = new ArrayList<>();
        this.identity = "Group";
    }

    @Override
    public void ungroup(){
        ArrayList<Shape> shapes = canvas.getShapes();
        for (Shape s : shapeList){
            shapes.add(s);
        }
        shapeList.clear();
        shapes.remove(this);
        canvas.repaint();
    }

    @Override
    public void addToGroup(Shape s){
        shapeList.add(s);
//        System.out.println("added an object: " + s + " @ (" + s.p1.x + "," + s.p1.y + "), (" + s.p2.x + ", " + s.p2.y + ")");
    }

    @Override
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
        for (Shape s : shapeList){
            s.setSelected(isSelected);
        }
    }

    @Override
    public boolean hitCheck(Point p){
        if(p.x >= p1.x && p.x <= p2.x && p.y >= p1.y && p.y <= p2.y) return true;
        else return false;
    }

    private void createCover(){
        p1.x = 9999; p1.y = 9999; p2.x = -1; p2.y = -1;

        for (Shape s : shapeList){
            if (s.p1.x < p1.x){
                p1.x = s.p1.x;
            }
            if (s.p1.y < p1.y){
                p1.y = s.p1.y;
            }
            if (s.p2.x > p2.x){
                p2.x = s.p2.x;
            }
            if (s.p2.y > p2.y){
                p2.y = s.p2.y;
            }
        }
        p1.x -= PERIPHERY; p1.y -= PERIPHERY; p2.x += PERIPHERY; p2.y += PERIPHERY;

//        System.out.println("cover: (" + p1.x + "," + p1.y + "), (" + p2.x + ", " + p2.y + ")");
    }

    @Override
    public void translate(int dx, int dy) {
        for (Shape s : shapeList) {
            s.translate(dx, dy);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Shape s : shapeList) {
            s.draw(g);
        }
        // draw a rectangle that covers everything in this GroupObj IF this GroupObj is selected.
        if (isSelected) {
            createCover();
//            g.setColor(new Color(229, 175, 85, 65));
            Graphics2D g2D = (Graphics2D) g;
            g.setColor(new Color(232, 28, 124, 180));
            GradientPaint coolColor = new GradientPaint(
                    (float)p1.x,
                    (float)((p1.y+p2.y)/2),
                    new Color(243, 195, 43, 57),
                    (float)p2.x,
                    (float)((p1.y+p2.y)/2),
                    new Color(222, 68, 141, 109));
            g.drawRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y); // int x, int y, int width, int height
            g2D.setPaint(coolColor);
            g2D.fillRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y); // int x, int y, int width, int height


            g.setColor(Color.white);
            g2D.setColor(Color.white);
        }
    }
}
