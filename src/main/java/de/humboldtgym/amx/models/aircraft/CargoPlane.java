package de.humboldtgym.amx.models.aircraft;

public class CargoPlane extends Plane {
	private int maxCargoA;
	private int maxCargoB;
	private int maxCargoC;
	private boolean frontHatch;
	private boolean sideHatch;
	private boolean backHatch;

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
