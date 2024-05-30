package com.lld.parkinglot.v2;

class ParkingSpot {
    private int id;
    private VehicleType type;
    private boolean isOccupied;

    public ParkingSpot ( int id , VehicleType type ) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId () {
        return id;
    }

    public VehicleType getType () {
        return type;
    }

    public boolean isOccupied () {
        return isOccupied;
    }

    public void occupy () {
        isOccupied = true;
    }

    public void vacate () {
        isOccupied = false;
    }
}
