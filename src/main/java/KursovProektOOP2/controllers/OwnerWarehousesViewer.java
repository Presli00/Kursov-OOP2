package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.WarehouseRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OwnerWarehousesViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();
    public final WarehouseRepository repository = WarehouseRepository.getInstance();

    @FXML
    public void initialize() throws IOException {
        List list = repository.getAll();
        for (int i = 0; i < list.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/WarehouseInfo.fxml"));
            AnchorPane warehouse = loader.load();
            WarehouseInfo controller = loader.getController();
            controller.idLabel.setText(String.valueOf(((Warehouse) list.get(i)).getWarehouseId()));
            controller.cityLabel.setText(String.valueOf(((Warehouse) list.get(i)).getCityId()));
            controller.streetLabel.setText(String.valueOf(((Warehouse) list.get(i)).getStreet()));
            controller.numberOfStorageRoomsLabel.setText(String.valueOf(((Warehouse) list.get(i)).getNumberOfStorageRooms()));
            controller.maintenanceLabel.setText(String.valueOf(((Warehouse) list.get(i)).getMaintenanceId()));
            controller.agentLabel.setText(String.valueOf(((Warehouse) list.get(i)).getAgentSId()));
            Vbox.getChildren().add(warehouse);
        }
        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }

}
