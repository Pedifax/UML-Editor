package Modes;

import Factories.AreaShapeFactory;
import Factories.RootFactory;
import java.awt.event.MouseEvent;

public class AreaObjCreationMode extends Mode{

    private RootFactory factory;

    public AreaObjCreationMode(String type){
        this.name = "SimpleObjCreationMode";
        this.type = type;
        factory = new AreaShapeFactory();
    }

    @Override
    public void mousePressed(MouseEvent e){
        canvas.addShape(factory.create(this.type, e.getPoint()));
        canvas.repaint();
    }
}
