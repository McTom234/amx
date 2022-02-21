package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.enums.WeightClass;

public class CargoAircraft extends Aircraft {
	private int maxCargoA;
	private int maxCargoB;
	private int maxCargoC;
	private boolean frontHatch;
	private boolean sideHatch;
	private boolean backHatch;
	private int minPilots;

	public CargoAircraft(String icao, int length, int span, int height, int emptyWeight, int maxWeight, WeightClass weightClass, int maxFuel, int fuelPerHour, int maintenanceInterval) {
		super(icao, length, span, height, emptyWeight, maxWeight, weightClass, maxFuel, fuelPerHour, maintenanceInterval);
	}
}
