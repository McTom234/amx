package de.humboldtgym.amx.models.aircraft;

public interface CargoAircraft {
    int getMaxCargoA();

    void setMaxCargoA(int maxCargoA);

    int getMaxCargoB();

    void setMaxCargoB(int maxCargoB);

    int getMaxCargoC();

    void setMaxCargoC(int maxCargoC);

    boolean isFrontHatch();

    void setFrontHatch(boolean frontHatch);

    boolean isSideHatch();

    void setSideHatch(boolean sideHatch);

    boolean isBackHatch();

    void setBackHatch(boolean backHatch);
}
