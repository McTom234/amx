package de.humboldtgym.amx.gui.validator;

import javax.swing.*;
import java.util.regex.Pattern;

public class IcaoValidator extends ComponentValidator<String, JTextField> {
    private static final Pattern ICAO_PATTERN =
            Pattern.compile("^([A-Z]|[0-9]){2,4}$");

    public IcaoValidator(JTextField targetComponent) {
        super(targetComponent);
    }

    @Override
    protected String doValidate() throws InputValidationException {
        var value = targetComponent.getText();
        if(value == null) {
            throw new InputValidationException("Text can't be null");
        }

        value = value.trim();

        if(!ICAO_PATTERN.matcher(value).matches()) {
            throw new InputValidationException("Input does not match the ICAO pattern");
        }

        return value;
    }
}
