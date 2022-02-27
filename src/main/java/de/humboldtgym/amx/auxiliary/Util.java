package de.humboldtgym.amx.auxiliary;

import javax.swing.*;

public final class Util {
    private static final double EARTH_RADIUS_KM = 6371.0;

    private Util() {
        throw new AssertionError("Util is a static class");
    }

    // Adapted from https://stackoverflow.com/questions/365826/calculate-distance-between-2-gps-coordinates
    public static double computeGPSDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        var radLatitudeDiff = Math.toRadians(latitudeB - latitudeA);
        var radLongitudeDiff = Math.toRadians(longitudeB - longitudeA);

        var radLatitudeA = Math.toRadians(latitudeA);
        var radLatitudeB = Math.toRadians(latitudeB);

        var a = Math.pow(Math.sin(radLatitudeDiff / 2.0), 2) +
                Math.pow(Math.sin(radLongitudeDiff / 2.0), 2) *
                Math.cos(radLatitudeA) * Math.cos(radLatitudeB);

        var c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public static double convertDistanceKMToNM(double distanceKM) {
        return distanceKM * 0.54;
    }

    public static double convertDistanceNMToKM(double distanceNM) {
        return distanceNM * 1.852;
    }

    public static JMenuItem runnableItem(String label, Runnable action) {
        var item = new JMenuItem(label);
        item.addActionListener((e) -> action.run());
        return item;
    }
}
