/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento.model;

import java.util.Collection;

public class ShoppingCartController {
    private ShoppingCart cart;
    private Caretaker caretaker;

    public ShoppingCartController() {
        cart = new ShoppingCart();
        caretaker = new Caretaker(cart);
    }

    public void addProduct(String name, double cost) {
        caretaker.saveState();
        Product p = new Product(name, cost);
        cart.addProduct(p);
    }

    public void reset() {
        caretaker.saveState();
        cart.reset();
    }

    public void removeProduct(String name) {
        for (Product p : cart.getProducts())
            if (p.getName().equals(name)) {
                caretaker.saveState();
                cart.removeProduct(p);
                return;
            }
        return;
    }

    public void undo() {
        caretaker.restoreState();
    }

    public Collection<Product> getProducts() {
        return cart.getProducts();
    }

    public String showAll() {
        String str = cart.toString() + "\nTotal price:" + cart.getTotal();
        return str;
    }
}
