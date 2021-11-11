package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class AdminAccountViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;

    @FXML
    public void initialize(){

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });

    }
}
