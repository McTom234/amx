package de.humboldtgym.amx.gui.validtor;

import javax.swing.*;

public class NameValidator extends ComponentValidator<String, JTextField> {
    public NameValidator(JTextField targetComponent) {
        super(targetComponent);
    }

    @Override
    protected String doValidate() throws InputValidationException {
        var value = targetComponent.getText();
        if(value == null) {
            throw new InputValidationException("Text can not be null");
        } else if(value.trim().length() < 3) {
            throw new InputValidationException("Name must be at least 3 characters long");
        }

        return value.trim();
    }
}
