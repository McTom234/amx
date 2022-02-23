package de.humboldtgym.amx.models.aircraft;

public class Helicopter extends Aircraft {
	private int rotors;
	private int rotorSpan;

	public int getRotors() {
		return rotors;
	}

	public void setRotors(int rotors) {
		this.rotors = rotors;
	}

	public int getRotorSpan() {
		return rotorSpan;
	}

	public void setRotorSpan(int rotorSpan) {
		this.rotorSpan = rotorSpan;
	}
}
