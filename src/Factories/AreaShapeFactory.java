package Factories;

import Objects_On_Canvas.AreaObj;
import Objects_On_Canvas.ClassObj;
import Objects_On_Canvas.Shape;
import Objects_On_Canvas.UseCase;

import java.awt.*;

public class AreaShapeFactory extends RootFactory{
    @Override
    public AreaObj create(String type, Point point){
        switch(type){
            case "Class":
                return new ClassObj(point);
            case "UseCase":
                return new UseCase(point);
            default:
                return null;
        }
    }
    public Shape create(String type, Point p1, Point p2){return null;}
}
