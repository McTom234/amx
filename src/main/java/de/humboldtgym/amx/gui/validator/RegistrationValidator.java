package de.humboldtgym.amx.gui.validator;

import javax.swing.*;
import java.util.regex.Pattern;

public class RegistrationValidator extends ComponentValidator<String, JTextField> {
    /*
     * Worst aircraft registration validation pattern ever, but works good enough.
     *
     * Matches:
     * TT-TTT to TT-TTTTT
     * and
     * T-TTT to T-TTTTT
     * whereas T is an uppercase char from A to Z or a digit from 0 to 9
     */
    private static final Pattern REGISTRATION_PATTERN =
            Pattern.compile("^((([A-Z]|[0-9]){2}-)|([A-Z]|[0-9])-)([A-Z]|[0-9]){3,5}$");

    public RegistrationValidator(JTextField targetComponent) {
        super(targetComponent);
    }

    @Override
    protected String doValidate() throws InputValidationException {
        var value = targetComponent.getText();
        if(value == null) {
            throw new InputValidationException("Text can't be null");
        }

        value = value.trim();

        if(!REGISTRATION_PATTERN.matcher(value).matches()) {
            throw new InputValidationException("Input does not match the registration pattern");
        }

        return value;
    }
}
