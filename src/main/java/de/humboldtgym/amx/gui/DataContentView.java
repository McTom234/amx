package de.humboldtgym.amx.gui;

import javax.swing.*;

public class DataContentView extends JTabbedPane {
    /* package */ DataContentView() {
        addTab("Fleet", new JLabel("Fleet editor"));
        addTab("Flights", new ToBeDoneView());
        addTab("Routes", new ToBeDoneView());
        addTab("Flights", new ToBeDoneView());
        addTab("Customers", new ToBeDoneView());
        addTab("Employees", new ToBeDoneView());
    }
}
