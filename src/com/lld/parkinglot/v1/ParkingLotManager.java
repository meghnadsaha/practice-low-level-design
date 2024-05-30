package com.lld.parkinglot.v1;


import java.util.*;

class ParkingLotManager {
    private List<ParkingTicket> activeTickets;

    public ParkingLotManager() {
        activeTickets = new ArrayList<>();
    }

    public ParkingTicket getTicketById(int id) {
        for (ParkingTicket ticket : activeTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void addTicket(ParkingTicket ticket) {
        activeTickets.add(ticket);
    }

    public void removeTicket(ParkingTicket ticket) {
        activeTickets.remove(ticket);
    }


    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("Sample Parking Lot", 10);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to park a vehicle, 2 to vacate a spot, or 3 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("Enter vehicle license plate:");
                String licensePlate = scanner.nextLine();
                System.out.println("Enter vehicle type (CAR, TRUCK, MOTORCYCLE):");
                VehicleType type = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                Vehicle vehicle = new Vehicle(licensePlate, type);
                ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
                if (ticket != null) {
                    System.out.println("Vehicle parked successfully. Ticket ID: " + ticket.getId());
                } else {
                    System.out.println("No available spots.");
                }
            } else if (choice == 2) {
                System.out.println("Enter ticket ID:");
                int ticketId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                // Assuming the ticket ID is valid
                ParkingTicket ticket = parkingLotManager.getTicketById(ticketId);
                if (ticket != null) {
                    parkingLot.vacateSpot(ticket);
                    System.out.println("Spot vacated successfully for Ticket ID: " + ticketId);
                } else {
                    System.out.println("Invalid ticket ID.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }


}
