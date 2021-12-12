package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminWarehouseViewer {

    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();

    @FXML
    public void initialize(){

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }

    @FXML
    private void addWarehouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AdminViews/WarehouseAdderForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.setResizable(false);
        stage.show();
    }
}
