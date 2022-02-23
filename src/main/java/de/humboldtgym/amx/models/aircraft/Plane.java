package de.humboldtgym.amx.models.aircraft;

public class Plane extends Aircraft {
	private int wingSpan;
	private boolean winglets;
	private boolean sharklets;
	private int engines;

	public int getWingSpan() {
		return wingSpan;
	}

	public void setWingSpan(int wingSpan) {
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
}
