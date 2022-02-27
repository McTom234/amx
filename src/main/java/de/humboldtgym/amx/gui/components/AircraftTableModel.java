package de.humboldtgym.amx.gui.components;

import de.humboldtgym.amx.models.aircraft.Aircraft;
import de.humboldtgym.amx.models.aircraft.PassengerPlane;
import de.humboldtgym.amx.models.enums.WeightClass;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AircraftTableModel extends AbstractTableModel {
    private final List<Aircraft> availableAircraft;

    public AircraftTableModel(List<Aircraft> availableAircraft) {
        this.availableAircraft = availableAircraft;
        this.availableAircraft.add(new PassengerPlane(
                "Reg",
                "Ica",
                120,
                30,
                15,
                320,
                1000,
                10000,
                WeightClass.MEDIUM,
                120000,
                5,
                360,
                32.5,
                new Date(),
                15232,
                "EGLL",
                2,
                3100,
                120.4,
                true,
                false,
                3,
                120
        ));
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
