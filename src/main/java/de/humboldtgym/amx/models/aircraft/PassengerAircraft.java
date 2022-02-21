package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;

public class PassengerAircraft extends Aircraft {
	private int maxPassengers;
	private int minPilots;

	public PassengerAircraft(String icao, int length, int span, int height, int emptyWeight, int maxWeight, WeightClass weightClass, int maxFuel, int fuelPerHour, int maintenanceInterval) {
		super(icao, length, span, height, emptyWeight, maxWeight, weightClass, maxFuel, fuelPerHour, maintenanceInterval);
	}
}
