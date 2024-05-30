package com.lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class VendingMachine {
    Map<String, Product> products;
    private Map<Coin, Integer> coins;
    private Map<Note, Integer> notes;
    double totalMoney;
    private final ReentrantLock lock = new ReentrantLock();

    public VendingMachine () {
        this.products = new HashMap<>();
        this.coins = new HashMap<>();
        this.notes = new HashMap<>();
        this.totalMoney = 0.0;
        initializeCurrency();
    }

    private void initializeCurrency () {
        // Initialize with zero quantity for each denomination
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
        for (Note note : Note.values()) {
            notes.put(note, 0);
        }
    }

    public void addProduct ( Product product ) {
        lock.lock();
        try {
            products.put(product.getName() , product);
        } finally {
            lock.unlock();
        }
    }

    public void removeProduct ( String name ) {
        lock.lock();
        try {
            products.remove(name);
        } finally {
            lock.unlock();
        }
    }

    public void restockProduct ( String name , int quantity ) {
        lock.lock();
        try {
            if (products.containsKey(name)) {
                products.get(name).increaseQuantity(quantity);
            }
        } finally {
            lock.unlock();
        }
    }

    public double collectMoney () {
        lock.lock();
        try {
            double moneyCollected = totalMoney;
            totalMoney = 0.0;
            return moneyCollected;
        } finally {
            lock.unlock();
        }
    }

    public String dispenseProduct ( String name , double payment ) {
        lock.lock();
        try {
            if (!products.containsKey(name)) {
                return "Product not available";
            }
            Product product = products.get(name);
            if (!product.isAvailable()) {
                return "Out of stock";
            }
            if (payment < product.getPrice()) {
                return "Insufficient funds";
            }
            product.reduceQuantity(1);
            totalMoney += product.getPrice();
            double change = payment - product.getPrice();
            Map<Integer, Integer> changeMap = returnChange(change);
            return "Product dispensed. " + formatChangeMessage(changeMap);
//            return "Product dispensed. Change: " + returnChange(change).toString();
        } finally {
            lock.unlock();
        }
    }

    private String formatChangeMessage ( Map<Integer, Integer> changeMap ) {
        if (changeMap.isEmpty()) {
            return "No change returned.";
        }
        StringBuilder sb = new StringBuilder("Change returned: ");
        for (Map.Entry<Integer, Integer> entry : changeMap.entrySet()) {
            sb.append(entry.getValue()).append(" x ").append(entry.getKey()).append(" ");
        }
        return sb.toString().trim();
    }

//    public Map<Integer, Integer> returnChange(double amount) {
//        Map<Integer, Integer> change = new HashMap<>();
//        // Logic to return change in coins and notes
//        // Example: Returning all amount as a single note for simplicity
//        if (amount > 0) {
//            change.put((int) amount, 1);
//        }
//        return change;
//    }


    public Map<Integer, Integer> returnChange ( double amount ) {
        Map<Integer, Integer> change = new HashMap<>();
        int remaining = (int) amount;

        int[] denominations = {500 , 100 , 25 , 10 , 5 , 1};
        for (int denomination : denominations) {
            if (remaining >= denomination) {
                int count = remaining / denomination;
                remaining %= denomination;
                change.put(denomination , count);
            }
        }

        return change;
    }


}
