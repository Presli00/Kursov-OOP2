package KursovProektOOP2.controllers;

import KursovProektOOP2.controllers.Admin.AccountInfo;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseViewer {
    @FXML
    ScrollPane ScrollPane;
    @FXML
    AnchorPane AnchorPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();
    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();

    @FXML
    private void initialize() throws IOException {
        if(UserSession.getRoleID().getRoleName().equals("Admin")){
            List<Warehouse> warehouses = warehouseRepository.getAll();
            ToolBar toolBar = new ToolBar();
            toolBar.setPrefHeight(45);
            toolBar.setPrefWidth(200);
            toolBar.setStyle("-fx-background-color: #4E97D1; -fx-background-radius: 20 ");
            Button addWarehouse = new Button("Добави Склад ");
            addWarehouse.setOnAction(e->{
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Views/SharedViews/WarehouseAdderForm.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Warehouse Form");
                stage.setResizable(false);
                stage.show();
            });
            addWarehouse.setStyle("-fx-background-radius: 70; -fx-font-family: Tahoma; -fx-font-size: 16px;");
            toolBar.getItems().add(addWarehouse);
            Vbox.getChildren().add(toolBar);
            warehouseInfo(true, warehouses);
        }else{
            Owner owner = ((Owner) ownerRepository.getById(UserSession.getOwner().getIdOwner()).get()); // get owner from db
            List<Warehouse> ownerWarehouses = new ArrayList<>(owner.getWarehouses()); // get his warehouses and cast to list
            warehouseInfo(false, ownerWarehouses);
        }



        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });
        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }

    private void warehouseInfo(boolean isAdmin, List<?> list) throws IOException {
        if(isAdmin){ //show all warehouses if user is an admin
            for (int i = 0; i < list.size(); i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/WarehouseInfo.fxml"));
                AnchorPane warehouse = loader.load();
                WarehouseInfo controller = loader.getController();
                controller.warehouseNameText.setText(((Warehouse) list.get(i)).getWarehouseName());
                controller.ownerNameText.setText(((Warehouse) list.get(i)).getOwnerId().getUserId().getUsername());
                controller.cityText.setText(((Warehouse) list.get(i)).getCityId().getCity());
                controller.streetText.setText(((Warehouse) list.get(i)).getStreet());
                Vbox.getChildren().add(warehouse);
            }
        }else{ // if user is a warehouse owner, show only his warehouses
            for (int i = 0; i < list.size(); i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/WarehouseInfo.fxml"));
                AnchorPane warehouse = loader.load();
                WarehouseInfo controller = loader.getController();
                controller.warehouseInfoVbox.getChildren().remove(controller.ownerNameHbox); // remove owner username from vbox
                controller.warehouseNameText.setText(((Warehouse) list.get(i)).getWarehouseName());
                controller.cityText.setText(((Warehouse) list.get(i)).getCityId().getCity());
                controller.streetText.setText(((Warehouse) list.get(i)).getStreet());
                Vbox.getChildren().add(warehouse);
            }
        }


    }
}
