package com.lld.parkinglot.v3;

import com.lld.parkinglot.v2.Vehicle;

import java.util.List;

public class ParkingLot {

    private static ParkingLot instance;
    private List<Level> levels;


    public void setLevels ( List<Level> levels ) {
        this.levels = levels;
    }

    // Private constructor to prevent instantiation
    private ParkingLot () {
        // Initialize the levels (this could be loaded from a configuration or set up here)
        // levels = new ArrayList<>(); // or any other initialization logic
    }

    // Public method to provide access to the instance
    public static synchronized ParkingLot getInstance () {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public boolean parkVehicle ( Vehicle vehicle ) {
        for (Level level : levels) {
            if (!level.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }


    public boolean unParkVehicle ( Vehicle vehicle ) {
        for (Level level : levels) {
            if (!level.unParkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public static void setInstance ( ParkingLot instance ) {
        ParkingLot.instance = instance;
    }

    public List<Level> getLevels () {
        return levels;
    }


}
