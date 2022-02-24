package de.humboldtgym.amx.io;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.models.Runway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AirportManager {
    private static final Logger LOGGER = LogManager.getLogger();

    private Set<Runway> runways;

    public AirportManager() {
        this.runways = new HashSet<>();
    }

    public void loadData() {
        LOGGER.debug("Loading data...");
        var reader = Application.getInstance().getDataManager().getObjectReader();
        var typeFactory = reader.getTypeFactory();

        var collectionType = typeFactory.constructCollectionType(Set.class, Runway.class);

        try {
            var parser = reader.createParser(getClass().getResource("/runways.json"));
            this.runways = reader.readValue(parser, collectionType);
            LOGGER.debug("Loaded {} runways", runways.size());
        } catch (IOException e) {
            throw new RuntimeException("Internal data corrupted", e);
        }
    }
}
