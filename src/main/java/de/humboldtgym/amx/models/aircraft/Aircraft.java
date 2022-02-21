package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;

import java.util.Date;

public class Aircraft extends AircraftType {
	private String registration;
	private Date bought;
	private int flightHours;
	private boolean winglets;
	private boolean sharklets;
	private int engines;
	private String location;

	public Aircraft(String icao, int length, int span, int height, int emptyWeight, int maxWeight, WeightClass weightClass, int maxFuel, int fuelPerHour, int maintenanceInterval) {
		super(icao, length, span, height, emptyWeight, maxWeight, weightClass, maxFuel, fuelPerHour, maintenanceInterval);
	}
}
