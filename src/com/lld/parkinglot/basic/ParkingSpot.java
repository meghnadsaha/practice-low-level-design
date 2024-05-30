package com.lld.parkinglot.basic;

class ParkingSpot {
    private int id;
    private boolean isOccupied;

    public ParkingSpot ( int id ) {
        this.id = id;
        this.isOccupied = false;
    }

    public int getId () {
        return id;
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
