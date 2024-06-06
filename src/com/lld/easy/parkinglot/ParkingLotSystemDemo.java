package com.lld.easy.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance(3, 10); // 3 levels with 10 spots each

        Vehicle car1 = new Car("ABC123");
        Vehicle motorcycle1 = new Motorcycle("DEF456");

        ParkingSpot carSpot = parkingLot.parkVehicle(car1);
        ParkingSpot motorcycleSpot = parkingLot.parkVehicle(motorcycle1);

        if (carSpot != null && motorcycleSpot != null) {
            parkingLot.removeVehicle(carSpot);
            parkingLot.removeVehicle(motorcycleSpot);
        }
    }
}
