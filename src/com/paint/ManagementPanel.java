package com.paint;

import javax.swing.*;

import java.util.Objects;


public class ManagementPanel extends JPanel {
    private final String[] path = {"/icon/pen.png", "/icon/erase.png", "/icon/rect.png", "/icon/oval.png"};
    private final String[] menuName = {"Open", "New", "New...", "Save", "Save As..", "Close", "Clear"};
    private final String[] item = {"1", "2", "4", "6", "8", "10", "12", "14", "18"};
    private final String[] actionCommand = {Constants.PENCIL, Constants.ERASE, Constants.RECT, Constants.OVAL};
    private final JButton[] jButtons = new JButton[actionCommand.length];
    private final JMenuItem[] menuItem = new JMenuItem[menuName.length];
    private JComboBox<String>comboBox;


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
            menuItem[i] = new JMenuItem(menuName[i]);
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
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(null);
        toolBar.setBounds(1, 25, 500, 25);
        toolBar.setFloatable(false);
        int sizeofButtons = 24;
        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i] = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource(path[i]))));
            jButtons[i].setActionCommand(actionCommand[i]);
            toolBar.add(jButtons[i]);
            jButtons[i].setBounds(sizeofButtons * i, 0, sizeofButtons, sizeofButtons);
        }
        comboBox = new JComboBox<>(item);
        comboBox.setBounds(sizeofButtons * jButtons.length, 0, sizeofButtons * 2, sizeofButtons -1);
        toolBar.add(comboBox);
        add(toolBar);
    }


    public JButton getjButtons(int i) {
        return jButtons[i];
    }

    public int getButtonLength() {
        return jButtons.length;
    }

    public JMenuItem getMenuItemElement(int i) {
        return menuItem[i];
    }

    public int getMenuLength() {
        return menuItem.length;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

}
