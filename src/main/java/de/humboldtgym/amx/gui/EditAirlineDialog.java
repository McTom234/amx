package de.humboldtgym.amx.gui;

import javax.swing.*;
import java.awt.*;

public class EditAirlineDialog extends JDialog {
    /* package */ EditAirlineDialog() {
        setTitle("Edit airline");
        setModal(true);
        setType(Type.UTILITY);

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(640, 420));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel weightsPanel = new JPanel();
        weightsPanel.setLayout(new BoxLayout(weightsPanel, BoxLayout.LINE_AXIS));

        weightsPanel.add(new JTextField());
        weightsPanel.add(Box.createHorizontalStrut(5));
        weightsPanel.add(new JTextField());
        weightsPanel.add(Box.createHorizontalStrut(5));
        weightsPanel.add(new JTextField());

        line(0, new JLabel("Name"), new JTextField());
        line(1, new JLabel("Primary color"), new JButton("..."));
        line(2, new JLabel("Secondary color"), new JButton("..."));
        line(3, new JLabel("Weight per passenger"), new JTextField());
        line(4, new JLabel("Cargo weights"), weightsPanel);
    }

    private void line(int y, JComponent left, JComponent right) {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = y;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 1;
        constraints.insets = new Insets(2, 2, 2, 2);

        add(left, constraints);

        constraints.gridx = 1;
        constraints.weightx = 2;
        add(right, constraints);
    }
}
