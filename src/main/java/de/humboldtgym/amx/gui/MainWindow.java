package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.gui.events.ReloadContentEvent;

import javax.swing.*;
import java.awt.*;

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
            setContentPane(new DataContentView());
            revalidate();
        } else {
            setContentPane(new LoadContentView());
        }
    }

    @Override
    protected void processEvent(AWTEvent e) {
        Application.getInstance().getLogger().debug("Processing event {}", e);
        if(e instanceof ReloadContentEvent) {
            reloadContent();
            return;
        }

        super.processEvent(e);
    }
}
