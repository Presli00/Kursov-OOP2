package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.WarehouseRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class OwnerWarehousesViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    private static final Logger log = Logger.getLogger(Main.class);
    public final WarehouseRepository repository = WarehouseRepository.getInstance();
    List warehouses;
    int currentUser = UserSession.getUserID();

    @FXML
    public void initialize() throws IOException {
        getAllWarehouses();

        for (int i = 0; i < warehouses.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/WarehouseInfo.fxml"));
            AnchorPane warehouse = loader.load();
            WarehouseInfo controller = loader.getController();
            controller.idLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getWarehouseId()));
            controller.cityLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getCityId().getCity()));
            controller.streetLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getStreet()));
            controller.numberOfStorageRoomsLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getNumberOfStorageRooms()));
            controller.maintenanceLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getMaintenanceId().getName()));
            controller.agentLabel.setText(String.valueOf(((Warehouse) warehouses.get(i)).getAgentsId()));
            Vbox.getChildren().add(warehouse);
            controller.checkButton.setOnAction(e->{
                ScrollPane sp = null;
                try {
                    sp = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/RoomsViewer.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Panes.setAndClearScrollPane(sp, AnchorPane);
                    System.out.println("sad");
            });
        }
        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }

    public void getAllWarehouses() {
warehouses=repository.getAll();
    }
}
