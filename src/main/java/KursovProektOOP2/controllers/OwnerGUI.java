package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;


public class OwnerGUI {

    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    @FXML
    Button logOutButton;
    @FXML
    Label usernameLabel;
    @FXML
    Label firstNameLabel;
    @FXML
    Label lastNameLabel;
    // Functions
    private static final Logger log = Logger.getLogger(Main.class);
    int currentUser = UserSession.getUserID();

    @FXML
    public void initialize() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String CHANGE_QUERY = "SELECT u FROM User u WHERE userId='" + currentUser + "'"; //query to change the username
        try {
            User result = (User) session.createQuery(CHANGE_QUERY).getSingleResult();
            usernameLabel.setText(result.getUsername());
            firstNameLabel.setText(result.getFirstName());
            lastNameLabel.setText(result.getLastName());
        } catch (Exception ex) {
            log.error("Info retrieval unsuccessful " + "\n" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/OwnerSpravki.fxml")); //LOAD VIEW
        Panes.clearAnchorPane(ap, ContentAnchorPane);
    }

    @FXML
    public void WarehouseOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/OwnerWarehousesViewer.fxml"));
        if (!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(sp);
        sp.setPrefWidth(ContentAnchorPane.getWidth());
        sp.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            sp.setPrefWidth(ContentAnchorPane.getWidth());
        });
        ContentAnchorPane.heightProperty().addListener(event -> {
            sp.setPrefHeight(ContentAnchorPane.getHeight());
        });
    }

    public void SettingOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/Settings.fxml")); //LOAD VIEW
        Panes.clearAnchorPane(ap, ContentAnchorPane);
    }
    public void logOutOnAction() throws IOException {
        closeWindow();
        UserSession.cleanUserSession();
        Panes.openWindow("/Views/LoginViews/LoginMenu.fxml", LoginMenu.class);
    }

    public void closeWindow() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

}
