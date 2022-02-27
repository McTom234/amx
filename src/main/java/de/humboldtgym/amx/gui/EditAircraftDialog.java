package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.models.aircraft.Aircraft;

import javax.swing.*;
import java.awt.*;

public class EditAircraftDialog extends JDialog {
    private final Aircraft aircraft;
    private final Runnable cancelCallback;

    private final JPanel realContent;

    public EditAircraftDialog(Aircraft aircraft, Runnable cancelCallback) {
        this.aircraft = aircraft;
        this.cancelCallback = cancelCallback;

        this.realContent = new JPanel();

        setTitle("Edit aircraft");
        setModal(true);
        setType(Type.UTILITY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(640, 420));

        setLayout(new BorderLayout());

        var scroller = new JScrollPane();
        scroller.setViewportView(realContent);
        scroller.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(scroller);

        realContent.setLayout(new GridBagLayout());

        line(0, new JLabel("Registration"), new JTextField());
        line(1, new JLabel("ICAO"), new JTextField());
        line(2, new JLabel("Length"), new JTextField());
        line(3, new JLabel("Width"), new JTextField());
        line(4, new JLabel("Knots"), new JTextField());
        line(5, new JLabel("Weights: "), new JTextField());
        line(6, new JLabel("Weight class"), new JTextField());
        line(7, new JLabel("Fuel"), new JTextField());
        line(8, new JLabel("Maintenance interval"), new JTextField());
        line(9, new JLabel("Time to next maintenance"), new JTextField());
        line(10, new JLabel("Bought at"), new JTextField());
        line(11, new JLabel("Flight hours"), new JTextField());
        line(12, new JLabel("Location"), new JTextField());
        line(13, new JLabel("Min pilots"), new JTextField());
    }

    private void line(int y, JComponent left, JComponent right) {
        var constraints = Util.basicFormConstraints(y);

        realContent.add(left, constraints);

        constraints.gridx = 1;
        constraints.weightx = 2;
        realContent.add(right, constraints);
    }
}
