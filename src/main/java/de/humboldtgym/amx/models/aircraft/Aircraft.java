package de.humboldtgym.amx.models.aircraft;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.models.Airline;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

public abstract class Aircraft {
    private String registration;
    private String icao;
    /**
     * Aircraft's body length in meters (m).
     */
    private double length;
    /**
     * Aircraft's body width in meters (m).
     */
    private double width;
    /**
     * Aircraft's body height in meters (m).
     */
    private double height;
    /**
     * Aircraft's maximum flight speed in knots (kts), which is nautical/sea miles per hour.
     */
    private int flightSpeedPerHour;
    /**
     * Aircraft's body weight in kilograms (kg).
     */
    private int emptyWeight;
    /**
     * Aircraft's maximum flight weight in kilograms (kg).
     */
    private int maxWeight;
    private WeightClass weightClass;
    /**
     * Aircraft's maximum fuel capacity in kilograms (kg).
     */
    private int maxFuel;
    /**
     * Aircraft's fuel consumption in kilograms (kg) per hour.
     */
    private double fuelPerHour;
    /**
     * Aircraft's maintenance interval in hours.
     */
    private int maintenanceInterval;
    /**
     * Aircraft's time to next maintenance in hours.
     */
    private double timeToNextMaintenance;
    private Date bought;
    private double flightHours;
    private String location;
    private int minPilots;

    public Aircraft(
            String registration,
            String icao,
            double length,
            double width,
            double height,
            int flightSpeedPerHour,
            int emptyWeight,
            int maxWeight,
            WeightClass weightClass,
            int maxFuel,
            double fuelPerHour,
            int maintenanceInterval,
            double timeToNextMaintenance,
            Date bought,
            double flightHours,
            String location,
            int minPilots
    ) {
        this.registration = registration;
        this.icao = icao;
        this.length = length;
        this.width = width;
        this.height = height;
        this.flightSpeedPerHour = flightSpeedPerHour;
        this.emptyWeight = emptyWeight;
        this.maxWeight = maxWeight;
        this.weightClass = weightClass;
        this.maxFuel = maxFuel;
        this.fuelPerHour = fuelPerHour;
        this.maintenanceInterval = maintenanceInterval;
        this.timeToNextMaintenance = timeToNextMaintenance;
        this.bought = bought;
        this.flightHours = flightHours;
        this.location = location;
        this.minPilots = minPilots;
    }

    public boolean requireMaintenance() {
        if (getTimeToNextMaintenance() > getMaintenanceInterval()) {
            LogManager.getLogger().error(String.format("Next maintenance for %s must not be later than maximum maintenance interval.", getRegistration()));
            return true;
        }
        if (getTimeToNextMaintenance() <= 0) {
            LogManager.getLogger().error(String.format("%s must be maintained.", getRegistration()));
            return true;
        }
        return false;
    }

    public void fly(String dest, Airline airline) {
        if (checkFlightDataForErrors(dest)) return;
        try {
            double actualRangeNM = Util.convertDistanceKMToNM(Application.getInstance().getAirportManager().computeDistance(getLocation(), dest));
            addFlightHours(actualRangeNM / getFlightSpeedPerHour());
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            return;
        }
        boarding(airline);
        startEngines();
        setLocation(dest);
    }

    /**
     * @param dest Flight destination.
     * @return false - no errors and invalid data found<br>
     * true - invalid flight data or error in flight data.
     */
    public boolean checkFlightDataForErrors(String dest) {
        if (requireMaintenance()) {
            LogManager.getLogger().error(String.format("%s cannot depart. Aircraft needs maintenance.", getRegistration()));
            return true;
        }
        try {
            double maxRangeKM = Util.convertDistanceNMToKM(getFlightSpeedPerHour() / (getMaxFuel() / getFuelPerHour()));
            double actualRangeKM = Application.getInstance().getAirportManager().computeDistance(getLocation(), dest);
            if (actualRangeKM > maxRangeKM) {
                LogManager.getLogger().error(String.format("%s cannot depart. The aircraft cannot fly that long. Your selected Route has a distance of %fkm. The aircraft can fly a maximum distance of %fkm.", getRegistration(), actualRangeKM, maxRangeKM));
                return true;
            }
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            return true;
        }
        return false;
    }

    public abstract void boarding(Airline airline);

    public abstract void startEngines();

    @JsonProperty
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @JsonProperty
    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    @JsonProperty
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @JsonProperty
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @JsonProperty
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @JsonProperty
    public int getFlightSpeedPerHour() {
        return flightSpeedPerHour;
    }

    public void setFlightSpeedPerHour(int flightSpeedPerHour) {
        this.flightSpeedPerHour = flightSpeedPerHour;
    }

    @JsonProperty
    public int getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(int emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    @JsonProperty
    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    @JsonProperty
    public WeightClass getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(WeightClass weightClass) {
        this.weightClass = weightClass;
    }

    @JsonProperty
    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    @JsonProperty
    public double getFuelPerHour() {
        return fuelPerHour;
    }

    public void setFuelPerHour(double fuelPerHour) {
        this.fuelPerHour = fuelPerHour;
    }

    @JsonProperty
    public int getMaintenanceInterval() {
        return maintenanceInterval;
    }

    public void setMaintenanceInterval(int maintenanceInterval) {
        this.maintenanceInterval = maintenanceInterval;
    }

    @JsonProperty
    public Date getBought() {
        return bought;
    }

    public void setBought(Date bought) {
        this.bought = bought;
    }

    @JsonProperty
    public double getFlightHours() {
        return flightHours;
    }

    public void addFlightHours(double hours) {
        this.flightHours += hours;
        this.timeToNextMaintenance += hours;
    }

    public void setFlightHours(double flightHours) {
        this.flightHours = flightHours;
    }

    @JsonProperty
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty
    public int getMinPilots() {
        return minPilots;
    }

    public void setMinPilots(int minPilots) {
        this.minPilots = minPilots;
    }

    public void maintain() {
        this.timeToNextMaintenance = this.maintenanceInterval;
    }

    @JsonProperty
    public double getTimeToNextMaintenance() {
        return timeToNextMaintenance;
    }

    public void setTimeToNextMaintenance(double timeToNextMaintenance) {
        this.timeToNextMaintenance = timeToNextMaintenance;
    }
}
