package com.lld.parkinglot.v3;

import com.lld.parkinglot.v2.Vehicle;

import java.util.List;

/**
 * The Level class represents a level in the parking lot and contains a list of parking spots.
 * It handles parking and unparking of vehicles within the level.
 */
public class Level {

    private int floorNumber;
    private List<ParkingSpot> spots;

    public boolean parkVehicle ( Vehicle vehicle ) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getType() == vehicle.getType()) {
                spot.park(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle ( Vehicle vehicle ) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() != null && spot.getVehicle().equals(vehicle)) {
                spot.unPark(vehicle);
                return true;
            }
        }
        return false;
    }

    // Getters and setters
    public int getFloorNumber () {
        return floorNumber;
    }

    public void setFloorNumber ( int floorNumber ) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSpot> getSpots () {
        return spots;
    }

    public void setSpots ( List<ParkingSpot> spots ) {
        this.spots = spots;
    }
}
