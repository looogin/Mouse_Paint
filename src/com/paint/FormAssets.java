package com.paint;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;
import static com.paint.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 02.10.13
 * Time: 11:14
 */

public class FormAssets extends JFrame {
    private final FileFilter fileFilter;
    private File file = new File("");
    private final PaintPanel paintPanel = new PaintPanel();
    private final ColorPanel colorPanel = new ColorPanel();
    private final ManagementPanel managementPanel = new ManagementPanel();


    public FormAssets(String str) {
        super(str);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        MouseAdapter ml = new MListener();
        fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
            }
            @Override
            public String getDescription() {
                return "Image file " + ".png";
            }
        };
        ActionListener ac = new AListener();
        contentPane.setLayout(null);
        paintPanel.setBounds(0, 50, 430, 450);
        colorPanel.setBounds(430, 50, 70, 450);
        managementPanel.setBounds(0, 0, 500, 50);
        contentPane.add(paintPanel);
        contentPane.add(colorPanel);
        contentPane.add(managementPanel);
        for (int i = 0; i < managementPanel.getMenuLength(); i++) managementPanel.getMenuItemElement(i).addActionListener(ac);
        for (int i = 0; i < managementPanel.getButtonLength(); i++) managementPanel.getJButton(i).addActionListener(ac);
        for (int i = 0; i < colorPanel.getCanvaslength(); i++) colorPanel.getCanvasElement(i).addMouseListener(ml);
        managementPanel.getComboBox().addActionListener(ac);
    }

    private class AListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog dialog;
            paintPanel.setWline(Integer.parseInt(Objects.requireNonNull(managementPanel.getComboBox().getSelectedItem()).toString()));
            switch (e.getActionCommand()) {
                case "Open" -> {
                    dialog = new FileDialog(fileFilter, e.getActionCommand());
                    if (dialog.getRet() == JFileChooser.APPROVE_OPTION) {
                        file = dialog.getSelectedFile();
                        paintPanel.loadImage(file);
                        setTitle("SimplePaint" + "  " + file);
                    }
                }
                case "Save" -> {
                    if (file.exists()) paintPanel.saveImage(file);
                    else {
                        JDialog jd = new JDialog();
                        jd.setVisible(true);
                        jd.setModal(true);
                        jd.setTitle("Ошибка");
                        jd.setLocation(525, 425);
                        jd.setSize(50, 50);
                        jd.add(new JLabel(" Файл не найден"));
                    }
                }
                case "Save As.." -> {
                    dialog = new FileDialog(fileFilter, e.getActionCommand());
                    if (dialog.getRet() == JFileChooser.APPROVE_OPTION) {
                        file = dialog.getSelectedFile();
                        String filePath = file.getPath();
                        if (!filePath.toLowerCase().endsWith(".png")) {
                            file = new File(filePath + ".png");
                        }
                        setTitle("SimplePaint" + "  " + ":" + file);
                        paintPanel.saveImage(file);
                    }
                }
                case CLOSE -> System.exit(0);
                case CLEAR -> paintPanel.clear();
                case PENCIL, ERASE, RECT, OVAL -> {
                    paintPanel.setTypeofDraw(e.getActionCommand());
                    managementPanel.getJButtonsByName(e.getActionCommand()).setSelected(true);
                }
            }

        }
    }


    private class MListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            colorPanel.setMbutton(e.getButton());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            paintPanel.setColor1(colorPanel.getColorbt1());
            paintPanel.setColor2(colorPanel.getColorbt2());

        }
    }
}

