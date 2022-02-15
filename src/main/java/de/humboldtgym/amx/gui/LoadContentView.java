package de.humboldtgym.amx.gui;

import javax.swing.*;
import java.awt.*;

public class LoadContentView extends JPanel {
    public LoadContentView() {
        var layout = new GridBagLayout();
        setLayout(layout);

        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        var openButton = new JButton("Open data set");
        add(openButton, constraints);
    }
}
