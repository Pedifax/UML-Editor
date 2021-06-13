package Display;

import javax.swing.JPanel;
import Modes.*;
import Objects_On_Canvas.*;
import Objects_On_Canvas.Shape;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyCanvas extends JPanel{

    private static MyCanvas uniqueInstance;                      // for single-click selection
    private Rectangle draggedArea = new Rectangle();
    private ArrayList<Shape> selectedShape = new ArrayList<>();  // for drag-selected shapes ( upcoming groupObj members )
    private ArrayList<Shape> shapes = new ArrayList<>();         // contains every object that's on the canvas
    private Line tempLine = new Line();

    private MyCanvas(){}

    public static MyCanvas getInstance(){
        if (uniqueInstance == null) {
            synchronized (MyCanvas.class){
                if(uniqueInstance == null){
                    uniqueInstance = new MyCanvas();
                }
            }
        }
        return uniqueInstance;
    }

    public void setMode(Mode mode){
        for (MouseListener ML : this.getMouseListeners()) { //這樣不用記住以前他叫啥名字
            this.removeMouseListener(ML);
        }
        for (MouseMotionListener MML : this.getMouseMotionListeners()) {
            this.removeMouseMotionListener(MML);
        }
        addMouseListener(mode);     // 看這裡需不需要casting up
        addMouseMotionListener(mode);

        mode.showInfo(); // just for checking
        refresh();
        repaint();
    }

    public void addShape(Shape newbie){
        shapes.add(newbie);
//        System.out.println("added a " + newbie.identity + ". Now len of shapes = " + shapes.size());
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public ArrayList<Shape> getSelectedShape() {
        return selectedShape;
    }

    public void rename(String name) {
        if (selectedShape.size() == 1) {
            selectedShape.get(0).rename(name);
            //記得percolating up，讓line, group不能rename
            repaint();
        }
    }

    //////////////////////////// Line Creation Mode ///////////////////////////////

    public void setTempLine(Line line) {
        this.tempLine = line;
    }

    public void removeTempLine(){
        this.tempLine = null;
    }

    //////////////////////////// Line Creation Mode ///////////////////////////////
    ////////////////////////////// Selection Mode /////////////////////////////////

    public void setSelectedShape(Shape theChosen) {
        this.selectedShape.clear();
        theChosen.setSelected(true);
        this.selectedShape.add(theChosen);
    }

    public void setSelectedShape(ArrayList<Shape> dragSelectedObjects) {   /////// 尚未大改之前!!!!!!
        resetSelectedShape();
        if (dragSelectedObjects.size() > 0){
            for(Shape s : dragSelectedObjects){
                selectedShape.add(s);
                s.setSelected(true);
            }
//            System.out.println(selectedShape.size() + " object(s) is(are) selected");
        }
    }

    public void resetSelectedShape(){
        for(Shape s : selectedShape){
            s.setSelected(false);
        }
        selectedShape.clear();// testing
    }

    public void refresh(){
        if(shapes.size() > 0){
            for (Shape s : shapes) {
                s.setSelected(false);
            }
        }
        selectedShape.clear();
        setDraggedArea(0,0,0,0);
    }

    public void setDraggedArea(int x, int y, int width, int height){
        draggedArea.setBounds(x, y, width, height);
    }

    public void resetDraggedArea(){
        setDraggedArea(0,0,0,0);
    }

    public Rectangle getDraggedArea(){
        return draggedArea;
    }

    ////////////////////////////// Selection Mode /////////////////////////////////
    //////////////////////////////    Grouping    /////////////////////////////////

    public void group() {
        //用indexOf找出selectedShape的index，再從shapes中remove掉，並加入一個group object即可
        if (selectedShape.size() > 1){
            Group newGroup = new Group();
            for (Shape s : selectedShape){
                newGroup.addToGroup(s);
            }
            for (Shape s : selectedShape){
                shapes.remove(s);
            }
            resetSelectedShape();
            setSelectedShape(newGroup); // make the new GroupObj appear as a selected object shortly after its creation

            shapes.add(newGroup);
//            System.out.println("num of selectedShape = " + selectedShape.size());
//            System.out.println("num of shapes = " + shapes.size());
            repaint();
        }
    }

    public void ungroup() {
        if (selectedShape.size() == 1) {    // SPEC: ungroup function available only when a single object is selected
            selectedShape.get(0).ungroup(); // percolated up method. Should only work for group objects
        }
    }

    //////////////////////////////    Grouping    /////////////////////////////////
    //////////////////////////////     paint()    /////////////////////////////////

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 1300, 700);
        g.setColor(Color.white);
        Graphics2D g2D = (Graphics2D) g;

        for(Shape s : shapes){
            s.draw(g);
        }

        if(tempLine != null){ tempLine.draw(g); }

        if(!draggedArea.isEmpty()){
            g.setColor(new Color(232, 28, 124, 180));
            g.drawRect(draggedArea.x, draggedArea.y, draggedArea.width, draggedArea.height);
            GradientPaint coolColor = new GradientPaint(
                    (float)draggedArea.getX(),
                    (float)draggedArea.getY(),
                    new Color(243, 195, 43, 57),
                    (float)draggedArea.getX() + (float)draggedArea.getWidth(),
                    (float)draggedArea.getY() + (float)draggedArea.getHeight(),
                    new Color(232, 28, 124, 109));
//                    new Color(245, 174, 174, 109),
//                    new Color(232, 28, 124, 89));

            g2D.setPaint(coolColor);
            g2D.fillRect(draggedArea.x, draggedArea.y, draggedArea.width, draggedArea.height);

            g2D.setPaint(Color.white);
            g.setColor(Color.white);
        }

    }
}
