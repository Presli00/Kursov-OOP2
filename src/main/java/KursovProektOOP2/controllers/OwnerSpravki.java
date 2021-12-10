package KursovProektOOP2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class OwnerSpravki {
    @FXML
    AnchorPane anchorPane;

    @FXML
    public void ownWarehouses() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/WarehousesToBeRented.fxml"));
        Panes.setAndClearScrollPane(sp, (AnchorPane) anchorPane.getParent().lookup("#ContentAnchorPane"));
    }
    @FXML
    public void agents(){
        Platform.runLater(()->{
            ScrollPane sp = null;
            try {
                sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/Agents.fxml"));
                Panes.setAndClearScrollPane(sp, (AnchorPane) anchorPane.getParent().lookup("#ContentAnchorPane"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    public void otherWarehouses() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/OtherOwnersWarehouses.fxml"));
        Panes.setAndClearScrollPane(sp, (AnchorPane) anchorPane.getParent().lookup("#ContentAnchorPane"));
    }
}
