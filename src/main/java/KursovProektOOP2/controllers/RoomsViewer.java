package KursovProektOOP2.controllers;

import KursovProektOOP2.data.repository.StorageRoomRepository;
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

public class RoomsViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();
    public final StorageRoomRepository repository = StorageRoomRepository.getInstance();

    @FXML
    public void initialize() throws IOException {
        List list=repository.getAll();
        for(int i=0;i< list.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/RoomsInfo.fxml"));
            AnchorPane warehouse = loader.load();
            WarehouseInfo controller = loader.getController();
            /*for the data

            Vbox.getChildren().add(warehouse); */
        }
        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }
    public void addRoomsToWarehouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/RoomAdder.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Room Form");
        stage.setResizable(false);
        stage.show();
    }
}
