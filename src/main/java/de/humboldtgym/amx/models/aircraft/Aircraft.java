package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.auxiliary.Util;
import de.humboldtgym.amx.models.Airline;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

public abstract class Aircraft {
	private String registration;
	private String icao;
	/** Aircraft's body length in meters (m). */
	private double length;
	/** Aircraft's body width in meters (m). */
	private double width;
	/** Aircraft's body height in meters (m). */
	private double height;
	/** Aircraft's maximum flight speed in knots (kts), which is nautical/sea miles per hour. */
	private int flightSpeedPerHour;
	/** Aircraft's body weight in kilograms (kg). */
	private int emptyWeight;
	/** Aircraft's maximum flight weight in kilograms (kg). */
	private int maxWeight;
	private WeightClass weightClass;
	/** Aircraft's maximum fuel capacity in kilograms (kg). */
	private int maxFuel;
	/** Aircraft's fuel consumption in kilograms (kg) per hour. */
	private double fuelPerHour;
	/** Aircraft's maintenance interval in hours. */
	private int maintenanceInterval;
	/** Aircraft's time to next maintenance in hours. */
	private double timeToNextMaintenance;
	private Date bought;
	private double flightHours;
	private String location;
	private int minPilots;

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
	 * @return 	false - no errors and invalid data found<br>
	 * 			true - invalid flight data or error in flight data.
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

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getFlightSpeedPerHour() {
		return flightSpeedPerHour;
	}

	public void setFlightSpeedPerHour(int flightSpeedPerHour) {
		this.flightSpeedPerHour = flightSpeedPerHour;
	}

	public int getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(int emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public WeightClass getWeightClass() {
		return weightClass;
	}

	public void setWeightClass(WeightClass weightClass) {
		this.weightClass = weightClass;
	}

	public int getMaxFuel() {
		return maxFuel;
	}

	public void setMaxFuel(int maxFuel) {
		this.maxFuel = maxFuel;
	}

	public double getFuelPerHour() {
		return fuelPerHour;
	}

	public void setFuelPerHour(double fuelPerHour) {
		this.fuelPerHour = fuelPerHour;
	}

	public int getMaintenanceInterval() {
		return maintenanceInterval;
	}

	public void setMaintenanceInterval(int maintenanceInterval) {
		this.maintenanceInterval = maintenanceInterval;
	}

	public Date getBought() {
		return bought;
	}

	public void setBought(Date bought) {
		this.bought = bought;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMinPilots() {
		return minPilots;
	}

	public void setMinPilots(int minPilots) {
		this.minPilots = minPilots;
	}

	public void maintain() {
		this.timeToNextMaintenance = this.maintenanceInterval;
	}

	public double getTimeToNextMaintenance() {
		return timeToNextMaintenance;
	}

	public void setTimeToNextMaintenance(double timeToNextMaintenance) {
		this.timeToNextMaintenance = timeToNextMaintenance;
	}
}
