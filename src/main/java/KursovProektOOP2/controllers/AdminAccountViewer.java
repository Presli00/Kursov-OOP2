package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminAccountViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();

    public final UserRepository repository = UserRepository.getInstance();


    @FXML
    public void initialize() throws IOException {
        List list = repository.getAll();
        for(int i = 0; i < list.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AccountInfo.fxml"));
            AnchorPane user = loader.load();
            AccountInfo controller = loader.getController();
            // Set data in the controller
            controller.idLabel.setText(String.valueOf(((User)list.get(i)).getUserId()));
            controller.usernameLabel.setText(((User) list.get(i)).getUsername());
            controller.roleLabel.setText(((User) list.get(i)).getRoleId().getRoleName());
            controller.firstnameLabel.setText(((User) list.get(i)).getFirstName());
            controller.lastnameLabel.setText(((User) list.get(i)).getLastName());
            controller.createdatLabel.setText(((User) list.get(i)).getCreatedDate().toString());
            if(((User) list.get(i)).getUpdatedDate() == null){
                controller.lastupdatedatLabel.setText("Never");
            }else{
                controller.lastupdatedatLabel.setText(((User) list.get(i)).getUpdatedDate().toString());
            }
            Vbox.getChildren().add(user);
        }

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });

    }

    public void addUser() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/RegistrationForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.setResizable(false);
        stage.show();
    }
}
