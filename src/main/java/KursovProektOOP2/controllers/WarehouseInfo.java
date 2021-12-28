package KursovProektOOP2.controllers;

import KursovProektOOP2.controllers.Owner.AgentAdder;
import KursovProektOOP2.controllers.Owner.MaintenanceAdder;
import KursovProektOOP2.data.entity.Warehouse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    VBox agentsVbox;
    @FXML
    Button addAgentButton;
    Warehouse warehouse;
    Stage stage = new Stage();

    @FXML
    private void initialize() {
        titledPaneVbox.widthProperty().addListener(event -> {
            Platform.runLater(() -> anchorPane.setPrefWidth(titledPaneVbox.getWidth()));
        });
        titledPaneVbox.heightProperty().addListener(event -> {
            Platform.runLater(() -> anchorPane.setPrefHeight(titledPaneVbox.getHeight()));
        });

    }

    @FXML
    private void viewRooms() {
        if (roomGridPane.isExpanded()) {
            warehouseInfoPane.setExpanded(true);
            roomGridPane.setExpanded(false);
            return;
        }
        warehouseInfoPane.setExpanded(true);
        roomGridPane.setExpanded(true);
    }

    @FXML
    private void assignAgents() {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/AgentAdder.fxml"));
            root = loader.load();
            AgentAdder controller = loader.getController();
            controller.setWarehouseID(warehouse.getWarehouseId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Agent");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void assignMaintenance(Warehouse w) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/MaintenanceAdder.fxml"));
            root = loader.load();
            MaintenanceAdder controller = loader.getController();
            controller.setWarehouseID(w.getWarehouseId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Maintenance");
        stage.setResizable(false);
        stage.show();
    }
}
