package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;

import java.util.Date;

public class Aircraft {
	private String registration;
	private String icao;
	private int length;
	private int width;
	private int height;
	private int emptyWeight;
	private int maxWeight;
	private WeightClass weightClass;
	private int maxFuel;
	private int fuelPerHour;
	private int maintenanceInterval;
	private Date bought;
	private int flightHours;
	private String location;
	private int minPilots;

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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public int getFuelPerHour() {
		return fuelPerHour;
	}

	public void setFuelPerHour(int fuelPerHour) {
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

	public int getFlightHours() {
		return flightHours;
	}

	public void setFlightHours(int flightHours) {
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
}
