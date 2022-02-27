package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.Application;
import de.humboldtgym.amx.models.Airline;
import org.apache.logging.log4j.LogManager;

public abstract class Plane extends Aircraft {
	private int minRunwayLength;
	private double wingSpan;
	private boolean winglets;
	private boolean sharklets;
	private int engines;

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

	public boolean isSharklets() {
		return sharklets;
	}

	public void setSharklets(boolean sharklets) {
		this.sharklets = sharklets;
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
