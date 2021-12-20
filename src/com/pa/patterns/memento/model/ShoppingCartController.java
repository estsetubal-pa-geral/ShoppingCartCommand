package com.pa.patterns.memento.model;

import java.util.Collection;
import java.util.Date;
import java.util.Stack;

public class ShoppingCartController {
    private ShoppingCart cart;
    private Stack<Command> commands;

    public ShoppingCartController() {
        cart = new ShoppingCart();
        commands = new Stack();
    }

    public void addProduct(String name, double cost) {
        Command c = new AddCommand(cart,name,cost);
        c.execute();
        commands.push(c);
    }

    public void reset() {
        Command c = new ResetCommand(cart);
        c.execute();
        commands.push(c);
    }

    public void removeProduct(String name) {
        Command c = new RemoveCommand(cart,name);
        c.execute();
        commands.push(c);
    }


    public void undo() {
        if (commands.empty())
            throw new ShoppingCartException("No Undo");
        Command cmd = commands.pop();
        cmd.unExecute();
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
