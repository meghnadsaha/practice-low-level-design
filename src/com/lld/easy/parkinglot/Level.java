package com.lld.easy.parkinglot;

import java.util.ArrayList;
import java.util.List;

class Level {
    protected final List<ParkingSpot> spots;

    public Level ( int numOfSpots ) {
        spots = new ArrayList<>(numOfSpots);
        for (int i = 0 ; i < numOfSpots ; i++) {
            spots.add(new ParkingSpot(VehicleType.CAR)); // Assuming all spots are for cars
        }
    }

    public synchronized ParkingSpot parkVehicle ( Vehicle vehicle ) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.getType() == vehicle.getType()) {
                spot.parkVehicle(vehicle);
                return spot;
            }
        }
        return null; // No available spot
    }

    public synchronized void removeVehicle ( ParkingSpot spot ) {
        spot.removeVehicle();
    }
}
