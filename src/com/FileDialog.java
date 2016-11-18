package com;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;


public class FileDialog extends JFileChooser {
    private int ret;

    public FileDialog(FileFilter fileFilter, String name) {
        super();
        setFileFilter(fileFilter);
        setRequestFocusEnabled(true);
        setAcceptAllFileFilterUsed(false);
        ret = showDialog(null, name);

    }

    public int getRet() {
        return ret;
    }
}
