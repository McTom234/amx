package de.humboldtgym.amx.gui.components;

import de.humboldtgym.amx.models.aircraft.Aircraft;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class AircraftTableModel extends AbstractTableModel {
    private final List<Aircraft> availableAircraft;

    public AircraftTableModel(List<Aircraft> availableAircraft) {
        this.availableAircraft = availableAircraft;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Registration";
            case 1 -> "ICAO";
            case 2 -> "Length";
            case 3 -> "Speed";
            case 4 -> "Weight class";
            case 5 -> "Location";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var aircraft = availableAircraft.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> aircraft.getRegistration();
            case 1 -> aircraft.getIcao();
            case 2 -> aircraft.getLength();
            case 3 -> aircraft.getFlightSpeedPerHour();
            case 4 -> aircraft.getWeightClass().name().toLowerCase(Locale.ROOT);
            case 5 -> aircraft.getLocation();
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return availableAircraft.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public void removeRow(int row) {
        this.availableAircraft.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public Aircraft getAircraft(int row) {
        return this.availableAircraft.get(row);
    }
}
