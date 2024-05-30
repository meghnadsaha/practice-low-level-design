package com.lld.parkinglot.v2;

import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private String name;
    private List<Level> levels;

    public ParkingLot ( String name , int numLevels , int spotsPerLevel ) {
        this.name = name;
        this.levels = new ArrayList<>();
        for (int i = 1 ; i <= numLevels ; i++) {
            this.levels.add(new Level(i , spotsPerLevel));
        }
    }

    public int getAvailableSpots () {
        return levels.stream().mapToInt(Level::getAvailableSpots).sum();
    }

    public ParkingTicket parkVehicle ( Vehicle vehicle ) {
        for (Level level : levels) {
            ParkingSpot spot = level.findSpotForVehicle(vehicle);
            if (spot != null) {
                spot.occupy();
                return new ParkingTicket(1 , vehicle , spot);  // In real system, ticket ID should be generated uniquely
            }
        }
        return null; // No available spots
    }

    public void vacateSpot ( ParkingTicket ticket ) {
        ticket.getParkingSpot().vacate();
    }
}
