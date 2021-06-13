package Display;

import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;


public class MyFrame extends JFrame{
    private Container cp;
    private MyTaskBar taskBar;
    private MyMenuBar menuBar;
    private MyCanvas canvas;

    public MyFrame(){
        taskBar = new MyTaskBar();
        menuBar = new MyMenuBar();
        canvas = MyCanvas.getInstance();
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.setBackground(Color.DARK_GRAY);
        cp.add(taskBar, BorderLayout.WEST);
        cp.add(canvas, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon("Frame_images/bro.png");
        this.setIconImage(icon.getImage());
        this.setTitle("106501005 UML Editor");
        this.setJMenuBar(menuBar);
        this.setSize(1300, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}
