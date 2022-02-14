package de.humboldtgym.amx;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.logging.log4j.LogManager;

public class Main {
    public static void main(String[] args) {
        var logger = LogManager.getLogger();
        logger.info("AMX version 1.0 starting...");

        logger.trace("Setting up look and feel...");
        FlatLaf.setup(new FlatLightLaf());

        logger.trace("Creating application instance...");
        var application = Application.getInstance();
        application.run();
    }
}
