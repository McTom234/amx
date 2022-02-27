package de.humboldtgym.amx.models.aircraft;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

public abstract class Helicopter extends Aircraft {
	private int rotors;
	private double rotorSpan;

	public Helicopter(
			String registration,
			String icao,
			double length,
			double width,
			double height,
			int flightSpeedPerHour,
			int emptyWeight,
			int maxWeight,
			WeightClass weightClass,
			int maxFuel,
			double fuelPerHour,
			int maintenanceInterval,
			double timeToNextMaintenance,
			Date bought,
			double flightHours,
			String location,
			int minPilots,
			int rotors,
			double rotorSpan
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
				weightClass,
				maxFuel,
				fuelPerHour,
				maintenanceInterval,
				timeToNextMaintenance,
				bought,
				flightHours,
				location,
				minPilots
		);
		this.rotors = rotors;
		this.rotorSpan = rotorSpan;
	}

	@Override
	public void startEngines() {
		LogManager.getLogger().info(String.format("%s started rotors. Consuming %d fuel per hour.", getRegistration(), getFuelPerHour()));
	}

	@JsonProperty
	public int getRotors() {
		return rotors;
	}

	public void setRotors(int rotors) {
		this.rotors = rotors;
	}

	@JsonProperty
	public double getRotorSpan() {
		return rotorSpan;
	}

	public void setRotorSpan(double rotorSpan) {
		this.rotorSpan = rotorSpan;
	}
}
