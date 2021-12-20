package com.pa.patterns.command.model;

public class RemoveCommand implements Command{
    private ShoppingCart cart;
    private String name;
    private double cost;


    public RemoveCommand(ShoppingCart cart, String name) {
        this.cart = cart;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("EXECUTE REMOVE");
        for (Product p : cart.getProducts())
            if (p.getName().equals(name)) {
                cost=p.getCost();
                cart.removeProduct(p);
                return;
            }

        return;

    }

    @Override
    public void unExecute() {
        Product p= new Product(name,cost);
        cart.addProduct(p);
    }
}
