package de.humboldtgym.amx.gui.validator;

public class ValidationBatch {
    private boolean isOk;

    public ValidationBatch() {
        isOk = true;
    }

    /* package */ void fail() {
        this.isOk = false;
    }

    public boolean isOk() {
        return isOk;
    }
}
