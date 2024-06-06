package com.lld.easy.parkinglot;

class ParkingSpot {
    private boolean available;
    private Vehicle vehicle;
    private final VehicleType type;

    public ParkingSpot ( VehicleType type ) {
        this.type = type;
        this.available = true;
    }

    public synchronized boolean isAvailable () {
        return available;
    }

    public synchronized void parkVehicle ( Vehicle vehicle ) {
        this.vehicle = vehicle;
        this.available = false;
        System.out.println(vehicle.getType() + " parked in spot");
    }

    public synchronized void removeVehicle () {
        this.vehicle = null;
        this.available = true;
        System.out.println("Spot is now available");
    }

    public VehicleType getType () {
        return type;
    }
}
