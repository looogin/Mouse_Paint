package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 02.10.13
 * Time: 11:43
 */
public class PaintPanel extends JPanel {
    private int lastX;
    private int lastY;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.WHITE;
    private BufferedImage imag, imag2;
    private Boolean noimage = false;
    private Boolean noimage2 = false;
    private float WLine;
    private String typeofdraw = "pencil";
    private Color colordraw;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Graphics2D g2d;

    public PaintPanel() {
        super();
        MouseAdapter listener = new Listener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        imag = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        imag2 = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        if (!noimage) {
            g2d = imag.createGraphics();
            g2d.setColor(new Color(255, 255, 255));
            g2d.fillRect(0, 0, 500, 500);
            noimage = true;
        }
        g.drawImage(imag, 0, 0, this);
        update(g);
    }

    @Override
    public void update(Graphics g) {
        if (!noimage2) {
            g2d = imag2.createGraphics();
        }
        drawShape();
    }

    public void clear() {
        if (noimage) {
            g2d = (Graphics2D) imag.getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 480, 480);
            repaint();
        }
    }

    public void saveImage(File f) {
        try {
            ImageIO.write(imag, "png", f);
        } catch (IOException ignored) {
        }
    }

    public void loadImage(File f) {
        try {
            imag = ImageIO.read(f);
            repaint();
        } catch (IOException ignored) {
        }
    }

    protected void setPosition(int x, int y) {
        lastX = x;
        lastY = y;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color col) {
        color1 = col;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color col) {
        color2 = col;
    }

    public float getWLine() {
        return WLine;
    }

    public void setWline(float wline) {
        WLine = wline;
    }

    public String getTypeofdraw() {
        return typeofdraw;
    }

    public void setTypeofdraw(String typeofdraw) {
        this.typeofdraw = typeofdraw;
    }

    private void paintPencil(int x, int y, Graphics2D g2, Color col) {
        g2.setColor(col);
        g2.setStroke(new BasicStroke(getWLine(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(lastX, lastY, x, y);
    }

    private void eraseDraw(int x, int y, Graphics2D g2) {
        g2.setStroke(new BasicStroke(getWLine() + 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2.setColor(Color.WHITE);
        g2.drawLine(lastX, lastY, x, y);
    }

    private void drawShape() {
        int ltX = Math.min(x1, x2);
        int ltY = Math.min(y1, y2);
        int rbX = Math.max(x1, x2);
        int rbY = Math.max(y1, y2);
        g2d.setStroke(new BasicStroke(getWLine(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(colordraw);
        if (noimage2) {
            switch (getTypeofdraw()) {
                case "rect":
                    g2d.drawRect(ltX, ltY, rbX - ltX, rbY - ltY);
                    break;
                case "oval":
                    g2d.drawOval(ltX, ltY, rbX - ltX, rbY - ltY);
                    break;
            }
        }

    }

    private void startDrawShape(MouseEvent e) {
        noimage2 = true;
        x1 = e.getX();
        y1 = e.getY();
        x2 = x1;
        y2 = y1;
        g2d.setColor(colordraw);
        switch (getTypeofdraw()) {
            case "rect":
                g2d.drawRect(x1, y1, 0, 0);
                break;
            case "oval":
                g2d.drawOval(x1, y1, 0, 0);
                break;
        }
        repaint();
    }

    private void endDrawShape() {
        int ltX = Math.min(x1, x2);
        int ltY = Math.min(y1, y2);
        int rbX = Math.max(x1, x2);
        int rbY = Math.max(y1, y2);
        x1 = -100;
        x2 = -100;
        y2 = -100;
        y1 = -100;
        g2d = (Graphics2D) imag.getGraphics();
        g2d.setColor(colordraw);
        g2d.setStroke(new BasicStroke(getWLine(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        switch (getTypeofdraw()) {
            case "rect":
                g2d.drawRect(ltX, ltY, rbX - ltX, rbY - ltY);
                break;
            case "oval":
                g2d.drawOval(ltX, ltY, rbX - ltX, rbY - ltY);
                break;
        }
        repaint();
    }

    private class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            setPosition(e.getX(), e.getY());
            switch (e.getButton()) {
                case 1:
                    colordraw = getColor1();
                    break;
                case 3:
                    colordraw = getColor2();
            }
            if (getTypeofdraw().equals("rect") || getTypeofdraw().equals("oval")) startDrawShape(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (getTypeofdraw().equals("rect") || getTypeofdraw().equals("oval")) endDrawShape();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            g2d = (Graphics2D) imag.getGraphics();
            switch (getTypeofdraw()) {
                case "pencil":
                    paintPencil(e.getX(), e.getY(), g2d, colordraw);
                    setPosition(e.getX(), e.getY());
                    repaint();
                    break;
                case "erase":
                    eraseDraw(e.getX(), e.getY(), g2d);
                    setPosition(e.getX(), e.getY());
                    repaint();
                    break;
                case "rect":
                    x2 = e.getX();
                    y2 = e.getY();
                    repaint();
                case "oval":
                    x2 = e.getX();
                    y2 = e.getY();
                    repaint();
                case "no":
                    setPosition(e.getX(), e.getY());
                    break;
            }
        }
    }
}
