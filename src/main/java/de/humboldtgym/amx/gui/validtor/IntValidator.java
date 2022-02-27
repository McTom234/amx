package de.humboldtgym.amx.gui.validtor;

import javax.swing.*;

public class IntValidator extends ComponentValidator<Integer, JTextField> {
    private final int minValue;
    private final int maxValue;

    public IntValidator(JTextField targetComponent) {
        this(targetComponent, Integer.MIN_VALUE);
    }

    public IntValidator(JTextField targetComponent, int minValue) {
        this(targetComponent, minValue, Integer.MAX_VALUE);
    }

    public IntValidator(JTextField targetComponent, int minValue, int maxValue) {
        super(targetComponent);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    protected Integer doValidate() throws InputValidationException {
        var value = targetComponent.getText();
        if(value == null) {
            throw new InputValidationException("Text can not be null");
        }

        value = value.trim();

        try {
            int v = Integer.parseInt(value);

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
