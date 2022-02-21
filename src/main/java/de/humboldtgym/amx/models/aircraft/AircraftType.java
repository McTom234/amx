package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;

public class AircraftType {
	private String icao;
	private int length;
	private int span;
	private int height;
	private int emptyWeight;
	private int maxWeight;
	private WeightClass weightClass;
	private int maxFuel;
	private int fuelPerHour;
	private int maintenanceInterval;

	public AircraftType(String icao, int length, int span, int height, int emptyWeight, int maxWeight, WeightClass weightClass, int maxFuel, int fuelPerHour, int maintenanceInterval) {
		this.icao = icao;
		this.length = length;
		this.span = span;
		this.height = height;
		this.emptyWeight = emptyWeight;
		this.maxWeight = maxWeight;
		this.weightClass = weightClass;
		this.maxFuel = maxFuel;
		this.fuelPerHour = fuelPerHour;
		this.maintenanceInterval = maintenanceInterval;
	}

	public Aircraft newAircraftOfType() {
		return (Aircraft) this;
	}
}
