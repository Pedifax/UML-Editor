package Objects_On_Canvas;

import java.awt.*;

public class UseCase extends AreaObj {

    Font font = new Font(Font.DIALOG, Font.PLAIN, 14);
    public final int WIDTH = 120;
    public final int HEIGHT = 90;
    public final int RENDER = 50;

    public UseCase(){
        this.name = "An UseCase";
    }
    public UseCase(Point point) {
        this();
        p1.x = point.x;
        p1.y = point.y;
        p2.x = point.x + WIDTH;
        p2.y = point.y + HEIGHT;
        createPorts();
    }

    @Override
    public void draw(Graphics g){
        g.drawOval(p1.x, p1.y, WIDTH, HEIGHT);
        // https://stackoverflow.com/questions/258486/calculate-the-display-width-of-a-string-in-java
        int fat = g.getFontMetrics(font).stringWidth(name);
        double empty = (Math.abs(p2.x-p1.x) - fat)/2;
        g.setFont(font);
        g.drawString(name, p1.x + (int)empty, p1.y + RENDER);

        if(isSelected){ showPorts(g); }
    }

}
