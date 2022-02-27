package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
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

    public void reloadContent(boolean freshData) {
        var loadedSet = Application.getInstance().getDataManager().getLoadedSet();

        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        fileMenu.add(Util.runnableItem("Exit", this::dispose));

        if(loadedSet != null) {
            setContentPane(new DataContentView());

            fileMenu.add(Util.runnableItem("Unload data", () -> {
                Application.getInstance().getDataManager().unloadSet();
                reloadContent(false);
            }));


            var dataMenu = new JMenu("Data");
            menuBar.add(dataMenu);

            if(freshData) {
                this.showAirlineEditDialog(true);
            }

            dataMenu.add(Util.runnableItem("Edit airline", () -> this.showAirlineEditDialog(false)));
        } else {
            setContentPane(new LoadContentView());
        }

        setJMenuBar(menuBar);
        revalidate();
    }

    @Override
    protected void processEvent(AWTEvent e) {
        Application.getInstance().getLogger().debug("Processing event {}", e);
        if(e instanceof ReloadContentEvent reloadEvent) {
            reloadContent(reloadEvent.isFreshData());
            return;
        }

        super.processEvent(e);
    }

    private void showAirlineEditDialog(boolean initialSetup) {
        var dialog = new EditAirlineDialog(initialSetup ? this::unloadData : null);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void unloadData() {
        Application.getInstance().getDataManager().unloadSet();
        reloadContent(false);
    }
}
