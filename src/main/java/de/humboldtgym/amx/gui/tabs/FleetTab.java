package de.humboldtgym.amx.gui.tabs;

import de.humboldtgym.amx.gui.components.AircraftTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FleetTab extends JPanel {
    private final AircraftTable table;

    public FleetTab() {
        setLayout(new BorderLayout());

        var scroller = new JScrollPane();

        this.table = new AircraftTable(new ArrayList<>());
        scroller.setViewportView(this.table);

        add(scroller, BorderLayout.CENTER);
    }
}
