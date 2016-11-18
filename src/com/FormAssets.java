package com;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 02.10.13
 * Time: 11:14
 */
public class FormAssets extends JFrame {
    private ActionListener ac;
    private FileFilter ff;
    private File file = new File("");
    private PaintPanel p1 = new PaintPanel();
    private ColorPanel p2 = new ColorPanel();
    private ManagementPanel p3 = new ManagementPanel();
    private FileDialog fd;
    private MouseAdapter ml;


    public FormAssets(String str) {
        super(str);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        ml = new MListener();
        ff = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Image file " + ".png";
            }
        };
        ac = new AListener();
        contentPane.setLayout(null);
        p1.setBounds(0, 50, 430, 450);
        p2.setBounds(430, 50, 70, 450);
        p3.setBounds(0, 0, 500, 50);
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        for (int i = 0; i < p3.getMenuLenght(); i++) p3.getMenuItemElement(i).addActionListener(ac);
        for (int i = 0; i < p3.getButtonLenght(); i++) p3.getjButtons(i).addActionListener(ac);
        for (int i = 0; i < p2.getCanvasleght(); i++) p2.getCanvasElement(i).addMouseListener(ml);
        p3.getCb().addActionListener(ac);
    }

    private class AListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            p1.setWline(Float.parseFloat(p3.getCb().getSelectedItem().toString()));
            switch (e.getActionCommand()) {
                case "Open":
                    fd = new FileDialog(ff, e.getActionCommand());
                    if (fd.getRet() == JFileChooser.APPROVE_OPTION) {
                        file = fd.getSelectedFile();
                        p1.loadImage(file);
                        setTitle("SimplePaint" + "  " + file);
                    }
                    break;
                case "Save":
                    if (file.exists()) p1.saveImage(file);
                    else {
                        JDialog jd = new JDialog();
                        jd.setVisible(true);
                        jd.setModal(true);
                        jd.setTitle("Ошибка");
                        jd.setLocation(525, 425);
                        jd.setSize(50, 50);
                        jd.add(new JLabel(" Файл ненайден"));
                    }
                    break;
                case "Save As..":
                    fd = new FileDialog(ff, e.getActionCommand());
                    if (fd.getRet() == JFileChooser.APPROVE_OPTION) {
                        file = fd.getSelectedFile();
                        String filePath = file.getPath();
                        if (!filePath.toLowerCase().endsWith(".png")) {
                            file = new File(filePath + ".png");
                        }
                        setTitle("SimplePaint" + "  " + ":" + file);
                        p1.saveImage(file);
                    }
                    break;
                case "Close":
                    System.exit(0);
                    break;
                case "Clear":
                    p1.clear();
                    break;
                case "pencil":
                    p1.setTypeofdraw(e.getActionCommand());
                    break;
                case "erase":
                    p1.setTypeofdraw(e.getActionCommand());
                    break;
                case "rect":
                    p1.setTypeofdraw(e.getActionCommand());
                    break;
                case "oval":
                    p1.setTypeofdraw(e.getActionCommand());
                    break;
            }

        }
    }


    private class MListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            p2.setMbutton(e.getButton());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            p1.setColor1(p2.getColorbt1());
            p1.setColor2(p2.getColorbt2());

        }
    }
}

