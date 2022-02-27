package de.humboldtgym.amx.gui.events;

import java.awt.*;

public class ReloadContentEvent extends AWTEvent {
    private final boolean freshData;

    public ReloadContentEvent(Object source, boolean freshData) {
        super(source, AWTEvent.RESERVED_ID_MAX + 1);
        this.freshData = freshData;
    }

    public boolean isFreshData() {
        return freshData;
    }
}
