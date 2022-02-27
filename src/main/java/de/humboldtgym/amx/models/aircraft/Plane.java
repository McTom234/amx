package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.models.Airline;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

public abstract class Plane extends Aircraft {
	private int minRunwayLength;
	private double wingSpan;
	private boolean winglets;
	private int engines;

	public Plane(
			String registration,
			String icao,
			double length,
			double width,
			double height,
			int flightSpeedPerHour,
			int emptyWeight,
			int maxWeight,
			int maxFuel,
			double fuelPerHour,
			int maintenanceInterval,
			double timeToNextMaintenance,
			Date bought,
			double flightHours,
			String location,
			int minPilots,
			int minRunwayLength,
			double wingSpan,
			boolean winglets,
			int engines
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
				maxFuel, fuelPerHour,
				maintenanceInterval,
				timeToNextMaintenance,
				bought,
				flightHours,
				location,
				minPilots
		);
		this.minRunwayLength = minRunwayLength;
		this.wingSpan = wingSpan;
		this.winglets = winglets;
		this.engines = engines;
	}

	@Override
	public boolean checkFlightDataForErrors(String dest) {
		super.checkFlightDataForErrors(dest);
		if (getMinRunwayLength() > Application.getInstance().getAirportManager().getLongestRunwayForAirport(dest).getLength()) {
			LogManager.getLogger().error(String.format("%s cannot depart. Runway length at %s too short!", getRegistration(), dest));
			return true;
		}
		return false;
	}

	@Override
	public void startEngines() {
		LogManager.getLogger().info(String.format("%s started %d engines. Consuming %f fuel per hour.", getRegistration(), getEngines(), getFuelPerHour()));
	}

	public double getWingSpan() {
		return wingSpan;
	}

	public void setWingSpan(double wingSpan) {
		this.wingSpan = wingSpan;
	}

	public boolean isWinglets() {
		return winglets;
	}

	public void setWinglets(boolean winglets) {
		this.winglets = winglets;
	}

	public int getEngines() {
		return engines;
	}

	public void setEngines(int engines) {
		this.engines = engines;
	}

	public int getMinRunwayLength() {
		return minRunwayLength;
	}

	public void setMinRunwayLength(int minRunwayLength) {
		this.minRunwayLength = minRunwayLength;
	}
}
