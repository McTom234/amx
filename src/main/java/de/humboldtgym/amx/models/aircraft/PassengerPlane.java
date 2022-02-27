package de.humboldtgym.amx.models.aircraft;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.models.Airline;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;
import java.util.Random;

public class PassengerPlane extends Plane implements PassengerAircraft {
    private int maxPassengers;

    @JsonCreator
    public PassengerPlane(
            @JsonProperty(required = true, value = "registration") String registration,
            @JsonProperty(required = true, value = "icao") String icao,
            @JsonProperty(required = true, value = "length") double length,
            @JsonProperty(required = true, value = "width") double width,
            @JsonProperty(required = true, value = "height") double height,
            @JsonProperty(required = true, value = "flightSpeedPerHour") int flightSpeedPerHour,
            @JsonProperty(required = true, value = "emptyWeight") int emptyWeight,
            @JsonProperty(required = true, value = "maxWeight") int maxWeight,
            @JsonProperty(required = true, value = "weightClass") WeightClass weightClass,
            @JsonProperty(required = true, value = "maxFuel") int maxFuel,
            @JsonProperty(required = true, value = "fuelPerHour") double fuelPerHour,
            @JsonProperty(required = true, value = "maintenanceInterval") int maintenanceInterval,
            @JsonProperty(required = true, value = "timeToNextMaintenance") double timeToNextMaintenance,
            @JsonProperty(required = true, value = "bought") Date bought,
            @JsonProperty(required = true, value = "flightHours") double flightHours,
            @JsonProperty(required = true, value = "location") String location,
            @JsonProperty(required = true, value = "minPilots") int minPilots,
            @JsonProperty(required = true, value = "minRunwayLength") int minRunwayLength,
            @JsonProperty(required = true, value = "wingSpan") double wingSpan,
            @JsonProperty(required = true, value = "winglets") boolean winglets,
            @JsonProperty(required = true, value = "engines") int engines,
            @JsonProperty(required = true, value = "maxPassengers") int maxPassengers
    ) {
        super(
                registration,
                icao,
                length,
                width,
                height,
                flightSpeedPerHour,
                emptyWeight,
                maxWeight,
                weightClass,
                maxFuel,
                fuelPerHour,
                maintenanceInterval,
                timeToNextMaintenance,
                bought,
                flightHours,
                location,
                minPilots,
                minRunwayLength,
                wingSpan,
                winglets,
                engines
        );

        this.maxPassengers = maxPassengers;
    }

    @Override
    public void boarding(Airline airline) {
        LogManager.getLogger().info(String.format("Connecting jetway at Airport %s to aircraft %s...", getLocation(), getRegistration()));
        LogManager.getLogger().info(String.format("Starting boarding %s...", getRegistration()));
        int passengers = 1;
        Random random = new Random();
        do {
            if (passengers == 0) {
                LogManager.getLogger().error(String.format("Cannot board any passengers to %s. The maximum weight is probably exceeded. Double check before departure!", getRegistration()));
                continue;
            }
            passengers = random.nextInt(getMaxPassengers() + 1);
        } while (passengers * airline.getWeightPerPassenger() <= getMaxWeight());
        LogManager.getLogger().info(String.format("Boarded %d passengers to %s. Boarding completed.", passengers, getRegistration()));
    }

    @JsonProperty
    @Override
    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }
}
