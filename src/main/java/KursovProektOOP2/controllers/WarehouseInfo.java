package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class WarehouseInfo {
    @FXML
    Label idLabel;
    @FXML
    Label cityLabel;
    @FXML
    Label streetLabel;
    @FXML
    Label numberOfStorageRoomsLabel;
    @FXML
    Label maintenanceLabel;
    @FXML
    Label agentLabel;

    public void checkButton() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/RoomsViewer.fxml"));
        Scene scene = new Scene(root);

    }
}
