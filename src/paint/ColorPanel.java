package paint;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 02.10.13
 * Time: 14:14
 */
@SuppressWarnings("unchecked")
public class ColorPanel extends Panel {
    private final int[] colorsNumbers = {0x0,0xFFFFFF, 0xFF0000, 0x0000FF, 0x008000, 0xFFFF00, 0xFF00FF, 0x8B4513, 0xFFA500, 0x9932CC, 0xBDB76B};
    private final Canvas[] arrcanvas = new Canvas[colorsNumbers.length];
    private Button jb1, jb2;
    private int mbutton;


    @SuppressWarnings("unchecked")
    public ColorPanel() {
        super();
        FocusListener fl = new FListener();
        setLayout(null);
        setPanelLook();
        int y = 66;
        int x = 6;
        Color[] colors = new Color[colorsNumbers.length];
        int i;
        for (i = 0; i < colors.length; i++) colors[i] = new Color(colorsNumbers[i]);
        for (i = 0; i < arrcanvas.length; i++) {
            arrcanvas[i] = new Canvas();
            arrcanvas[i].setBounds(x, y + (i * 24), 20, 20);
            arrcanvas[i].setBackground(colors[i]);
            arrcanvas[i].addFocusListener(fl);
            add(arrcanvas[i]);
        }

    }

    private void setPanelLook() {
        JPanel pa;
        pa = new JPanel();
        pa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pa.setBounds(4, 4, 56, 56);
        pa.setLayout(null);
        jb1 = new Button("");
        jb2 = new Button("");
        jb1.setBounds(6, 6, 25, 25);
        jb2.setBounds(23, 23, 25, 25);
        jb1.setBackground(Color.BLACK);
        jb2.setBackground(Color.WHITE);
        jb1.setEnabled(false);
        jb2.setEnabled(false);
        pa.add(jb1);
        pa.add(jb2);
        add(pa);
    }

    public int getMbutton() {
        return mbutton;
    }

    public void setMbutton(int mbutton) {
        this.mbutton = mbutton;
    }

    public int getCanvasleght() {
        return arrcanvas.length;
    }

    public Canvas getCanvasElement(int i) {
        return arrcanvas[i];
    }

    public Color getColorbt1() {
        return jb1.getBackground();
    }

    public Color getColorbt2() {
        return jb2.getBackground();
    }

    private class FListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            for (Canvas canvas : arrcanvas) {
                if (e.getSource() == canvas) {
                    switch (getMbutton()) {
                        case 1 -> jb1.setBackground(canvas.getBackground());
                        case 3 -> jb2.setBackground(canvas.getBackground());
                    }

                }

            }

        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }
}

