package de.humboldtgym.amx.gui.tabs;

import de.humboldtgym.amx.gui.EditAircraftDialog;
import de.humboldtgym.amx.gui.components.AircraftTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FleetTab extends JPanel {
    private final AircraftTable table;
    private final JButton editButton;
    private final JButton deleteButton;

    public FleetTab() {
        setLayout(new BorderLayout());

        var scroller = new JScrollPane();

        this.table = new AircraftTable(new ArrayList<>());
        this.table.getSelectionModel().addListSelectionListener(this::onSelectionChanged);
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FleetTab.this.onClicked(e);
            }
        });

        scroller.setViewportView(this.table);

        add(scroller, BorderLayout.CENTER);

        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttonPanel.add(Box.createHorizontalGlue());

        editButton = new JButton("Edit");
        editButton.addActionListener((e) -> onEdit());
        editButton.setEnabled(table.getSelectedRow() != -1);
        buttonPanel.add(editButton);

        buttonPanel.add(Box.createHorizontalStrut(5));

        var addButton = new JButton("Add");
        buttonPanel.add(addButton);

        buttonPanel.add(Box.createHorizontalStrut(5));

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener((e) -> onDelete());
        deleteButton.setEnabled(table.getSelectedRow() != -1);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onSelectionChanged(ListSelectionEvent e) {
        int selectedRow = table.getSelectedRow();

        editButton.setEnabled(selectedRow != -1);
        deleteButton.setEnabled(selectedRow != -1);
    }

    private void onClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            onEdit();
        }
    }

    private void onEdit() {
        var aircraft = table.getModel().getAircraft(table.getSelectedRow());
        var editDialog = new EditAircraftDialog(aircraft, () -> {});
        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    private void onDelete() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to remove this Aircraft?",
                "Remove aircraft",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if(option == JOptionPane.YES_OPTION) {
            table.getModel().removeRow(table.getSelectedRow());
        }
    }
}
