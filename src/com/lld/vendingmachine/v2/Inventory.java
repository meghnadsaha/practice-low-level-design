package com.lld.vendingmachine.v2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Inventory {
    private ConcurrentMap<Product, Integer> products = new ConcurrentHashMap<>();

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public boolean isProductAvailable(Product product) {
        return products.containsKey(product) && products.get(product) > 0;
    }

    public void decreaseQuantity(Product product) {
        int quantity = products.get(product);
        if (quantity > 0) {
            products.put(product, quantity - 1);
        }
    }
}
