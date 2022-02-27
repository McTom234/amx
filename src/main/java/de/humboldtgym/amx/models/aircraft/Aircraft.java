package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

public abstract class Aircraft {
	private String registration;
	private String icao;
	private double length;
	private double width;
	private double height;
	private int flightSpeedPerHour;
	private int emptyWeight;
	private int maxWeight;
	private WeightClass weightClass;
	private int maxFuel;
	private double fuelPerHour;
	private int maintenanceInterval;
	private double timeToNextMaintenance;
	private Date bought;
	private double flightHours;
	private String location;
	private int minPilots;

	public boolean requireMaintenance() {
		if (timeToNextMaintenance > maintenanceInterval) {
			LogManager.getLogger().error(String.format("Next maintenance for %s must not be later than maximum maintenance interval.", getRegistration()));
			return true;
		}
		if (timeToNextMaintenance <= 0) {
			LogManager.getLogger().error(String.format("%s must be maintained.", getRegistration()));
			return true;
		}
		return false;
	}

	public void fly(String dest) {
		if (!checkFlightData(dest)) return;
		this.location = dest;
		addFlightHours(0); // TODO add flight time API
		startEngines();
	}

	public boolean checkFlightData(String dest) {
		if (requireMaintenance()) {
			LogManager.getLogger().error(String.format("%s cannot depart. Aircraft needs maintenance.", getRegistration()));
			return false;
		}
		int flightTime = 0;
		double maxFlightTime = getMaxFuel() / getFuelPerHour();
		if (0 > maxFlightTime) { // TODO add flight time API
			LogManager.getLogger().error(String.format("%s cannot depart. The aircraft cannot fly that long. Your selected Route has a flight time of %d hours. The aircraft can fly for a maximum time of %f hours.", getRegistration(), flightTime, maxFlightTime));
			return false;
		}
		return true;
	};

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

	public void addFlightHours(int hours) {
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
