package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("AMX");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    public void reloadContent() {
        var loadedSet = Application.getInstance().getDataManager().getLoadedSet();

        if(loadedSet != null) {
            // TODO: display data editor
            throw new IllegalStateException("UNIMPLEMENTED");
        } else {
            setContentPane(new LoadContentView());
        }
    }
}
