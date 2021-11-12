package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AdminAccountViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;

    public final UserRepository repository = UserRepository.getInstance();


    @FXML
    public void initialize() throws IOException {
        System.out.println("s");
        List list = repository.getAll();
        Label label = new Label(((User)list.get(0)).getFirstName());
        Label label2 = new Label("s");
        Vbox.getChildren().addAll(label, label2);

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });

    }
}
