package de.humboldtgym.amx;

import de.humboldtgym.amx.gui.MainWindow;
import de.humboldtgym.amx.io.AirportManager;
import de.humboldtgym.amx.io.DataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static Application instance;
    
    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }
        
        return instance;
    }
    
    private final MainWindow window;
    private final Logger logger;
    private final DataManager dataManager;
    private final AirportManager airportManager;

    private Application() {
        this.window = new MainWindow();
        this.logger = LogManager.getLogger();
        this.dataManager = new DataManager();
        this.airportManager = new AirportManager();
    }

    public void run() {
        this.airportManager.loadData();

        this.window.reloadContent(false);
        this.window.setVisible(true);
    }

    public Logger getLogger() {
        return logger;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public AirportManager getAirportManager() {
        return airportManager;
    }
}
