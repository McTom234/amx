package de.humboldtgym.amx.models.aircraft;

import org.apache.logging.log4j.LogManager;

public class Helicopter extends Aircraft {
	private int rotors;
	private double rotorSpan;

	@Override
	public void startEngines() {
		LogManager.getLogger().info(String.format("%s started rotors. Consuming %d fuel per hour.", getRegistration(), getFuelPerHour()));
	}

	public int getRotors() {
		return rotors;
	}

	public void setRotors(int rotors) {
		this.rotors = rotors;
	}

	public double getRotorSpan() {
		return rotorSpan;
	}

	public void setRotorSpan(double rotorSpan) {
		this.rotorSpan = rotorSpan;
	}
}
