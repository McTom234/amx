package de.humboldtgym.amx.io;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.exceptions.InvalidDataException;
import de.humboldtgym.amx.models.Runway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class AirportManager {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Map<String, Set<Runway>> airportsToRunway;

    public AirportManager() {
        this.airportsToRunway = new HashMap<>();
    }

    public void loadData() {
        LOGGER.debug("Loading data...");
        var reader = Application.getInstance().getDataManager().getObjectReader();
        var typeFactory = reader.getTypeFactory();

        var collectionType = typeFactory.constructCollectionType(Set.class, Runway.class);

        try {
            var parser = reader.createParser(getClass().getResource("/runways.json"));
            Set<Runway> runways = reader.readValue(parser, collectionType);
            LOGGER.debug("Loaded {} runways, indexing...", runways.size());

            for (var runway : runways) {
                var runwaysPerAirport = this.airportsToRunway.computeIfAbsent(runway.getICAO(), (k) -> new HashSet<>());
                runwaysPerAirport.add(runway);
            }
        } catch (IOException e) {
            throw new RuntimeException("Internal data corrupted", e);
        }
    }

    public Set<String> getAirports() {
        return this.airportsToRunway.keySet();
    }

    public Set<Runway> getRunwaysForAirport(String icao) {
        return this.airportsToRunway.get(icao);
    }

    public Runway getFirstRunwayForAirport(String icao) {
        var all = getRunwaysForAirport(icao);
        if (all == null) {
            return null;
        }

        return all.stream().findFirst().orElse(null);
    }

    public Runway getLongestRunwayForAirport(String icao) {
        var all = getRunwaysForAirport(icao);
        if (all == null) {
            return null;
        }

        return all.stream().max(Comparator.comparingInt(Runway::getLength)).orElse(null);
    }

    public double computeDistance(String icaoA, String icaoB) throws InvalidDataException {
        Runway a = getFirstRunwayForAirport(icaoA);
        if (a == null) {
            throw new InvalidDataException("No runways for airport with ICAO " + icaoA);
        }

        Runway b = getFirstRunwayForAirport(icaoB);
        if (b == null) {
            throw new InvalidDataException("No runways for airport with ICAO " + icaoB);
        }

        return computeDistance(a, b);
    }

    public double computeDistance(Runway a, Runway b) {
        return Util.computeGPSDistance(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
    }
}
