package de.humboldtgym.amx.gui.validator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class ComponentValidator<T, C extends JComponent> {
    private final Border originalBorder;
    protected final C targetComponent;

    protected ComponentValidator(C targetComponent) {
        this.originalBorder = targetComponent.getBorder();
        this.targetComponent = targetComponent;
    }

    public final T validate(ValidationBatch batch) {
        try {
            T v = doValidate();
            succeedValidation();
            return v;
        } catch (InputValidationException e) {
            batch.fail();
            failValidation(e);
            return null;
        }
    }

    protected abstract T doValidate() throws InputValidationException;

    private void succeedValidation() {
        targetComponent.setBorder(originalBorder);
    }

    private void failValidation(InputValidationException e) {
        targetComponent.setBorder(new LineBorder(Color.RED, 2));
        targetComponent.setToolTipText(e.getMessage());
    }
}
