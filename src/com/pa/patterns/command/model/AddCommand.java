package com.pa.patterns.command.model;

public class AddCommand implements Command{
    private ShoppingCart cart;
    private String name;
    private double cost;

    public AddCommand(ShoppingCart cart, String name, double cost) {
        this.cart = cart;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public void execute() {
        System.out.println("EXECUTE ADD");
        Product p= new Product(name,cost);
        cart.addProduct(p);

    }

    @Override
    public void unExecute() {
        Product p= new Product(name,cost);
        cart.removeProduct(p);
    }
}
