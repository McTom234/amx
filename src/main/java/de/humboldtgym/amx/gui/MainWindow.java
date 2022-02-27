package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.exceptions.DataException;
import de.humboldtgym.amx.gui.events.ReloadContentEvent;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

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

        var extrasMenu = new JMenu("Extras");
        menuBar.add(extrasMenu);

        var themeMnu = new JMenu("Theme");
        extrasMenu.add(themeMnu);

        if(loadedSet != null) {
            setContentPane(new DataContentView());

            fileMenu.add(Util.runnableItem("Save", this::saveData));
            fileMenu.add(Util.runnableItem("Unload data", this::unloadData));


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

    private void saveData() {
        var saveChooser = new JFileChooser();
        saveChooser.setDialogTitle("Select save location");
        saveChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".json");
            }

            @Override
            public String getDescription() {
                return "Json";
            }
        });
        saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int response = saveChooser.showSaveDialog(this);

        if(response == JFileChooser.APPROVE_OPTION) {
            var selected = saveChooser.getSelectedFile();
            Application.getInstance().getLogger().debug("Saving data to file {}", selected);

            try {
                Application.getInstance().getDataManager().saveDataSet(selected.toPath());
            } catch (DataException e) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Data loading failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
