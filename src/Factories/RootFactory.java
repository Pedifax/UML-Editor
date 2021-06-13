package Factories;

import java.awt.Point;
import Objects_On_Canvas.Shape;

public abstract class RootFactory {
    public abstract Shape create(String type, Point point);
    public abstract Shape create(String type, Point p1, Point p2);
}
