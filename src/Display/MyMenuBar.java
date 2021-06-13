package Display;

import Objects_On_Canvas.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyMenuBar extends JMenuBar {
    JMenu editMenu;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem ReName;
    JMenuItem Group;
    JMenuItem UnGroup;
    private MyCanvas canvas = MyCanvas.getInstance();

    public MyMenuBar(){


        Color bro_color_lucid = new Color(239, 7, 114, 173); //alpha可以調整透明度

        editMenu = new JMenu("Edit");
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        ReName = new JMenuItem("ReName");
        Group = new JMenuItem("Group");
        UnGroup = new JMenuItem("UnGroup");

        ReName.addActionListener(e -> {

            ArrayList<Shape> chosenShapes = canvas.getSelectedShape();

            //  the below condition check ensures JPanel only pops up when the user is allowed to rename an object,
            //  rather than pop out everytime the user hit "rename"
            if (chosenShapes.size() == 1 && !chosenShapes.get(0).identity.equals("Group")){
                JPanel jpn = new JPanel();
                JTextField jtf =  new JTextField("");
                JFrame window = new JFrame("Wanna change the name?");
                JButton ok = new JButton("Change!");
                JButton cancel = new JButton("Cancel!");

                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        canvas.rename(jtf.getText());
                        window.dispose();
                    }
                });

                cancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        window.dispose();
                    }
                });

                window.setSize(500, 200);
                window.setLayout(new BorderLayout());

                jpn.setLayout(new FlowLayout());
                jpn.add(ok);
                jpn.add(cancel);

                window.add(jtf, BorderLayout.CENTER);
                window.add(jpn, BorderLayout.SOUTH);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            }
        });

        Group.addActionListener(e -> {
            canvas.group();
        });

        UnGroup.addActionListener(e -> {
            canvas.ungroup();
        });

        editMenu.add(ReName);
        editMenu.add(Group);
        editMenu.add(UnGroup);
        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
        this.setBackground(bro_color_lucid);
    }
}
