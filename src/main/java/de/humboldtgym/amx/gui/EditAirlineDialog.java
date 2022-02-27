package de.humboldtgym.amx.gui;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.gui.components.InlineColorChooser;
import de.humboldtgym.amx.gui.validtor.DoubleValidator;
import de.humboldtgym.amx.gui.validtor.NameValidator;
import de.humboldtgym.amx.gui.validtor.ValidationBatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class EditAirlineDialog extends JDialog {
    private final Runnable cancelCallback;

    private final NameValidator name;
    private final InlineColorChooser primaryColor;
    private final InlineColorChooser secondaryColor;
    private final DoubleValidator weightPerPassenger;
    private final DoubleValidator weightA;
    private final DoubleValidator weightB;
    private final DoubleValidator weightC;

    /* package */ EditAirlineDialog(Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;

        var data = Application.getInstance().getDataManager().getLoadedSet().getAirline();

        var nameField = new JTextField(data.getName());
        var weightPerPassengerField = new JTextField(Double.toString(data.getWeightPerPassenger()));
        var weightAField = new JTextField(Double.toString(data.getWeightCargoA()));
        var weightBField = new JTextField(Double.toString(data.getWeightCargoB()));
        var weightCField = new JTextField(Double.toString(data.getWeightCargoC()));

        this.name = new NameValidator(nameField);
        this.primaryColor = new InlineColorChooser(new Color(data.getPrimaryColor()));
        this.secondaryColor = new InlineColorChooser(new Color(data.getSecondaryColor()));
        this.weightPerPassenger = new DoubleValidator(weightPerPassengerField, 10);
        this.weightA = new DoubleValidator(weightAField, 10);
        this.weightB = new DoubleValidator(weightBField, 10);
        this.weightC = new DoubleValidator(weightCField, 10);

        setTitle("Edit airline");
        setModal(true);
        setType(Type.UTILITY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(640, 0));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        var weightsPanel = new JPanel();
        weightsPanel.setLayout(new BoxLayout(weightsPanel, BoxLayout.X_AXIS));

        weightsPanel.add(weightAField);
        weightsPanel.add(Box.createHorizontalStrut(5));
        weightsPanel.add(weightBField);
        weightsPanel.add(Box.createHorizontalStrut(5));
        weightsPanel.add(weightCField);

        line(0, new JLabel("Name"), nameField);
        line(1, new JLabel("Primary color"), primaryColor);
        line(2, new JLabel("Secondary color"), secondaryColor);
        line(3, new JLabel("Weight per passenger"), weightPerPassengerField);
        line(4, new JLabel("Cargo weights"), weightsPanel);

        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        var cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancel);
        cancelButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        var okButton = new JButton("Ok");
        okButton.addActionListener(this::ok);
        okButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonPanel.add(okButton);

        var constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(5, 2, 0, 2);

        add(buttonPanel, constraints);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onUserClose(e);
            }
        });

        pack();
    }

    private void line(int y, JComponent left, JComponent right) {
        var constraints = Util.basicFormConstraints(y);
        add(left, constraints);

        constraints.gridx = 1;
        constraints.weightx = 2;
        add(right, constraints);
    }

    private void cancel(ActionEvent e) {
        if(this.cancelCallback != null) {
            this.cancelCallback.run();
        }

        this.dispose();
    }

    private void ok(ActionEvent e) {
        var batch = new ValidationBatch();
        var name = this.name.validate(batch);
        var weightPerPassenger = this.weightPerPassenger.validate(batch);
        var weightA = this.weightA.validate(batch);
        var weightB = this.weightB.validate(batch);
        var weightC = this.weightC.validate(batch);

        if (!batch.isOk()) {
            return;
        }

        var primaryColor = this.primaryColor.getColor();
        var secondaryColor = this.secondaryColor.getColor();

        var data = Application.getInstance().getDataManager().getLoadedSet().getAirline();

        data.setName(name);
        data.setPrimaryColor(primaryColor.getRGB());
        data.setSecondaryColor(secondaryColor.getRGB());
        data.setWeightPerPassenger(Objects.requireNonNull(weightPerPassenger));
        data.setWeightCargoA(Objects.requireNonNull(weightA));
        data.setWeightCargoB(Objects.requireNonNull(weightB));
        data.setWeightCargoC(Objects.requireNonNull(weightC));

        this.dispose();
    }

    private void onUserClose(WindowEvent e) {
        if(this.cancelCallback != null) {
            this.cancelCallback.run();
        }
    }
}
