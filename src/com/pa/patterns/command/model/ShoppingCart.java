package com.pa.patterns.command.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }
    public ShoppingCart(ShoppingCart cart) {
        products = new ArrayList<>(cart.getProducts());
    }


    public void addProduct(Product p) {
        products.add(p);
    }

    public void reset() {
        products.clear();
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getCost();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.valueOf(products);
    }


    public void setCart(ShoppingCart oldCart) {
        products.clear();
        this.products.addAll(oldCart.products);
    }
}
