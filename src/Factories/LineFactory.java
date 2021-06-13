package Factories;

import java.awt.Point;

import Objects_On_Canvas.*;

public class LineFactory extends RootFactory{
    @Override
    public Line create(String type, Point p1, Point p2){
        switch(type){
            case "Association":
                return new AssociationLine(p1, p2);
            case "Generalization":
                return new GeneralizationLine(p1, p2);
            case "Composition":
                return new CompositionLine(p1, p2);
            default:
                return null;
        }
    }
    public Shape create(String type, Point point){return null;}
}
