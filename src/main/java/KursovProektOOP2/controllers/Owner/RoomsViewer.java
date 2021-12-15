package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.controllers.Main;
import KursovProektOOP2.controllers.Owner.RoomsInfo;
import KursovProektOOP2.data.entity.ProductType;
import KursovProektOOP2.data.entity.StorageRoom;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomsViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();
    private static final Logger log = Logger.getLogger(Main.class);
    public final StorageRoomRepository repository = StorageRoomRepository.getInstance();
    List rooms;

    @FXML
    public void initialize() throws IOException {
        getAllRooms();
        for (int i = 0; i < rooms.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/RoomsInfo.fxml"));
            AnchorPane room = loader.load();
            RoomsInfo controller = loader.getController();
            controller.idLabel.setText(String.valueOf(((StorageRoom) rooms.get(i)).getStorageRoomId()));
            controller.sizeLabel.setText(String.valueOf(((StorageRoom) rooms.get(i)).getSize()));
            controller.climateLabel.setText(String.valueOf(((StorageRoom) rooms.get(i)).getClimateId().getClimate()));
            StringBuilder products=new StringBuilder();
            for(int k = 0; k< ((StorageRoom) rooms.get(i)).getProductId().size(); k++){
                List<ProductType> productsList = new ArrayList<>(((StorageRoom) rooms.get(i)).getProductId());
                products.append(productsList.get(k).getType()+" ");
            }
            controller.productLabel.setText(String.valueOf(products));

            Vbox.getChildren().add(room);
            controller.removeButton.setOnAction(e->{

            });
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

    public void getAllRooms() {
        rooms = repository.getAll();
    }
}
