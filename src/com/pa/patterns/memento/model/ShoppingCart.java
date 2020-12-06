/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ShoppingCart implements Originator {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
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

    @Override
    public Memento createMemento() {
        Memento memento = new MyMemento(products);
        return memento;
    }

    @Override
    public void setMemento(Memento savedState) {
        if (savedState instanceof MyMemento) {
            products.clear();
            products.addAll(((MyMemento) savedState).state);
        }
    }

    private class MyMemento implements Memento {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

        private List state;
        private String description;

        public MyMemento(List<Product> stateToSave) {
            this.description = "ProductList saved state from " + simpleDateFormat.format(new Date());
            this.state = new ArrayList<>(stateToSave); //copy of list
        }

        @Override
        public String getDescrtiption() {
            return description;
        }
    }
}
