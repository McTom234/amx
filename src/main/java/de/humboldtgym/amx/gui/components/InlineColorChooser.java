package de.humboldtgym.amx.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InlineColorChooser extends JPanel {
    private final JPanel display;
    private Color color;

    public InlineColorChooser(Color color) {
        this.color = color == null ? Color.WHITE : color;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        display = new JPanel();
        display.setBackground(color);
        display.setMinimumSize(new Dimension(200, 0));

        var chooseButton = new JButton("...");
        chooseButton.addActionListener(this::onChoose);

        add(display);
        add(chooseButton);
    }

    public Color getColor() {
        return color;
    }

    private void onChoose(ActionEvent e) {
        this.color = JColorChooser.showDialog(this, "Choose a new color", color, false);
        this.display.setBackground(color);
    }
}
