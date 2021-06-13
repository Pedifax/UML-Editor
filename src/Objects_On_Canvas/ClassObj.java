package Objects_On_Canvas;

import java.awt.*;

public class ClassObj extends AreaObj {

    Font font = new Font(Font.DIALOG, Font.PLAIN, 14);
    public final int WIDTH = 110;
    public final int HEIGHT = 120;
    public final int RENDER = 25;

    public ClassObj(){
        this.name = "Class Object";
    }

    public ClassObj(Point point) {
        this();
//        System.out.println("classObj got a point at (" + point.x + ", " + point.y + ")." );
        p1.x = point.x;
        p1.y = point.y;
        p2.x = point.x + WIDTH;
        p2.y = point.y + HEIGHT;
        createPorts();

    }

    @Override
    public void draw(Graphics g){
        int temp = HEIGHT / 3;
        int y_level2 = p1.y + temp;
        int y_level3 = y_level2 + temp;
        g.drawRect(p1.x, p1.y, WIDTH, HEIGHT);
        g.drawLine(p1.x, y_level2, p2.x, y_level2);
        g.drawLine(p1.x, y_level3, p2.x, y_level3);

        // https://stackoverflow.com/questions/258486/calculate-the-display-width-of-a-string-in-java
        int fat = g.getFontMetrics(font).stringWidth(name);
        // 會回傳，在這個font底下輸入某個string，由左至右總共會佔多少空間
        double empty = (Math.abs(p2.x-p1.x) - fat)/2;
        g.setFont(font);
        g.drawString(name, p1.x + (int)empty, p1.y + RENDER);

        if(isSelected){ showPorts(g); }
    }
}
