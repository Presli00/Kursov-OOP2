package KursovProektOOP2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WarehouseInfo {

    @FXML
    AnchorPane anchorPane;
    @FXML
    VBox titledPaneVbox;
    @FXML
    VBox warehouseInfoVbox;
    @FXML
    HBox ownerNameHbox;
    @FXML
    Text warehouseNameText;
    @FXML
    Text ownerNameText;
    @FXML
    Text cityText;
    @FXML
    Text streetText;
    @FXML
    TitledPane warehouseInfoPane;
    @FXML
    TitledPane roomGridPane;
    @FXML
    VBox roomVbox;

    @FXML
    private void initialize(){
        titledPaneVbox.widthProperty().addListener(event -> {
            Platform.runLater(()-> anchorPane.setPrefWidth(titledPaneVbox.getWidth()));
        });
        titledPaneVbox.heightProperty().addListener(event -> {
            Platform.runLater(()-> anchorPane.setPrefHeight(titledPaneVbox.getHeight()));
        });

    }

    @FXML
    private void viewRooms(){
        if(roomGridPane.isExpanded()){
            warehouseInfoPane.setExpanded(true);
            roomGridPane.setExpanded(false);
            return;
        }
        warehouseInfoPane.setExpanded(true);
        roomGridPane.setExpanded(true);
    }

}
