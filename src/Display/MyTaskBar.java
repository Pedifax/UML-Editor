package Display;

import Modes.LineCreationMode;
import Modes.SelectionMode;
import Modes.AreaObjCreationMode;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

public class MyTaskBar extends JToolBar{

    private final int TaskRows = 6;
    private final int TsakColumns = 1;

    private MyCanvas canvas = MyCanvas.getInstance();

    private ArrayList<MyButton> buttons = new ArrayList<>();

    public MyTaskBar(){

        ImageIcon selectionIcon = new ImageIcon("Tool_images/select.png");
        ImageIcon associationIcon = new ImageIcon("Tool_images/associate.png");
        ImageIcon generalizationIcon = new ImageIcon("Tool_images/general.png");
        ImageIcon compositionIcon = new ImageIcon("Tool_images/composite.png");
        ImageIcon classIcon = new ImageIcon("Tool_images/class.png");
        ImageIcon usecaseIcon = new ImageIcon("Tool_images/usecase.png");

        buttons.add(new MyButton_Selection(selectionIcon));
        buttons.add(new MyButton_Line("Association", associationIcon));
        buttons.add(new MyButton_Line("Generalization", generalizationIcon));
        buttons.add(new MyButton_Line("Composition", compositionIcon));
        buttons.add(new MyButton_SimpleObj("Class", classIcon));
        buttons.add(new MyButton_SimpleObj("UseCase", usecaseIcon));

        this.setBackground(Color.darkGray);
        this.setLayout(new GridLayout(TaskRows,TsakColumns,0,0));
        this.setFloatable(false);
        for(MyButton btn : buttons) this.add(btn);
    }

    private void setEveryoneDarkGray(MyButton exception){ //按下某一按鈕後，只有他變黑色，其他都變回深灰色
        for(MyButton temp : buttons){
            if(temp == exception) continue;
            temp.setBackground(Color.darkGray);
            repaint();
        }
    }

    private class MyButton extends JButton{
        protected String buttonName;
        private MyButton(){
            this.setBackground(Color.darkGray);
            this.setOpaque(true);
            this.setFocusable(false);
            this.addActionListener(e -> {
                setBackground(Color.black);
                setEveryoneDarkGray(this);
            });
        }
    }

    private class MyButton_Selection extends MyButton{
        private MyButton_Selection(ImageIcon icon){
            this.buttonName = "selection";
            this.setIcon(icon);
            this.addActionListener(e -> canvas.setMode(new SelectionMode()));
        }
    }

    private class MyButton_Line extends MyButton{
        private MyButton_Line(String name, ImageIcon icon){
            this.buttonName = name;
            this.setIcon(icon);
            this.addActionListener(e -> canvas.setMode(new LineCreationMode(this.buttonName)));
        }
    }

    private class MyButton_SimpleObj extends MyButton{
        private MyButton_SimpleObj(String name, ImageIcon icon){
            this.buttonName = name;
            this.setIcon(icon);
            this.addActionListener(e -> canvas.setMode(new AreaObjCreationMode(this.buttonName)));
        }
    }
}
