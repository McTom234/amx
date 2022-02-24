package de.humboldtgym.amx.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.humboldtgym.amx.models.aircraft.Aircraft;
import de.humboldtgym.amx.models.bookings.Customer;
import de.humboldtgym.amx.models.flight.Flight;
import de.humboldtgym.amx.models.flight.Route;
import de.humboldtgym.amx.models.persons.Employee;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    private String name;
    private int primaryColor;
    private int secondaryColor;
    private int weightPerPassenger;
    private int weightCargoA;
    private int weightCargoB;
    private int weightCargoC;

    private final List<Aircraft> fleet;
    private final List<Route> routes;
    private final List<Flight> flights;
    private final List<Customer> customers;
    private final List<Employee> employees;

    public Airline(
            String name,
            int primaryColor,
            int secondaryColor,
            int weightPerPassenger,
            int weightCargoA,
            int weightCargoB,
            int weightCargoC
    ) {
        this.name = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.weightPerPassenger = weightPerPassenger;
        this.weightCargoA = weightCargoA;
        this.weightCargoB = weightCargoB;
        this.weightCargoC = weightCargoC;

        this.fleet = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    @JsonCreator
    public Airline(
            @JsonProperty("name") String name,
            @JsonProperty("primaryColor") int primaryColor,
            @JsonProperty("secondaryColor") int secondaryColor,
            @JsonProperty("weightPerPassenger") int weightPerPassenger,
            @JsonProperty("weightCargoA") int weightCargoA,
            @JsonProperty("weightCargoB") int weightCargoB,
            @JsonProperty("weightCargoC") int weightCargoC,
            @JsonProperty("fleet") List<Aircraft> fleet,
            @JsonProperty("routes") List<Route> routes,
            @JsonProperty("flights") List<Flight> flights,
            @JsonProperty("customers") List<Customer> customers,
            @JsonProperty("employees") List<Employee> employees
    ) {
        this.name = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.weightPerPassenger = weightPerPassenger;
        this.weightCargoA = weightCargoA;
        this.weightCargoB = weightCargoB;
        this.weightCargoC = weightCargoC;
        this.fleet = fleet;
        this.routes = routes;
        this.flights = flights;
        this.customers = customers;
        this.employees = employees;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    @JsonProperty
    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @JsonProperty
    public int getWeightPerPassenger() {
        return weightPerPassenger;
    }

    public void setWeightPerPassenger(int weightPerPassenger) {
        this.weightPerPassenger = weightPerPassenger;
    }

    @JsonProperty
    public int getWeightCargoA() {
        return weightCargoA;
    }

    public void setWeightCargoA(int weightCargoA) {
        this.weightCargoA = weightCargoA;
    }

    @JsonProperty
    public int getWeightCargoB() {
        return weightCargoB;
    }

    public void setWeightCargoB(int weightCargoB) {
        this.weightCargoB = weightCargoB;
    }

    @JsonProperty
    public int getWeightCargoC() {
        return weightCargoC;
    }

    public void setWeightCargoC(int weightCargoC) {
        this.weightCargoC = weightCargoC;
    }
}
