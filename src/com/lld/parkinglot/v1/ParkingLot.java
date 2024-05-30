package com.lld.parkinglot.v1;

import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private String name;
    private int capacity;
    private List<ParkingSpot> availableSpots;

    public ParkingLot ( String name , int capacity ) {
        this.name = name;
        this.capacity = capacity;
        this.availableSpots = new ArrayList<>();
        for (int i = 1 ; i <= capacity ; i++) {
            this.availableSpots.add(new ParkingSpot(i));
        }
    }

    public int getCapacity () {
        return capacity;
    }

    public int getAvailableSpots () {
        return (int) availableSpots.stream().filter(spot -> !spot.isOccupied()).count();
    }

    public ParkingTicket parkVehicle ( Vehicle vehicle ) {
        ParkingSpot spot = availableSpots.stream().filter(s -> !s.isOccupied()).findFirst().orElse(null);
        if (spot != null) {
            spot.occupy();
            return new ParkingTicket(1 , vehicle , spot);
        }
        return null; // No available spots
    }

    public void vacateSpot ( ParkingTicket ticket ) {
        ticket.getParkingSpot().vacate();
    }
}
