package com;

import javax.swing.*;

import static com.Constants.*;

@SuppressWarnings("unchecked")
public class ManagementPanel extends JPanel {
    private String[] path = {"/icon/pen.png", "/icon/erase.png", "/icon/rect.png", "/icon/oval.png"};
    private String[] menuname = {"Open", "New", "New...", "Save", "Save As..", "Close", "Clear"};
    private String[] item = {"1", "2", "4", "6", "8", "10", "12", "14", "18"};
    private String[] actioncommand = {PENCIL, ERASE, RECT, OVAL};
    private JButton[] jButtons = new JButton[actioncommand.length];
    private JMenuItem[] menuItem = new JMenuItem[menuname.length];
    private JComboBox cb;

    public ManagementPanel() {
        super();
        setLayout(null);
        setMenu();
        setToolBar();
    }

    private void setMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(menuname[i]);
            menu.add(menuItem[i]);
            if (i == 0) menu.add(new JSeparator());
            if (i == 2) menu.add(new JSeparator());
            if (i == 4) menu.add(new JSeparator());
        }
        menu2.add(menuItem[6]);
        menubar.add(menu);
        menubar.add(menu2);
        menubar.setBounds(1, 0, 500, 25);
        add(menubar);
    }

    private void setToolBar() {
        int h = 24;
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(null);
        toolBar.setBounds(1, 25, 500, 25);
        toolBar.setFloatable(false);
        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i] = new JButton(new ImageIcon(getClass().getResource(path[i])));
            jButtons[i].setActionCommand(actioncommand[i]);
            toolBar.add(jButtons[i]);
            jButtons[i].setBounds(h * i, 0, h, 24);
        }
        cb = new JComboBox(item);
        cb.setBounds(h * jButtons.length, 0, h * 2, 23);
        toolBar.add(cb);
        add(toolBar);
    }


    public JButton getjButtons(int i) {
        return jButtons[i];
    }

    public int getButtonLenght() {
        return jButtons.length;
    }

    public JMenuItem getMenuItemElement(int i) {
        return menuItem[i];
    }

    public int getMenuLenght() {
        return menuItem.length;
    }

    public JComboBox getCb() {
        return cb;
    }

}
