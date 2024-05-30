To address these additional requirements, we'll need to enhance the `ParkingLot`, `ParkingSpot`, and `ParkingTicket` classes and introduce new classes like `Level` for better organization. Here's how the UML relationships would look:

### UML Class Diagram with Relationships

1. **Classes and Attributes**:
    - `ParkingLot`: Manages multiple levels.
    - `Level`: Contains multiple parking spots.
    - `ParkingSpot`: Represents an individual parking spot with a specific type.
    - `Vehicle`: Represents a vehicle with a type and license plate.
    - `ParkingTicket`: Issued when a vehicle is parked, tracks the parking spot, vehicle, and entry time.

2. **Associations**:
    - `ParkingLot` has a 1-to-many association with `Level`.
    - `Level` has a 1-to-many association with `ParkingSpot`.
    - `ParkingSpot` can accommodate a specific `VehicleType`.

3. **Dependencies**:
    - `ParkingLot` and `Level` classes depend on `Vehicle` and `ParkingTicket` classes.

### Enhanced Class Diagram

```plaintext
+-------------------+
|    ParkingLot     |
+-------------------+
| - name: String    |
| - levels: List<Level> |
+-------------------+
| + getAvailableSpots(): int          |
| + parkVehicle(vehicle: Vehicle): ParkingTicket |
| + vacateSpot(ticket: ParkingTicket): void       |
+-------------------+
           |
           |1
           |
           n
+-------------------+
|      Level        |
+-------------------+
| - id: int         |
| - spots: List<ParkingSpot> |
+-------------------+
| + getAvailableSpots(): int  |
| + findSpotForVehicle(vehicle: Vehicle): ParkingSpot |
+-------------------+
           |
           |1
           |
           n
+---------------------+
|    ParkingSpot      |
+---------------------+
| - id: int           |
| - type: VehicleType |
| - isOccupied: boolean |
+---------------------+
| + occupy(): void    |
| + vacate(): void    |
+---------------------+

+-------------------+
|     Vehicle       |
+-------------------+
| - licensePlate: String |
| - type: VehicleType    |
+-------------------+
| + getLicensePlate(): String |
| + getType(): VehicleType    |
+-------------------+

+-------------------+
|  ParkingTicket    |
+-------------------+
| - id: int         |
| - vehicle: Vehicle |
| - spot: ParkingSpot |
| - entryTime: Date  |
+-------------------+
| + getId(): int    |
| + getVehicle(): Vehicle |
| + getSpot(): ParkingSpot |
| + getEntryTime(): Date |
+-------------------+

enum VehicleType {
    CAR,
    TRUCK,
    MOTORCYCLE
}
```

### Updated Code Implementation

```java
import java.util.*;

enum VehicleType {
    CAR,
    TRUCK,
    MOTORCYCLE
}

class Vehicle {
    private String licensePlate;
    private VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }
}

class ParkingSpot {
    private int id;
    private VehicleType type;
    private boolean isOccupied;

    public ParkingSpot(int id, VehicleType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}

class ParkingTicket {
    private int id;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private Date entryTime;

    public ParkingTicket(int id, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = new Date();
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Date getEntryTime() {
        return entryTime;
    }
}

class Level {
    private int id;
    private List<ParkingSpot> spots;

    public Level(int id, int numSpots) {
        this.id = id;
        this.spots = new ArrayList<>();
        for (int i = 1; i <= numSpots; i++) {
            // Randomly assign spot type for simplicity; in real system, it would be more complex
            VehicleType type = VehicleType.values()[new Random().nextInt(VehicleType.values().length)];
            this.spots.add(new ParkingSpot(i, type));
        }
    }

    public int getId() {
        return id;
    }

    public int getAvailableSpots() {
        return (int) spots.stream().filter(spot -> !spot.isOccupied()).count();
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        return spots.stream().filter(spot -> !spot.isOccupied() && spot.getType() == vehicle.getType()).findFirst().orElse(null);
    }
}

class ParkingLot {
    private String name;
    private List<Level> levels;

    public ParkingLot(String name, int numLevels, int spotsPerLevel) {
        this.name = name;
        this.levels = new ArrayList<>();
        for (int i = 1; i <= numLevels; i++) {
            this.levels.add(new Level(i, spotsPerLevel));
        }
    }

    public int getAvailableSpots() {
        return levels.stream().mapToInt(Level::getAvailableSpots).sum();
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            ParkingSpot spot = level.findSpotForVehicle(vehicle);
            if (spot != null) {
                spot.occupy();
                return new ParkingTicket(1, vehicle, spot);  // In real system, ticket ID should be generated uniquely
            }
        }
        return null; // No available spots
    }

    public void vacateSpot(ParkingTicket ticket) {
        ticket.getParkingSpot().vacate();
    }
}

public class ParkingLotManager {
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
        ParkingLotManager manager = new ParkingLotManager();
        ParkingLot parkingLot = new ParkingLot("Sample Parking Lot", 3, 10);
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
                    manager.addTicket(ticket);
                    System.out.println("Vehicle parked successfully. Ticket ID: " + ticket.getId());
                } else {
                    System.out.println("No available spots.");
                }
            } else if (choice == 2) {
                System.out.println("Enter ticket ID:");
                int ticketId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                ParkingTicket ticket = manager.getTicketById(ticketId);
                if (ticket != null) {
                    parkingLot.vacateSpot(ticket);
                    manager.removeTicket(ticket);
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
```

### Summary of Changes

1. **New `Level` Class**:
    - Represents a level in the parking lot with multiple parking spots.
    - Manages the spots and finds an available spot for a specific vehicle type.

2. **Enhanced `ParkingLot` Class**:
    - Manages multiple levels.
    - Parks vehicles by finding an available spot across all levels.
    - Vacates spots based on parking tickets.

3. **Updated `ParkingLotManager` Class**:
    - Manages active parking tickets.
    - Handles user input to park and vacate vehicles.

4. **New `ParkingSpot` Attribute**:
    - Added `VehicleType` to define the type of vehicle each spot can accommodate.

This setup provides a more realistic and functional parking lot

system, supporting multiple levels, vehicle types, and real-time management of parking spots.