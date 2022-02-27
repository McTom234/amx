package de.humboldtgym.amx.models.aircraft;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.models.Airline;
import de.humboldtgym.amx.models.enums.WeightClass;
import org.apache.logging.log4j.LogManager;

import java.util.Date;
import java.util.Random;

public class CargoHelicopter extends Helicopter implements CargoAircraft {
	private int maxCargoA;
	private int maxCargoB;
	private int maxCargoC;
	private boolean frontHatch;
	private boolean sideHatch;
	private boolean backHatch;

	@JsonCreator
	public CargoHelicopter(
			@JsonProperty(required = true, value = "registration") String registration,
			@JsonProperty(required = true, value = "icao") String icao,
			@JsonProperty(required = true, value = "length") double length,
			@JsonProperty(required = true, value = "width") double width,
			@JsonProperty(required = true, value = "height") double height,
			@JsonProperty(required = true, value = "flightSpeedPerHour") int flightSpeedPerHour,
			@JsonProperty(required = true, value = "emptyWeight") int emptyWeight,
			@JsonProperty(required = true, value = "maxWeight") int maxWeight,
			@JsonProperty(required = true, value = "weightClass") WeightClass weightClass,
			@JsonProperty(required = true, value = "maxFuel") int maxFuel,
			@JsonProperty(required = true, value = "fuelPerHour") double fuelPerHour,
			@JsonProperty(required = true, value = "maintenanceInterval") int maintenanceInterval,
			@JsonProperty(required = true, value = "timeToNextMaintenance") double timeToNextMaintenance,
			@JsonProperty(required = true, value = "bought") Date bought,
			@JsonProperty(required = true, value = "flightHours") double flightHours,
			@JsonProperty(required = true, value = "location") String location,
			@JsonProperty(required = true, value = "minPilots") int minPilots,
			@JsonProperty(required = true, value = "rotors") int rotors,
			@JsonProperty(required = true, value = "rotorSpan") double rotorSpan,
			@JsonProperty(required = true, value = "maxCargoA") int maxCargoA,
			@JsonProperty(required = true, value = "maxCargoB") int maxCargoB,
			@JsonProperty(required = true, value = "maxCargoC") int maxCargoC,
			@JsonProperty(required = true, value = "frontHatch") boolean frontHatch,
			@JsonProperty(required = true, value = "sideHatch") boolean sideHatch,
			@JsonProperty(required = true, value = "backHatch") boolean backHatch
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
				minPilots,
				rotors,
				rotorSpan
		);
		this.maxCargoA = maxCargoA;
		this.maxCargoB = maxCargoB;
		this.maxCargoC = maxCargoC;
		this.frontHatch = frontHatch;
		this.sideHatch = sideHatch;
		this.backHatch = backHatch;
	}

	@Override
	public void boarding(Airline airline) {
		LogManager.getLogger().info(String.format("Opening hatches of aircraft %s at Airport %s...", getLocation(), getRegistration()));
		LogManager.getLogger().info(String.format("Starting loading %s...", getRegistration()));
		int cargoA = 1;
		int cargoB = 1;
		int cargoC = 1;
		Random random = new Random();
		do {
			if (cargoA == 0 && cargoB == 0 && cargoC == 0) {
				LogManager.getLogger().error(String.format("Cannot load any cargo to %s. The maximum weight is probably exceeded. Double check before departure!", getRegistration()));
				continue;
			}
			cargoA = random.nextInt(getMaxCargoA()+1);
			cargoB = random.nextInt(getMaxCargoB()+1);
			cargoC = random.nextInt(getMaxCargoC()+1);
		} while ((cargoA * airline.getWeightCargoA() + cargoB * airline.getWeightCargoB() + cargoC * airline.getWeightCargoC()) <= getMaxWeight());
		LogManager.getLogger().info(String.format("Loaded %d type A cargo boxes, %d type B cargo boxes and %d type C cargo boxes to %s. Boarding completed.", cargoA, cargoB, cargoC, getRegistration()));
	}

	@JsonProperty
	@Override
	public int getMaxCargoA() {
		return maxCargoA;
	}

	@Override
	public void setMaxCargoA(int maxCargoA) {
		this.maxCargoA = maxCargoA;
	}

	@JsonProperty
	@Override
	public int getMaxCargoB() {
		return maxCargoB;
	}

	@Override
	public void setMaxCargoB(int maxCargoB) {
		this.maxCargoB = maxCargoB;
	}

	@JsonProperty
	@Override
	public int getMaxCargoC() {
		return maxCargoC;
	}

	@Override
	public void setMaxCargoC(int maxCargoC) {
		this.maxCargoC = maxCargoC;
	}

	@JsonProperty
	@Override
	public boolean isFrontHatch() {
		return frontHatch;
	}

	@Override
	public void setFrontHatch(boolean frontHatch) {
		this.frontHatch = frontHatch;
	}

	@JsonProperty
	@Override
	public boolean isSideHatch() {
		return sideHatch;
	}

	@Override
	public void setSideHatch(boolean sideHatch) {
		this.sideHatch = sideHatch;
	}

	@JsonProperty
	@Override
	public boolean isBackHatch() {
		return backHatch;
	}

	@Override
	public void setBackHatch(boolean backHatch) {
		this.backHatch = backHatch;
	}
}
