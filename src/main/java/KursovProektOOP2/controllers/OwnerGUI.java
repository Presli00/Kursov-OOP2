package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;


public class OwnerGUI {

    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    // Buttons
    @FXML
    Button SpravkiButton;

    // Functions

    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/OwnerSpravki.fxml")); //LOAD VIEW
        ContentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(ContentAnchorPane.getHeight());
        });
    }

    @FXML
    public void WarehouseOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/OwnerWarehouses.fxml"));
        ContentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(ContentAnchorPane.getHeight());
        });
        /*ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/OwnerWarehousesOwned.fxml"));
        ContentAnchorPane.getChildren().add(sp);
        sp.setPrefWidth(ContentAnchorPane.getWidth());
        sp.setPrefHeight(ContentAnchorPane.getHeight()-ap.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            sp.setPrefWidth(ContentAnchorPane.getWidth());
        });
        ContentAnchorPane.heightProperty().addListener(event -> {
            sp.setPrefHeight(ContentAnchorPane.getHeight());
        });*/
    }

}
