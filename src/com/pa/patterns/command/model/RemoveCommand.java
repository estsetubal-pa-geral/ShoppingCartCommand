package com.pa.patterns.command.model;

public class RemoveCommand implements Command{
    private ShoppingCart cart;
    private String name;
    private Product removeProduct;

    public RemoveCommand(ShoppingCart cart, String name) {
        this.cart = cart;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("EXECUTE REMOVE");
        removeProduct = cart.removeProduct(name);
        return;

    }

    @Override
    public void unExecute() {
        cart.addProduct(removeProduct);
    }
}
