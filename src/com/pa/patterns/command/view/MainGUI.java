package com.pa.patterns.command.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(new ShoppingCartPanel().getGridPaneMain());
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
