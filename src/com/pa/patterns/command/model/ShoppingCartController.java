package com.pa.patterns.command.model;

import java.util.Collection;
import java.util.Stack;

public class ShoppingCartController {
    private ShoppingCart cart;
    private CommandManager commandmanager;


    public ShoppingCartController() {
        cart = new ShoppingCart();
        commandmanager= new CommandManager();

    }

    public void addProduct(String name, double cost) {
        Command c = new AddCommand(cart,name,cost);
        commandmanager.executeCommand(c);
    }

    public void reset() {
        Command c = new ResetCommand(cart);
        commandmanager.executeCommand(c);
    }

    public void removeProduct(String name) {
        Command c = new RemoveCommand(cart,name);
        commandmanager.executeCommand(c);
    }


    public void undo() {
        commandmanager.undo();
        System.out.println("UNDO");
    }

    public Collection<Product> getProducts() {
        return cart.getProducts();
    }

    public String showAll() {
        String str = cart.toString() + "\nTotal price:" + cart.getTotal();
        return str;
    }
}
