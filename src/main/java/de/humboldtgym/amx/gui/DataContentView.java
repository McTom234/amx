package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.gui.tabs.FleetTab;
import de.humboldtgym.amx.gui.tabs.ToBeDoneTab;

import javax.swing.*;

public class DataContentView extends JTabbedPane {
    /* package */ DataContentView() {
        addTab("Fleet", new FleetTab());
        addTab("Flights", new ToBeDoneTab());
        addTab("Routes", new ToBeDoneTab());
        addTab("Flights", new ToBeDoneTab());
        addTab("Customers", new ToBeDoneTab());
        addTab("Employees", new ToBeDoneTab());
    }
}
