package de.humboldtgym.amx.io;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.models.Airline;

public class DataSet {
    private final Airline airline;

    public DataSet() {
        this.airline = new Airline(
                null,
                0,
                0,
                100,
                1000,
                1200,
                1400
        );
    }

    @JsonCreator
    public DataSet(@JsonProperty("airline") Airline airline) {
        this.airline = airline;
    }

    @JsonProperty
    public Airline getAirline() {
        return airline;
    }
}
