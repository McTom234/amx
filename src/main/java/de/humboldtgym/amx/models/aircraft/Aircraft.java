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
}
