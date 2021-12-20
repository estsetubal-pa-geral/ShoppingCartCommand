package com.pa.patterns.command.view;

import com.pa.patterns.command.model.Product;
import com.pa.patterns.command.model.ShoppingCartController;
import com.pa.patterns.command.model.ShoppingCartException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static sun.management.Agent.error;

public class ShoppingCartPanel {

    private ShoppingCartController shoppingCartController;
    private ListView<Product> listViewCartContents;

    private Button buttonUndo;
    private GridPane gridPaneMain;
    private Button buttonAddProduct;
    private Button buttonReset;
    private Button buttonRemoveProduct;
    private TextField textFieldProductName;
    private TextField textFieldPrice;

    public GridPane getGridPaneMain() {
        return gridPaneMain;
    }

    public ShoppingCartPanel() {
        shoppingCartController = new ShoppingCartController();
        gridPaneMain = new GridPane();

        // Add product
        GridPane gridPaneAddProduct = new GridPane();
        Label labelAddProduct = new Label("Add products to cart");
        labelAddProduct.setStyle("-fx-font-weight: bold");
        gridPaneAddProduct.add(labelAddProduct, 0, 0);
        gridPaneAddProduct.add(new Label("Name"), 0, 1);
        textFieldProductName = new TextField();
        gridPaneAddProduct.add(textFieldProductName, 1, 1);
        gridPaneAddProduct.add(new Label("Price"), 0, 2);
        textFieldPrice = new TextField();
        gridPaneAddProduct.add(textFieldPrice, 1, 2);
        HBox hBoxAddProductButtons = new HBox();
        buttonAddProduct = new Button("Add");
        buttonRemoveProduct = new Button("Remove");
        buttonReset = new Button("ResetAll");
        hBoxAddProductButtons.getChildren().addAll(buttonAddProduct,buttonRemoveProduct,buttonReset);
        hBoxAddProductButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxAddProductButtons.setStyle("-fx-padding: 2px 0 0 0");
        gridPaneAddProduct.add(hBoxAddProductButtons, 1, 3);

        gridPaneMain.add(gridPaneAddProduct, 0, 0);

        // Shopping cart
        GridPane gridPaneCartContents = new GridPane();
        Label labelCartContents = new Label("Cart contents");
        labelCartContents.setStyle("-fx-font-weight: bold");
        listViewCartContents = new ListView<>();
        gridPaneCartContents.add(labelCartContents, 0, 0);
        gridPaneCartContents.add(listViewCartContents, 0, 1);


        buttonUndo = new Button("Undo");
        HBox hBoxUndo = new HBox();
        hBoxUndo.getChildren().add(buttonUndo);
        hBoxUndo.setAlignment(Pos.CENTER_RIGHT);
        hBoxUndo.setStyle("-fx-padding: 2px 0 0 0");
        gridPaneCartContents.add(hBoxUndo, 0, 2);
        GridPane.setHgrow(listViewCartContents, Priority.ALWAYS);

        setTriggers();

        gridPaneMain.add(gridPaneCartContents, 0, 1);

        updateProductCartList();

        gridPaneMain.setStyle("-fx-padding: 5px");
    }

    private void setTriggers() {
        buttonAddProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (textFieldProductName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
                    error("Missing product information.");
                } else {
                    try {
                        String name = textFieldProductName.getText();
                        double price = Double.parseDouble(textFieldPrice.getText());
                        shoppingCartController.addProduct(name, price);
                        updateProductCartList();
                        cleanInput();
                    } catch (NumberFormatException nfe) {
                        showError("Invalid price value.");
                    }
                }
            }
        });

        buttonRemoveProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (listViewCartContents.getSelectionModel().getSelectedItems().isEmpty()) {
                    error("Missing product information.");
                } else {
                    try {
                        Product p=listViewCartContents.getSelectionModel().getSelectedItems().get(0);
                        shoppingCartController.removeProduct(p.getName());
                        updateProductCartList();
                    } catch (NumberFormatException nfe) {
                        showError("Invalid price value.");
                    }
                }
            }
        });

        buttonReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shoppingCartController.reset();
                updateProductCartList();
            }});



        buttonUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    shoppingCartController.undo();
                } catch (ShoppingCartException e) {
                    showError("No previous state recorded.");
                }
                updateProductCartList();
            }
        });

    }

    private void cleanInput() {
        this.textFieldPrice.setText("");
        this.textFieldProductName.setText("");
    }

    private void updateProductCartList() {
        listViewCartContents.getItems().clear();
        for (Product product : shoppingCartController.getProducts()) {
            listViewCartContents.getItems().add(product);
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Shopping Cart Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
