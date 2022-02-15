package de.humboldtgym.amx.gui.events;

import java.awt.*;

public class ReloadContentEvent extends AWTEvent {
    public ReloadContentEvent(Object source) {
        super(source, AWTEvent.RESERVED_ID_MAX + 1);
    }
}
