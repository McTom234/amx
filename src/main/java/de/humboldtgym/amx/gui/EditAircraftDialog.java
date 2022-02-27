package de.humboldtgym.amx.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.gui.validator.*;
import de.humboldtgym.amx.models.aircraft.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Objects;

public class EditAircraftDialog extends JDialog {
    private final Aircraft aircraft;
    private final Runnable doneCallback;

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
    private final DatePicker bought;
    private final DoubleValidator flightHours;
    private final NameValidator location;
    private final IntValidator minPilots;

    private final IntValidator maxPassengers;

    private final IntValidator maxCargoA;
    private final IntValidator maxCargoB;
    private final IntValidator maxCargoC;

    private final JCheckBox frontHatch;
    private final JCheckBox sideHatch;
    private final JCheckBox backHatch;

    private final IntValidator minRunwayLength;
    private final DoubleValidator wingSpan;
    private final JCheckBox winglets;
    private final IntValidator engines;

    private final IntValidator rotors;
    private final DoubleValidator rotorSpan;

    public EditAircraftDialog(Aircraft aircraft, Runnable doneCallback) {
        this.aircraft = aircraft;
        this.doneCallback = doneCallback;

        this.realContent = new JPanel();

        var registrationField = new JTextField(aircraft.getRegistration());
        var icaoField = new JTextField(aircraft.getIcao());
        var lengthField = new JTextField(Double.toString(aircraft.getLength()));
        var widthField = new JTextField(Double.toString(aircraft.getWidth()));
        var heightField = new JTextField(Double.toString(aircraft.getHeight()));
        var flightSpeedField = new JTextField(Integer.toString(aircraft.getFlightSpeedPerHour()));
        var emptyWeightField = new JTextField(Integer.toString(aircraft.getEmptyWeight()));
        var maxWeightField = new JTextField(Integer.toString(aircraft.getMaxWeight()));
        var maxFuelField = new JTextField(Integer.toString(aircraft.getMaxFuel()));
        var fuelPerHourField = new JTextField(Double.toString(aircraft.getFuelPerHour()));
        var maintenanceIntervalField = new JTextField(Integer.toString(aircraft.getMaintenanceInterval()));
        var flightHoursField = new JTextField(Double.toString(aircraft.getFlightHours()));
        var locationField = new JTextField(aircraft.getLocation());
        var minPilotsField = new JTextField(Integer.toString(aircraft.getMinPilots()));

        this.registration = new RegistrationValidator(registrationField);
        this.icao = new IcaoValidator(icaoField);
        this.length = new DoubleValidator(lengthField, 5.0, 300.0);
        this.width = new DoubleValidator(widthField, 3.0, 20.0);
        this.height = new DoubleValidator(heightField, 5.0, 20.0);
        this.flightSpeed = new IntValidator(flightSpeedField, 50, 500);
        this.emptyWeight = new IntValidator(emptyWeightField, 1000);
        this.maxWeight = new IntValidator(maxWeightField, 1000);
        this.maxFuel = new IntValidator(maxFuelField, 10);
        this.fuelPerHour = new DoubleValidator(fuelPerHourField, 1.0);
        this.maintanenceInterval = new IntValidator(maintenanceIntervalField, 72);
        this.bought = createDatePicker();
        this.flightHours = new DoubleValidator(flightHoursField, 0);
        this.location = new NameValidator(locationField);
        this.minPilots = new IntValidator(minPilotsField, 1);

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

        addHeading("General", 1);

        line(2, new JLabel("Registration"), registrationField);
        line(3, new JLabel("ICAO"), icaoField);
        line(4, new JLabel("Length"), lengthField);
        line(5, new JLabel("Width"), widthField);
        line(6, new JLabel("Height"), heightField);
        line(7, new JLabel("Knots"), flightSpeedField);
        line(8, new JLabel("Empty weight"), emptyWeightField);
        line(9, new JLabel("Max takeoff weight"), maxWeightField);
        line(10, new JLabel("Max fuel"), maxFuelField);
        line(11, new JLabel("Fuel per hour"), fuelPerHourField);
        line(12, new JLabel("Maintenance interval"), maintenanceIntervalField);
        line(13, new JLabel("Bought at"), this.bought);
        line(14, new JLabel("Flight hours"), flightHoursField);
        line(15, new JLabel("Location"), locationField);
        line(16, new JLabel("Min pilots"), minPilotsField);

        int nextLine = -1;

        if(aircraft instanceof Plane plane) {
            var minRunwayLengthField = new JTextField(Integer.toString(plane.getMinRunwayLength()));
            var wingSpanField = new JTextField(Double.toString(plane.getWingSpan()));
            var enginesField = new JTextField(Integer.toString(plane.getEngines()));

            this.minRunwayLength = new IntValidator(minRunwayLengthField, 500, 4000);
            this.wingSpan = new DoubleValidator(wingSpanField, 5.0, 100.0);
            this.engines = new IntValidator(enginesField, 1, 16);
            this.winglets = new JCheckBox((String) null, plane.isWinglets());

            addHeading("Plane", 17);

            line(18, new JLabel("Min runway length"), minRunwayLengthField);
            line(19, new JLabel("Wing span"), wingSpanField);
            line(20, new JLabel("Engine count"), enginesField);
            line(21, new JLabel("Winglets"), this.winglets);

            nextLine = 22;
        } else {
            this.minRunwayLength = null;
            this.wingSpan = null;
            this.engines = null;
            this.winglets = null;
        }

        if(aircraft instanceof Helicopter helicopter) {
            var rotorsField = new JTextField(Integer.toString(helicopter.getRotors()));
            var rotorSpanField = new JTextField(Double.toString(helicopter.getRotorSpan()));

            this.rotors = new IntValidator(rotorsField, 2);
            this.rotorSpan = new DoubleValidator(rotorSpanField, 5.0);

            addHeading("Helicopter", 17);

            line(18, new JLabel("Rotor count"), rotorsField);
            line(19, new JLabel("Rotor span"), rotorSpanField);

            nextLine = 20;
        } else {
            this.rotors = null;
            this.rotorSpan = null;
        }

        if(aircraft instanceof PassengerAircraft passengerAircraft) {
            var passengersField = new JTextField(Integer.toString(passengerAircraft.getMaxPassengers()));
            this.maxPassengers = new IntValidator(passengersField, 1, 2000);

            addHeading("Passenger", nextLine);

            line(nextLine + 2, new JLabel("Max passengers"), passengersField);
            nextLine += 3;
        } else {
            this.maxPassengers = null;
        }

        if(aircraft instanceof CargoAircraft cargoAircraft) {
            var maxCargoAField = new JTextField(Integer.toString(cargoAircraft.getMaxCargoA()));
            var maxCargoBField = new JTextField(Integer.toString(cargoAircraft.getMaxCargoB()));
            var maxCargoCField = new JTextField(Integer.toString(cargoAircraft.getMaxCargoC()));

            this.frontHatch = new JCheckBox("Front", cargoAircraft.isFrontHatch());
            this.sideHatch = new JCheckBox("Side", cargoAircraft.isSideHatch());
            this.backHatch = new JCheckBox("Back", cargoAircraft.isBackHatch());

            this.maxCargoA = new IntValidator(maxCargoAField, 0);
            this.maxCargoB = new IntValidator(maxCargoBField, 0);
            this.maxCargoC = new IntValidator(maxCargoCField, 0);

            addHeading("Cargo", nextLine);

            var maxCargoPanel = new JPanel();
            maxCargoPanel.setLayout(new BoxLayout(maxCargoPanel, BoxLayout.X_AXIS));

            maxCargoPanel.add(maxCargoAField);
            maxCargoPanel.add(Box.createHorizontalStrut(5));
            maxCargoPanel.add(maxCargoBField);
            maxCargoPanel.add(Box.createHorizontalStrut(5));
            maxCargoPanel.add(maxCargoCField);

            line(nextLine + 2, new JLabel("Max cargo"), maxCargoPanel);

            var hatchesPanel = new JPanel();
            hatchesPanel.setLayout(new BoxLayout(hatchesPanel, BoxLayout.X_AXIS));

            hatchesPanel.add(frontHatch);
            hatchesPanel.add(Box.createHorizontalStrut(5));
            hatchesPanel.add(sideHatch);
            hatchesPanel.add(Box.createHorizontalStrut(5));
            hatchesPanel.add(backHatch);

            line(nextLine + 3, new JLabel("Hatches"), hatchesPanel);

            nextLine += 4;
        } else {
            this.maxCargoA = null;
            this.maxCargoB = null;
            this.maxCargoC = null;

            this.frontHatch = null;
            this.backHatch = null;
            this.sideHatch = null;
        }

        addSpacer(nextLine);

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

    private void addSpacer(int y) {
        var spacer = Box.createVerticalGlue();

        var constraints = Util.basicFormConstraints(y);
        constraints.weighty = 2;
        constraints.weightx = 1;

        realContent.add(spacer, constraints);
    }

    private void addHeading(String content, int y) {
        var generalLabel = new JLabel(content);
        generalLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        generalLabel.setFont(generalLabel.getFont().deriveFont(Font.PLAIN, 20.0f));

        var constraints = Util.basicFormConstraints(y);
        constraints.weightx = 2;

        realContent.add(generalLabel, constraints);
    }

    private void ok(ActionEvent e) {
        var batch = new ValidationBatch();
        var registration = this.registration.validate(batch);
        var icao = this.icao.validate(batch);
        var length = this.length.validate(batch);
        var width = this.width.validate(batch);
        var height = this.height.validate(batch);
        var flightSpeed = this.flightSpeed.validate(batch);
        var emptyWeight = this.emptyWeight.validate(batch);
        var maxWeight = this.maxWeight.validate(batch);
        var maxFuel = this.maxFuel.validate(batch);
        var fuelPerHour = this.fuelPerHour.validate(batch);
        var maintenanceInterval = this.maintanenceInterval.validate(batch);
        var flightHours = this.flightHours.validate(batch);
        var location = this.location.validate(batch);
        var minPilots = this.minPilots.validate(batch);

        Integer minRunwayLength = null;
        Double wingSpan = null;
        Boolean winglets = null;
        Integer engines = null;

        if(aircraft instanceof Plane) {
            minRunwayLength = this.minRunwayLength.validate(batch);
            wingSpan = this.wingSpan.validate(batch);
            winglets = this.winglets.isSelected();
            engines = this.engines.validate(batch);
        }

        Integer rotors = null;
        Double rotorSpan = null;

        if(aircraft instanceof Helicopter) {
            rotors = this.rotors.validate(batch);
            rotorSpan = this.rotorSpan.validate(batch);
        }

        Integer maxPassengers = null;

        if(aircraft instanceof PassengerAircraft) {
            maxPassengers = this.maxPassengers.validate(batch);
        }

        Integer maxCargoA = null;
        Integer maxCargoB = null;
        Integer maxCargoC = null;

        Boolean frontHatch = null;
        Boolean sideHatch = null;
        Boolean backHatch = null;

        if(aircraft instanceof CargoAircraft) {
            maxCargoA = this.maxCargoA.validate(batch);
            maxCargoB = this.maxCargoB.validate(batch);
            maxCargoC = this.maxCargoC.validate(batch);

            frontHatch = this.frontHatch.isSelected();
            sideHatch = this.sideHatch.isSelected();
            backHatch = this.backHatch.isSelected();
        }

        if(!batch.isOk()) {
            return;
        }

        aircraft.setRegistration(registration);
        aircraft.setIcao(icao);
        aircraft.setLength(Objects.requireNonNull(length));
        aircraft.setWidth(Objects.requireNonNull(width));
        aircraft.setHeight(Objects.requireNonNull(height));
        aircraft.setFlightSpeedPerHour(Objects.requireNonNull(flightSpeed));
        aircraft.setEmptyWeight(Objects.requireNonNull(emptyWeight));
        aircraft.setMaxWeight(Objects.requireNonNull(maxWeight));
        aircraft.setMaxFuel(Objects.requireNonNull(maxFuel));
        aircraft.setFuelPerHour(Objects.requireNonNull(fuelPerHour));
        aircraft.setMaintenanceInterval(Objects.requireNonNull(maintenanceInterval));
        aircraft.setBought(Date.from(this.bought.getDate().atStartOfDay().atZone(ZoneId.of("Z")).toInstant()));
        aircraft.setFlightHours(Objects.requireNonNull(flightHours));
        aircraft.setLocation(location);
        aircraft.setMinPilots(Objects.requireNonNull(minPilots));

        if(aircraft instanceof Plane plane) {
            plane.setMinRunwayLength(Objects.requireNonNull(minRunwayLength));
            plane.setWingSpan(Objects.requireNonNull(wingSpan));
            plane.setWinglets(Objects.requireNonNull(winglets));
            plane.setEngines(Objects.requireNonNull(engines));
        }

        if(aircraft instanceof Helicopter helicopter) {
            helicopter.setRotors(Objects.requireNonNull(rotors));
            helicopter.setRotorSpan(Objects.requireNonNull(rotorSpan));
        }

        if(aircraft instanceof PassengerAircraft passengerAircraft) {
            passengerAircraft.setMaxPassengers(Objects.requireNonNull(maxPassengers));
        }

        if(aircraft instanceof CargoAircraft cargoAircraft) {
            cargoAircraft.setMaxCargoA(Objects.requireNonNull(maxCargoA));
            cargoAircraft.setMaxCargoB(Objects.requireNonNull(maxCargoB));
            cargoAircraft.setMaxCargoC(Objects.requireNonNull(maxCargoC));

            cargoAircraft.setFrontHatch(Objects.requireNonNull(frontHatch));
            cargoAircraft.setSideHatch(Objects.requireNonNull(sideHatch));
            cargoAircraft.setBackHatch(Objects.requireNonNull(backHatch));
        }

        if(this.doneCallback != null) {
            this.doneCallback.run();
        }

        this.dispose();
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }
}
