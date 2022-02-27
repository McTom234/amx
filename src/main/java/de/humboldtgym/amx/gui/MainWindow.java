package de.humboldtgym.amx.gui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.exceptions.DataException;
import de.humboldtgym.amx.gui.events.ReloadContentEvent;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("AMX");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    public void reloadContent(boolean freshData) {
        var loadedSet = Application.getInstance().getDataManager().getLoadedSet();
        if(loadedSet != null && freshData) {
            this.showAirlineEditDialog(true);
            loadedSet = Application.getInstance().getDataManager().getLoadedSet(); // may have been cancelled
        }

        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        fileMenu.add(Util.runnableItem("Exit", this::promptQuit));

        var extrasMenu = new JMenu("Extras");
        menuBar.add(extrasMenu);

        var themeMenu = new JMenu("Theme");
        extrasMenu.add(themeMenu);

        themeMenu.add(Util.runnableItem("Light", () -> Util.updateLAF(this, new FlatLightLaf())));
        themeMenu.add(Util.runnableItem("Dark", () -> Util.updateLAF(this, new FlatDarkLaf())));
        themeMenu.add(Util.runnableItem("Darcula", () -> Util.updateLAF(this, new FlatDarculaLaf())));
        themeMenu.add(Util.runnableItem("IntelliJ", () -> Util.updateLAF(this, new FlatIntelliJLaf())));
        themeMenu.add(Util.runnableItem("Native", () -> Util.updateLAF(this, UIManager.getSystemLookAndFeelClassName())));

        if(loadedSet != null) {
            setContentPane(new DataContentView());

            fileMenu.add(Util.runnableItem("Save", this::saveData));
            fileMenu.add(Util.runnableItem("Unload data", () -> {
                int selection = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to unload the data set? All unsaved changes will be lost!",
                        "Unload data",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if(selection == JOptionPane.YES_OPTION) {
                    unloadData();
                }
            }));


            var dataMenu = new JMenu("Data");
            menuBar.add(dataMenu);

            dataMenu.add(Util.runnableItem("Edit airline", () -> this.showAirlineEditDialog(false)));
        } else {
            setContentPane(new LoadContentView());
        }

        setJMenuBar(menuBar);
        revalidate();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptQuit();
            }
        });
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

    public void promptQuit() {
        int selection = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit? All unsaved changes will be lost!",
                "Exit application",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if(selection == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}
