package com.lld.parkinglot.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Level {
    private int id;
    private List<ParkingSpot> spots;

    public Level ( int id , int numSpots ) {
        this.id = id;
        this.spots = new ArrayList<>();
        for (int i = 1 ; i <= numSpots ; i++) {
            // Randomly assign spot type for simplicity; in real system, it would be more complex
            VehicleType type = VehicleType.values()[new Random().nextInt(VehicleType.values().length)];
            this.spots.add(new ParkingSpot(i , type));
        }
    }

    public int getId () {
        return id;
    }

    public int getAvailableSpots () {
        return (int) spots.stream().filter(spot -> !spot.isOccupied()).count();
    }

    public ParkingSpot findSpotForVehicle ( Vehicle vehicle ) {
        return spots.stream()
                    .filter(spot -> !spot.isOccupied() && spot.getType() == vehicle.getType())
                    .findFirst()
                    .orElse(null);
    }
}
