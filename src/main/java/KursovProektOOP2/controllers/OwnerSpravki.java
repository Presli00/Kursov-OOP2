package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class OwnerSpravki {
    @FXML
    AnchorPane ContentAnchorPane;

    @FXML
    public void ownWarehouses() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/WarehousesToBeRented.fxml"));
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
    }
    @FXML
    public void agents() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/Agents.fxml"));
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
        System.out.println("sad");
    }
    @FXML
    public void otherWarehouses() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/OtherOwnersWarehouses.fxml"));
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
    }
}
