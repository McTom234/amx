package de.humboldtgym.amx.gui.components;

import de.humboldtgym.amx.models.aircraft.Aircraft;

import javax.swing.*;
import java.util.List;

public class AircraftTable extends JTable {
    private final AircraftTableModel model;

    public AircraftTable(List<Aircraft> aircraft) {
        this.model = new AircraftTableModel(aircraft);
        setModel(model);
    }
}
