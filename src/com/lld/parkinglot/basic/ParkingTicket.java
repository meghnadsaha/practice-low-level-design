package com.lld.parkinglot.basic;

import java.util.Date;

class ParkingTicket {
    private int id;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private Date entryTime;

    public ParkingTicket ( int id , Vehicle vehicle , ParkingSpot parkingSpot ) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = new Date();
    }

    public int getId () {
        return id;
    }

    public Vehicle getVehicle () {
        return vehicle;
    }

    public ParkingSpot getParkingSpot () {
        return parkingSpot;
    }

    public Date getEntryTime () {
        return entryTime;
    }
}
