package com.lld.parkinglot.v3;

import com.lld.parkinglot.v2.Vehicle;
import com.lld.parkinglot.v2.VehicleType;

public class ParkingSpot {


    private VehicleType type;
    private Vehicle vehicle;


    public ParkingSpot ( VehicleType type ) {
        this.type = type;
    }


    public boolean isOccupied () {
        return vehicle != null;
    }

    public VehicleType getType () {
        return type;
    }

    public void setType ( VehicleType type ) {
        this.type = type;
    }

    public Vehicle getVehicle () {
        return vehicle;
    }

    public void setVehicle ( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }

    public void park ( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }

    public void unPark ( Vehicle vehicle ) {
        this.vehicle = null;
    }
}
