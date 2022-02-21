package de.humboldtgym.amx.models;

import de.humboldtgym.amx.models.aircraft.Aircraft;
import de.humboldtgym.amx.models.aircraft.AircraftType;
import de.humboldtgym.amx.models.bookings.Customer;
import de.humboldtgym.amx.models.flight.Flight;
import de.humboldtgym.amx.models.flight.Route;
import de.humboldtgym.amx.models.persons.Employee;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Airline {
    private String name;
    private String primaryColor;
    private String secondaryColor;
    private BufferedImage logo;
    private int weightPerPassenger;
    private int weightCargoA;
    private int weightCargoB;
    private int weightCargoC;

    private ArrayList<AircraftType> aircraftTypes;
    private ArrayList<Aircraft> fleet;
    private ArrayList<Route> routes;
    private ArrayList<Flight> flights;
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
}
