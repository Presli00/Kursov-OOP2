package KursovProektOOP2.controllers;

import KursovProektOOP2.controllers.Admin.AccountInfo;
import KursovProektOOP2.controllers.Agent.AgentFormular;
import KursovProektOOP2.controllers.Owner.AgentRating;
import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.RatingRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.util.Panes;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public final AgentRepository agentRepository = AgentRepository.getInstance();

    @FXML
    private void initialize() throws IOException {
        // warehouse viewer for owner
        if(UserSession.getRoleID().getRoleName().equals("Admin")){ // warehouse viewer for admin with button for adding new warehouses
            ToolBar toolBar = new ToolBar();
            toolBar.setPrefHeight(45);
            toolBar.setPrefWidth(200);
            toolBar.setStyle("-fx-background-color: #4E97D1; -fx-background-radius: 20 ");
            Button addWarehouse = new Button("Добави Склад ");
            addWarehouse.setOnAction(e->{
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/SharedViews/WarehouseAdderForm.fxml")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                assert root != null;
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Warehouse Form");
                stage.setResizable(false);
                stage.show();
            });
            addWarehouse.setStyle("-fx-background-radius: 70; -fx-font-family: Tahoma; -fx-font-size: 16px;");
            toolBar.getItems().add(addWarehouse);
            Vbox.getChildren().add(toolBar);
            warehouseInfo(true, false);
        }else warehouseInfo(false, UserSession.getRoleID().getRoleName().equals("Agent"));



        ScrollPane.widthProperty().addListener(event -> AnchorPane.setPrefWidth(ScrollPane.getWidth()));
        ScrollPane.heightProperty().addListener(event -> AnchorPane.setPrefHeight(ScrollPane.getHeight()));
    }

    public void warehouseInfo(boolean isAdmin, boolean isAgent) throws IOException {
        if(isAdmin){ //show all warehouses if user is an admin
            List<Warehouse> list = warehouseRepository.getAll();
            for (int i = 0; i < list.size(); i++){ // load warehouses
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/WarehouseInfo.fxml"));
                AnchorPane warehouse = loader.load();
                WarehouseInfo controller = loader.getController();

                controller.warehouseNameText.setText(list.get(i).getWarehouseName());
                controller.ownerNameText.setText(list.get(i).getOwnerId().getUserId().getUsername());
                controller.cityText.setText(list.get(i).getCityId().getCity());
                controller.streetText.setText(list.get(i).getStreet());
                controller.agentsVbox.getChildren().remove(controller.addAgentButton);
                HBox row = new HBox();
                row.setSpacing(10);
                List<StorageRoom> roomList = new ArrayList<>(list.get(i).getRooms());
                for(int j = 0; j < roomList.size(); j++){

                    if(j % 5 == 0){
                        if(list.get(i).getRooms().size() % 5 == 0 && j == list.get(i).getRooms().size()-1){ // if room size is 5 row will be added twice once here and once after the loop
                            break;
                        }
                        controller.roomVbox.getChildren().add(row);
                        row = new HBox();
                        row.setSpacing(10);
                    }
                    dupli(roomList, row, j);
                }

                controller.roomVbox.getChildren().add(row); // ADD ROW TO VBOX

                Vbox.getChildren().add(warehouse);
            }
        }else if(isAgent) { //unfortunate code duplication
            List<Warehouse> list = new ArrayList<>(((Agent) agentRepository.getById(UserSession.getAgent().getIdAgent()).get()).getWarehouses());
            for (int i = 0; i < list.size(); i++) { // load warehouses
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/WarehouseInfo.fxml"));
                AnchorPane warehouse = loader.load();
                WarehouseInfo controller = loader.getController();

                controller.warehouseNameText.setText(list.get(i).getWarehouseName());
                controller.ownerNameText.setText(list.get(i).getOwnerId().getUserId().getUsername());
                controller.cityText.setText(list.get(i).getCityId().getCity());
                controller.streetText.setText(list.get(i).getStreet());
                controller.agentsVbox.getChildren().remove(controller.addAgentButton);
                HBox row = new HBox();
                row.setSpacing(10);
                List<StorageRoom> roomList = new ArrayList<>(list.get(i).getRooms());
                for (int j = 0; j < roomList.size(); j++) {

                    if (j % 5 == 0) {
                        if (list.get(i).getRooms().size() % 5 == 0 && j == list.get(i).getRooms().size() - 1) { // if room size is 5 row will be added twice once here and once after the loop
                            break;
                        }
                        controller.roomVbox.getChildren().add(row);
                        row = new HBox();
                        row.setSpacing(10);
                    }
                    dupli(roomList, row, j);
                }

                controller.roomVbox.getChildren().add(row); // ADD ROW TO VBOX

                Vbox.getChildren().add(warehouse);
            }
        }else{ // if user is a warehouse owner, show only his warehouses
            Owner owner = ((Owner) ownerRepository.getById(UserSession.getOwner().getIdOwner()).get()); // get owner from db
            List<Warehouse> list = new ArrayList<>(owner.getWarehouses()); // get his warehouses and cast to list
            for (int i = 0; i < list.size(); i++){ // code duplication :/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/WarehouseInfo.fxml"));
                AnchorPane warehouse = loader.load();
                WarehouseInfo controller = loader.getController();
                controller.warehouse = list.get(i);
                controller.warehouseInfoVbox.getChildren().remove(controller.ownerNameHbox); // remove owner username from vbox

                controller.warehouseNameText.setText(list.get(i).getWarehouseName());
                controller.cityText.setText(list.get(i).getCityId().getCity());
                controller.streetText.setText(list.get(i).getStreet());
                HBox row = new HBox();
                row.setSpacing(10);
                List<StorageRoom> roomList = new ArrayList<>(list.get(i).getRooms());
                for(int j = 0; j < roomList.size(); j++){

                    if(j % 5 == 0){
                        if(list.get(i).getRooms().size() % 5 == 0 && j == list.get(i).getRooms().size()-1){ // if room size is 5 row will be added twice once here and once after the loop
                            break;
                        }
                        controller.roomVbox.getChildren().add(row);
                        row = new HBox();
                        row.setSpacing(10);
                    }
                    dupli(roomList, row, j);
                }
                // if the warehouse can hold more rooms and the user is an owner add +Button that adds new rooms
                if(list.get(i).getRooms().size() < list.get(i).getNumberOfStorageRooms()){
                    Button addNewRoom = new Button("+");
                    addNewRoom.setPrefHeight(50);
                    addNewRoom.setPrefWidth(50);
                    addNewRoom.setStyle("-fx-font-family: Tahoma; -fx-font-size: 15px;");

                    int finalI = i;
                    addNewRoom.setOnAction(e->{
                        Parent root = null;
                        try {
                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Views/SharedViews/StorageRoomForm.fxml"));
                            root = loader1.load();
                            StorageRoomForm scontroller = loader1.getController();
                            scontroller.warehouseID = list.get(finalI).getWarehouseId();
                            scontroller.inst = this;
                            scontroller.winst = controller;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        assert root != null;
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Storage Room Form");
                        stage.setResizable(false);
                        stage.show();
                    });
                    row.getChildren().add(addNewRoom);
                }
                controller.roomVbox.getChildren().add(row); // ADD ROW TO VBOX

                HBox agentRow = new HBox();
                agentRow.setSpacing(10);
                List<Agent> agentList = new ArrayList<>(list.get(i).getAgentsId());
                for(int j = 0; j < agentList.size(); j++){
                    if(j % 3 == 0){
                        if(agentList.size() % 3 == 0 && j == list.get(i).getAgentsId().size()-1){
                            break;
                        }
                        controller.agentsVbox.getChildren().add(agentRow);
                        agentRow = new HBox();
                        agentRow.setSpacing(10);
                    }
                    Button agent = new Button(String.format("%s", agentList.get(j).getIdFromUser().getUsername()));
                    agent.setPrefHeight(50);
                    agent.setPrefWidth(100);
                    Tooltip tooltip = new Tooltip();
                    List<Rating> agentRating = new ArrayList<>(agentList.get(j).getReceivedRatings());
                    tooltip.setText(String.format( "Rating: %1$s\n", agentRating.size() == 0 ? "No rating" : Panes.getRating(agentRating))
                    );
                    agent.setTooltip(tooltip);
                    agent.setStyle("-fx-font-family: Tahoma; -fx-font-size: 15px;");
                    int finalJ = j;
                    agent.setOnMouseClicked(e->{
                        if(e.getClickCount() == 2){
                            Parent root = null;
                            try {
                                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Views/OwnerViews/AgentRating.fxml"));
                                root = loader1.load();
                                AgentRating agentRatingController = loader1.getController();
                                agentRatingController.rateText.setText("Rate " + agent.getText());
                                agentRatingController.agentID = agentList.get(finalJ).getIdAgent();
                                agentRatingController.ownerID = owner.getIdOwner();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            assert root != null;
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Rate Agent");
                            stage.setResizable(false);
                            stage.show();
                        }
                    });

                    agentRow.getChildren().add(agent);
                }
                controller.agentsVbox.getChildren().add(agentRow); // ADD AGENTROW TO VBOX
                Vbox.getChildren().add(warehouse);
            }
        }

    }

    public void reloadWarehouseRooms(WarehouseInfo controller){
        controller.roomVbox.getChildren().clear();
        List<StorageRoom> warehouseRooms = new ArrayList<>(((Warehouse) warehouseRepository.getById(controller.warehouse.getWarehouseId()).get()).getRooms());
        HBox row = new HBox();
        row.setSpacing(10);
        for (int i = 0; i < warehouseRooms.size(); i++){
            if(i % 5 == 0){
                if(warehouseRooms.size() % 5 == 0 && i == warehouseRooms.size()-1){ // if room size is 5 row will be added twice once here and once after the loop
                    break;
                }
                controller.roomVbox.getChildren().add(row);
                row = new HBox();
                row.setSpacing(10);
            }
            dupli(warehouseRooms, row, i);
        }
        // if the warehouse can hold more rooms and the user is an owner add +Button that adds new rooms
        if(warehouseRooms.size() < controller.warehouse.getNumberOfStorageRooms()){
            Button addNewRoom = new Button("+");
            addNewRoom.setPrefHeight(50);
            addNewRoom.setPrefWidth(50);
            addNewRoom.setStyle("-fx-font-family: Tahoma; -fx-font-size: 15px;");
            addNewRoom.setOnAction(e->{
                Parent root = null;
                try {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Views/SharedViews/StorageRoomForm.fxml"));
                    root = loader1.load();
                    StorageRoomForm scontroller = loader1.getController();
                    scontroller.warehouseID = controller.warehouse.getWarehouseId();
                    scontroller.inst = this;
                    scontroller.winst = controller;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Storage Room Form");
                stage.setResizable(false);
                stage.show();
            });
            row.getChildren().add(addNewRoom);
        }
        controller.roomVbox.getChildren().add(row); // ADD ROW TO VBOX
        controller.roomGridPane.setExpanded(true);
    }

    private void dupli(List<StorageRoom> warehouseRooms, HBox row, int i) {
        Button room = new Button(String.format("%d", i + 1)); // room number
        room.setPrefHeight(50);
        room.setPrefWidth(50);
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format( "\nSize: %1$s\n" +
                "%2$s\n"+
                "%3$s\n"+
                "%4$s\n", warehouseRooms.get(i).getSize()+" м3", warehouseRooms.get(i).getClimateId().getClimate(), warehouseRooms.get(i).getProductId().getType(), warehouseRooms.get(i).isRented() ? "Rented" : "Free")
        );
        room.setTooltip(tooltip);
        if(UserSession.getRoleID().getRoleName().equals("Agent")){
            room.setOnMouseClicked(e->{
                if(e.getClickCount() == 2){

                    if(warehouseRooms.get(i).isRented()){
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Room is already Rented");
                        a.show();
                    }else{
                        Parent root = null;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AgentViews/AgentFormular.fxml"));
                            root = loader.load();
                            AgentFormular agentFormularController = loader.getController();
                            agentFormularController.agentID = UserSession.getAgent().getIdAgent();
                            agentFormularController.ownerID = warehouseRooms.get(i).getwarehouse().getOwnerId().getIdOwner();
                            agentFormularController.roomID = warehouseRooms.get(i).getStorageRoomId();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        assert root != null;
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Create Formular");
                        stage.setResizable(false);
                        stage.show();
                    }
                }
            });
        }
        if(warehouseRooms.get(i).getClimateId().getClimate().equals("Dry")){
            room.setStyle("-fx-background-color: #EB931F");
        }

        if(warehouseRooms.get(i).getClimateId().getClimate().equals("Cold")){
            room.setStyle("-fx-background-color: #C5DAFF");
        }

        if(warehouseRooms.get(i).getClimateId().getClimate().equals("Humid")){
            room.setStyle("-fx-background-color: #928E64");
        }

        if(warehouseRooms.get(i).getClimateId().getClimate().equals("Freezing")){
            room.setStyle("-fx-background-color: #0E9BD1");
        }

        row.getChildren().add(room); // ADD TO ROW
    }

}
