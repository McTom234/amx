package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.models.aircraft.*;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AircraftTypeDialog extends JDialog {
    private final JRadioButton cargoButton;
    private final JRadioButton planeButton;

    public AircraftTypeDialog() {
        setTitle("Select aircraft type");
        setModal(true);
        setType(Type.UTILITY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JRadioButton passengerButton = new JRadioButton("Passenger", true);
        this.cargoButton = new JRadioButton("Cargo");
        this.planeButton = new JRadioButton("Plane", true);
        JRadioButton helicopterButton = new JRadioButton("Helicopter");

        var contentTypeGroup = new ButtonGroup();
        contentTypeGroup.add(passengerButton);
        contentTypeGroup.add(cargoButton);

        var aircraftTypeGroup = new ButtonGroup();
        aircraftTypeGroup.add(planeButton);
        aircraftTypeGroup.add(helicopterButton);

        line(1, passengerButton, cargoButton);
        line(2, planeButton, helicopterButton);

        var constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(5, 5, 0, 5);

        var nextButton = new JButton("Next");
        nextButton.addActionListener((e) -> onNext());
        add(nextButton, constraints);

        pack();
        setResizable(false);
    }

    private void line(int y, JComponent left, JComponent right) {
        var constraints = Util.basicFormConstraints(y);
        add(left, constraints);

        constraints.gridx = 1;
        constraints.weightx = 2;
        add(right, constraints);
    }

    private void onNext() {
        Aircraft aircraft;

        if(cargoButton.isSelected()) {
            if(planeButton.isSelected()) {
                aircraft = new CargoPlane(
                        null,
                        null,
                        0.0,
                        0.0,
                        0.0,
                        0,
                        0,
                        0,
                        null,
                        0,
                        0.0,
                        0,
                        0.0,
                        new Date(),
                        0.0,
                        null,
                        0,
                        0,
                        0.0,
                        false,
                        0,
                        0,
                        0,
                        0,
                        false,
                        false,
                        false
                );
            } else {
                aircraft = new CargoHelicopter(
                        null,
                        null,
                        0.0,
                        0.0,
                        0.0,
                        0,
                        0,
                        0,
                        null,
                        0,
                        0.0,
                        0,
                        0.0,
                        new Date(),
                        0.0,
                        null,
                        0,
                        0,
                        0.0,
                        0,
                        0,
                        0,
                        false,
                        false,
                        false
                );
            }
        } else {
            if(planeButton.isSelected()) {
                aircraft = new PassengerPlane(
                        null,
                        null,
                        0.0,
                        0.0,
                        0.0,
                        0,
                        0,
                        0,
                        null,
                        0,
                        0.0,
                        0,
                        0.0,
                        new Date(),
                        0.0,
                        null,
                        0,
                        0,
                        0.0,
                        false,
                        0,
                        0
                );
            } else {
                aircraft = new PassengerHelicopter(
                        null,
                        null,
                        0.0,
                        0.0,
                        0.0,
                        0,
                        0,
                        0,
                        null,
                        0,
                        0.0,
                        0,
                        0.0,
                        new Date(),
                        0.0,
                        null,
                        0,
                        0,
                        0.0,
                        0
                );
            }
        }

        var editDialog = new EditAircraftDialog(aircraft, null);
        editDialog.setLocationRelativeTo(this);

        dispose();

        editDialog.setVisible(true);
    }
}
