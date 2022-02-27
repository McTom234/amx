package de.humboldtgym.amx.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.gui.validator.*;
import de.humboldtgym.amx.models.aircraft.Aircraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZoneId;
import java.util.Date;

public class EditAircraftDialog extends JDialog {
    private final Aircraft aircraft;
    private final Runnable cancelCallback;

    private final JPanel realContent;

    private final RegistrationValidator registration;
    private final IcaoValidator icao;
    private final DoubleValidator length;
    private final DoubleValidator width;
    private final DoubleValidator height;
    private final IntValidator flightSpeed;
    private final IntValidator emptyWeight;
    private final IntValidator maxWeight;
    private final IntValidator maxFuel;
    private final DoubleValidator fuelPerHour;
    private final IntValidator maintanenceInterval;
    private Date bought;
    private final DoubleValidator flightHours;
    private final NameValidator location;
    private final IntValidator minPilots;

    public EditAircraftDialog(Aircraft aircraft, Runnable cancelCallback) {
        this.aircraft = aircraft;
        this.cancelCallback = cancelCallback;

        this.realContent = new JPanel();

        var registrationField = new JTextField(aircraft.getRegistration());
        var icaoField = new JTextField(aircraft.getIcao());
        var lengthField = new JTextField(Double.toString(aircraft.getLength()));
        var widthField = new JTextField(Double.toString(aircraft.getWidth()));
        var heightField = new JTextField(Double.toString(aircraft.getHeight()));
        var flightSpeedField = new JTextField(Double.toString(aircraft.getFlightSpeedPerHour()));
        var emptyWeightField = new JTextField(Integer.toString(aircraft.getEmptyWeight()));
        var maxWeightField = new JTextField(Integer.toString(aircraft.getMaxWeight()));
        var maxFuelField = new JTextField(Integer.toString(aircraft.getMaxFuel()));
        var fuelPerHourField = new JTextField(Double.toString(aircraft.getFuelPerHour()));
        var maintenanceIntervalField = new JTextField(Integer.toString(aircraft.getMaintenanceInterval()));
        var boughtField = createDatePicker();
        var flightHoursField = new JTextField(Double.toString(aircraft.getFlightHours()));
        var locationField = new JTextField(aircraft.getLocation());
        var minPilotsField = new JTextField(Integer.toString(aircraft.getMinPilots()));

        this.registration = new RegistrationValidator(registrationField);
        this.icao = new IcaoValidator(icaoField);
        this.length = new DoubleValidator(lengthField);
        this.width = new DoubleValidator(widthField);
        this.height = new DoubleValidator(heightField);
        this.flightSpeed = new IntValidator(flightSpeedField);
        this.emptyWeight = new IntValidator(emptyWeightField);
        this.maxWeight = new IntValidator(maxWeightField);
        this.maxFuel = new IntValidator(maxFuelField);
        this.fuelPerHour = new DoubleValidator(fuelPerHourField);
        this.maintanenceInterval = new IntValidator(maintenanceIntervalField);
        this.bought = aircraft.getBought();
        this.flightHours = new DoubleValidator(flightHoursField);
        this.location = new NameValidator(locationField);
        this.minPilots = new IntValidator(minPilotsField);

        setTitle("Edit aircraft");
        setModal(true);
        setType(Type.UTILITY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(640, 420));

        setLayout(new BorderLayout());

        var scroller = new JScrollPane();
        scroller.setViewportView(realContent);
        scroller.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        realContent.setLayout(new GridBagLayout());

        line(0, new JLabel("Registration"), registrationField);
        line(1, new JLabel("ICAO"), icaoField);
        line(2, new JLabel("Length"), lengthField);
        line(3, new JLabel("Width"), widthField);
        line(4, new JLabel("Knots"), flightSpeedField);
        line(5, new JLabel("Empty weight"), emptyWeightField);
        line(6, new JLabel("Max takeoff weight"), maxWeightField);
        line(7, new JLabel("Max fuel"), maxFuelField);
        line(8, new JLabel("Fuel per hour"), fuelPerHourField);
        line(9, new JLabel("Maintenance interval"), maintenanceIntervalField);
        line(10, new JLabel("Bought at"), boughtField);
        line(11, new JLabel("Flight hours"), flightHoursField);
        line(12, new JLabel("Location"), locationField);
        line(13, new JLabel("Min pilots"), minPilotsField);

        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        var cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancel);
        cancelButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        var okButton = new JButton("Ok");
        okButton.addActionListener(this::ok);
        okButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonPanel.add(okButton);

        add(scroller, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onUserClose(e);
            }
        });
    }

    private void line(int y, JComponent left, JComponent right) {
        var constraints = Util.basicFormConstraints(y);

        realContent.add(left, constraints);

        constraints.gridx = 1;
        constraints.weightx = 2;
        realContent.add(right, constraints);
    }

    private DatePicker createDatePicker() {
        var defaults = UIManager.getDefaults();

        var convertedDate = aircraft.getBought().toInstant().atZone(ZoneId.of("Z")).toLocalDate();

        var picker = new DatePicker();
        picker.setDate(convertedDate);

        var settings = picker.getSettings();
        settings.setVisibleClearButton(false);
        settings.setAllowEmptyDates(false);

        settings.setColor(
                DatePickerSettings.DateArea.BackgroundOverallCalendarPanel,
                defaults.getColor("Panel.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.BackgroundOverallCalendarPanel,
                defaults.getColor("Panel.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.BackgroundTodayLabel,
                defaults.getColor("Button.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.TextFieldBackgroundDisabled,
                defaults.getColor("Label.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.CalendarBorderSelectedDate,
                defaults.getColor("Button.select")
        );
        settings.setColor(
                DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover,
                defaults.getColor("Button.highlight")
        );
        settings.setColor(
                DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels,
                defaults.getColor("Panel.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.TextFieldBackgroundValidDate,
                defaults.getColor("TextField.background")
        );
        settings.setColor(
                DatePickerSettings.DateArea.DatePickerTextValidDate,
                defaults.getColor("TextField.foreground")
        );

        return picker;
    }

    private void onUserClose(WindowEvent e) {
        if(this.cancelCallback != null) {
            this.cancelCallback.run();
        }
    }

    private void ok(ActionEvent e) {

    }

    private void cancel(ActionEvent e) {
        if(this.cancelCallback != null) {
            this.cancelCallback.run();
        }

        this.dispose();
    }
}
