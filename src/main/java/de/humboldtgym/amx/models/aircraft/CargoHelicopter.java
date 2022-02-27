package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.Airline;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

public class CargoHelicopter extends Helicopter {
	private int maxCargoA;
	private int maxCargoB;
	private int maxCargoC;
	private boolean frontHatch;
	private boolean sideHatch;
	private boolean backHatch;

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

	public int getMaxCargoA() {
		return maxCargoA;
	}

	public void setMaxCargoA(int maxCargoA) {
		this.maxCargoA = maxCargoA;
	}

	public int getMaxCargoB() {
		return maxCargoB;
	}

	public void setMaxCargoB(int maxCargoB) {
		this.maxCargoB = maxCargoB;
	}

	public int getMaxCargoC() {
		return maxCargoC;
	}

	public void setMaxCargoC(int maxCargoC) {
		this.maxCargoC = maxCargoC;
	}

	public boolean isFrontHatch() {
		return frontHatch;
	}

	public void setFrontHatch(boolean frontHatch) {
		this.frontHatch = frontHatch;
	}

	public boolean isSideHatch() {
		return sideHatch;
	}

	public void setSideHatch(boolean sideHatch) {
		this.sideHatch = sideHatch;
	}

	public boolean isBackHatch() {
		return backHatch;
	}

	public void setBackHatch(boolean backHatch) {
		this.backHatch = backHatch;
	}
}
