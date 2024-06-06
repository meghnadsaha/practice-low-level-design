package com.lld.easy.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private static ParkingLot instance;
    private final List<Level> levels;

    private ParkingLot ( int numOfLevels , int spotsPerLevel ) {
        levels = new ArrayList<>(numOfLevels);
        for (int i = 0 ; i < numOfLevels ; i++) {
            levels.add(new Level(spotsPerLevel));
        }
    }

    public static synchronized ParkingLot getInstance ( int numOfLevels , int spotsPerLevel ) {
        if (instance == null) {
            instance = new ParkingLot(numOfLevels , spotsPerLevel);
        }
        return instance;
    }

    public synchronized ParkingSpot parkVehicle ( Vehicle vehicle ) {
        for (Level level : levels) {
            ParkingSpot spot = level.parkVehicle(vehicle);
            if (spot != null) {
                return spot;
            }
        }
        return null; // No available spot
    }

    public synchronized void removeVehicle ( ParkingSpot spot ) {
        for (Level level : levels) {
            if (level.spots.contains(spot)) {
                level.removeVehicle(spot);
                break;
            }
        }
    }
}
