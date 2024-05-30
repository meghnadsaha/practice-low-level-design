package com.lld.vendingmachine.v1;

public class Product {

    String name;

    double price;
    int quantity;

    public Product ( String name , double price , int quantity ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public void setPrice ( double price ) {
        this.price = price;
    }

    public double getPrice () {
        return price;
    }


    public int getQuantity () {
        return quantity;
    }

    public void setQuantity ( int quantity ) {
        this.quantity = quantity;
    }



    /**
     * Custom methods
     */

    void reduceQuantity ( int amount ) {
        if (quantity >= amount) {
            quantity -= amount;
        }
    }

    void increaseQuantity ( int amount ) {
        quantity += amount;
    }

    boolean isAvailable () {
        return quantity > 0;
    }


}
