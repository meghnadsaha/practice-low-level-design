package com.lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    Map<String, Product> products = new HashMap<>();
    Map<Integer, Integer> coins = new HashMap<>();
    Map<Integer, Integer> notes = new HashMap<>();
    double totalMoney;

    public VendingMachine () {
        this.products = new HashMap<>();
        this.coins = new HashMap<>();
        this.notes = new HashMap<>();
        this.totalMoney = 0.0;
    }

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public void restockProduct(String name, int quantity) {
        if (products.containsKey(name)) {
            products.get(name).increaseQuantity(quantity);
        }
    }

    public double collectMoney() {
        double moneyCollected = totalMoney;
        totalMoney = 0.0;
        return moneyCollected;
    }

    public String dispenseProduct(String name, double payment) {
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
        return "Product dispensed. Change: " + returnChange(change).toString();
    }


    public Map<Integer, Integer> returnChange(double amount) {
        Map<Integer, Integer> change = new HashMap<>();
        // Logic to return change in coins and notes
        // Example: Returning all amount as a single note for simplicity
        if (amount > 0) {
            change.put((int) amount, 1);
        }
        return change;
    }





}
