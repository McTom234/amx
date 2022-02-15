package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.gui.events.ReloadContentEvent;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadContentView extends JPanel {
    public LoadContentView() {
        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5,5);

        var openButton = new JButton("Open data set");
        openButton.addActionListener(this::openCallback);
        add(openButton, constraints);

        var createButton = new JButton("Create new data set");
        createButton.addActionListener(this::createCallback);
        add(createButton, constraints);
    }

    private void openCallback(ActionEvent e) {
        var fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open data set");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".json");
            }

            @Override
            public String getDescription() {
                return "Json";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        var result = fileChooser.showOpenDialog(this);

        if(result == JFileChooser.APPROVE_OPTION) {
            var selected = fileChooser.getSelectedFile();
            Application.getInstance().getLogger().debug("Opening file {}", selected);
        }
    }

    private void createCallback(ActionEvent e) {
        Application.getInstance().getDataManager().newSet();
        SwingUtilities.getRoot(this).dispatchEvent(new ReloadContentEvent(this));
    }
}
