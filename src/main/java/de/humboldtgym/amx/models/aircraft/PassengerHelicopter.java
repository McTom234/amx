package de.humboldtgym.amx.models.aircraft;

import de.humboldtgym.amx.models.Airline;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

public class PassengerHelicopter extends Helicopter {
	private int maxPassengers;

	@Override
	public void boarding(Airline airline) {
		LogManager.getLogger().info(String.format("Starting boarding %s...", getRegistration()));
		int passengers = 1;
		Random random = new Random();
		do {
			if (passengers == 0) {
				LogManager.getLogger().error(String.format("Cannot board any passengers to %s. The maximum weight is probably exceeded. Double check before departure!", getRegistration()));
				continue;
			}
			passengers = random.nextInt(getMaxPassengers()+1);
		} while (passengers * airline.getWeightPerPassenger() <= getMaxWeight());
		LogManager.getLogger().info(String.format("Boarded %d passengers to %s. Boarding completed.", passengers, getRegistration()));
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}
}
