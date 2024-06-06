package com.lld.hard.hotelmanagement;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Guest {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    public Guest(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

enum RoomType {
    SINGLE, DOUBLE, DELUXE, SUITE
}

enum RoomStatus {
    AVAILABLE, BOOKED, OCCUPIED
}

enum ReservationStatus {
    CONFIRMED, CANCELLED
}

class Room {
    private int id;
    private RoomType type;
    private double price;
    private RoomStatus status;

    public Room(int id, RoomType type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = RoomStatus.AVAILABLE;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public void book() {
        if (status == RoomStatus.AVAILABLE) {
            status = RoomStatus.BOOKED;
        } else {
            throw new IllegalStateException("Room is not available for booking");
        }
    }

    public void checkIn() {
        if (status == RoomStatus.BOOKED) {
            status = RoomStatus.OCCUPIED;
        } else {
            throw new IllegalStateException("Room is not booked");
        }
    }

    public void checkOut() {
        if (status == RoomStatus.OCCUPIED) {
            status = RoomStatus.AVAILABLE;
        } else {
            throw new IllegalStateException("Room is not occupied");
        }
    }
}

class Reservation {
    private int id;
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private ReservationStatus status;

    public Reservation(int id, Guest guest, Room room, Date checkInDate, Date checkOutDate) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void cancel() {
        if (status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELLED;
            room.setStatus(RoomStatus.AVAILABLE);
        } else {
            throw new IllegalStateException("Reservation is not confirmed");
        }
    }
}

interface Payment {
    void processPayment(double amount);
}

class CashPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}

class CreditCardPayment implements Payment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " using card " + cardNumber);
    }
}

class OnlinePayment implements Payment {
    private String accountNumber;

    public OnlinePayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing online payment of $" + amount + " using account " + accountNumber);
    }
}

class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private Map<Integer, Guest> guests = new HashMap<>();
    private Map<Integer, Room> rooms = new HashMap<>();
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private int nextGuestId = 1;
    private int nextRoomId = 1;
    private int nextReservationId = 1;
    private final ReentrantLock lock = new ReentrantLock();

    private HotelManagementSystem() {
    }

    public static synchronized HotelManagementSystem getInstance() {
        if (instance == null) {
            instance = new HotelManagementSystem();
        }
        return instance;
    }

    public Guest addGuest(String name, String email, String phoneNumber) {
        lock.lock();
        try {
            Guest guest = new Guest(nextGuestId++, name, email, phoneNumber);
            guests.put(guest.getId(), guest);
            return guest;
        } finally {
            lock.unlock();
        }
    }

    public Room addRoom(RoomType type, double price) {
        lock.lock();
        try {
            Room room = new Room(nextRoomId++, type, price);
            rooms.put(room.getId(), room);
            return room;
        } finally {
            lock.unlock();
        }
    }

    public Reservation bookRoom(int guestId, int roomId, Date checkInDate, Date checkOutDate) {
        lock.lock();
        try {
            Guest guest = guests.get(guestId);
            Room room = rooms.get(roomId);
            if (guest == null || room == null) {
                throw new IllegalArgumentException("Invalid guest or room ID");
            }
            room.book();
            Reservation reservation = new Reservation(nextReservationId++, guest, room, checkInDate, checkOutDate);
            reservations.put(reservation.getId(), reservation);
            return reservation;
        } finally {
            lock.unlock();
        }
    }

    public void cancelReservation(int reservationId) {
        lock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            if (reservation != null) {
                reservation.cancel();
            } else {
                throw new IllegalArgumentException("Invalid reservation ID");
            }
        } finally {
            lock.unlock();
        }
    }

    public void checkIn(int reservationId) {
        lock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            if (reservation != null && reservation.getStatus() == ReservationStatus.CONFIRMED) {
                Room room = reservation.getRoom();
                room.checkIn();
            } else {
                throw new IllegalArgumentException("Invalid reservation ID or reservation not confirmed");
            }
        } finally {
            lock.unlock();
        }
    }

    public void checkOut(int reservationId) {
        lock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            if (reservation != null && reservation.getStatus() == ReservationStatus.CONFIRMED) {
                Room room = reservation.getRoom();
                room.checkOut();
            } else {
                throw new IllegalArgumentException("Invalid reservation ID or reservation not confirmed");
            }
        } finally {
            lock.unlock();
        }
    }

    public void processPayment(int reservationId, Payment payment) {
        lock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            if (reservation != null && reservation.getStatus() == ReservationStatus.CONFIRMED) {
                Room room = reservation.getRoom();
                payment.processPayment(room.getPrice());
            } else {
                throw new IllegalArgumentException("Invalid reservation ID or reservation not confirmed");
            }
        } finally {
            lock.unlock();
        }
    }

    public void generateReport() {
        lock.lock();
        try {
            System.out.println("Hotel Report:");
            System.out.println("Total Guests: " + guests.size());
            System.out.println("Total Rooms: " + rooms.size());
            System.out.println("Total Reservations: " + reservations.size());
        } finally {
            lock.unlock();
        }
    }
}

public class HotelManagementSystemDemo {
    public static void main(String[] args) {
        HotelManagementSystem hms = HotelManagementSystem.getInstance();

        Guest guest1 = hms.addGuest("John Doe", "john@example.com", "1234567890");
        Guest guest2 = hms.addGuest("Jane Smith", "jane@example.com", "0987654321");

        Room room1 = hms.addRoom(RoomType.SINGLE, 100.0);
        Room room2 = hms.addRoom(RoomType.DOUBLE, 150.0);

        Date checkInDate = new Date();
        Date checkOutDate = new Date(checkInDate.getTime() + (1000 * 60 * 60 * 24));

        Reservation reservation1 = hms.bookRoom(guest1.getId(), room1.getId(), checkInDate, checkOutDate);
        Reservation reservation2 = hms.bookRoom(guest2.getId(), room2.getId(), checkInDate, checkOutDate);

        hms.checkIn(reservation1.getId());
        hms.processPayment(reservation1.getId(), new CashPayment());
        hms.checkOut(reservation1.getId());

        hms.checkIn(reservation2.getId());
        hms.processPayment(reservation2.getId(), new CreditCardPayment("1234-5678-9876-5432"));
        hms.checkOut(reservation2.getId());

        hms.generateReport();
    }
}
