package com.pa.patterns.command.view;

import com.pa.patterns.command.model.ShoppingCartController;


public class MainConsola {
    public static void main(String args[]){
        ShoppingCartController cartController= new ShoppingCartController();
        cartController.addProduct("bananas",3.0);
        cartController.addProduct("leite",1.0);
        cartController.addProduct("farinha",2.0);
        System.out.println(cartController.showAll());
        cartController.undo();
        System.out.println(cartController.showAll());
        cartController.addProduct("pao",2.0);
        cartController.removeProduct("leite");
        System.out.println(cartController.showAll());
        cartController.undo();
        System.out.println(cartController.showAll());
        cartController.reset();
        System.out.println(cartController.showAll());
        cartController.undo();
        System.out.println(cartController.showAll());

    }
}
