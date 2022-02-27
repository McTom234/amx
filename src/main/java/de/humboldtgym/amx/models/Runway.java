package de.humboldtgym.amx.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Runway {
    private final String icao;
    private final int heading;
    private final double latitude;
    private final double longitude;
    private final int length;

    @JsonCreator
    public Runway(
            @JsonProperty("ICAO") String icao,
            @JsonProperty("RWY") int heading,
            @JsonProperty("LAT") double latitude,
            @JsonProperty("LON") double longitude,
            @JsonProperty("LENGTH") int length
    ) {
        this.icao = icao;
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.length = length;
    }

    public String getICAO() {
        return icao;
    }

    public int getHeading() {
        return heading;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getLength() {
        return length;
    }
}
