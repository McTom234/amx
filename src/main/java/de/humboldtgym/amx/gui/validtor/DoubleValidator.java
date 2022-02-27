package de.humboldtgym.amx.gui.validtor;

import javax.swing.*;

public class DoubleValidator extends ComponentValidator<Double, JTextField>  {
    private final double minValue;
    private final double maxValue;

    public DoubleValidator(JTextField targetComponent) {
        this(targetComponent, Double.MIN_VALUE);
    }

    public DoubleValidator(JTextField targetComponent, double minValue) {
        this(targetComponent, minValue, Double.MAX_VALUE);
    }

    public DoubleValidator(JTextField targetComponent, double minValue, double maxValue) {
        super(targetComponent);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    protected Double doValidate() throws InputValidationException {
        var value = targetComponent.getText();
        if(value == null) {
            throw new InputValidationException("Text can not be null");
        }

        value = value.trim();

        try {
            double v = Double.parseDouble(value);

            if(v < minValue) {
                throw new InputValidationException("Input is too small");
            } else if(v > maxValue) {
                throw new InputValidationException("Input is too big");
            }

            return v;
        } catch (NumberFormatException e) {
            throw new InputValidationException("Input is not a valid number", e);
        }
    }
}
