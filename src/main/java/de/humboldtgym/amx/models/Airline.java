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
    private double weightPerPassenger;
    private double weightCargoA;
    private double weightCargoB;
    private double weightCargoC;

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
            @JsonProperty(required = true, value = "name") String name,
            @JsonProperty(required = true, value = "primaryColor") int primaryColor,
            @JsonProperty(required = true, value = "secondaryColor") int secondaryColor,
            @JsonProperty(required = true, value = "weightPerPassenger") double weightPerPassenger,
            @JsonProperty(required = true, value = "weightCargoA") double weightCargoA,
            @JsonProperty(required = true, value = "weightCargoC") double weightCargoC,
            @JsonProperty(required = true, value = "weightCargoB") double weightCargoB,
            @JsonProperty(required = true, value = "fleet") List<Aircraft> fleet,
            @JsonProperty(required = true, value = "routes") List<Route> routes,
            @JsonProperty(required = true, value = "flights") List<Flight> flights,
            @JsonProperty(required = true, value = "customers") List<Customer> customers,
            @JsonProperty(required = true, value = "employees") List<Employee> employees
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
    public double getWeightPerPassenger() {
        return weightPerPassenger;
    }

    public void setWeightPerPassenger(double weightPerPassenger) {
        this.weightPerPassenger = weightPerPassenger;
    }

    @JsonProperty
    public double getWeightCargoA() {
        return weightCargoA;
    }

    public void setWeightCargoA(double weightCargoA) {
        this.weightCargoA = weightCargoA;
    }

    @JsonProperty
    public double getWeightCargoB() {
        return weightCargoB;
    }

    public void setWeightCargoB(double weightCargoB) {
        this.weightCargoB = weightCargoB;
    }

    @JsonProperty
    public double getWeightCargoC() {
        return weightCargoC;
    }

    public void setWeightCargoC(double weightCargoC) {
        this.weightCargoC = weightCargoC;
    }

    @JsonProperty
    public List<Aircraft> getFleet() {
        return fleet;
    }

    @JsonProperty
    public List<Customer> getCustomers() {
        return customers;
    }

    @JsonProperty
    public List<Employee> getEmployees() {
        return employees;
    }

    @JsonProperty
    public List<Flight> getFlights() {
        return flights;
    }

    @JsonProperty
    public List<Route> getRoutes() {
        return routes;
    }
}
