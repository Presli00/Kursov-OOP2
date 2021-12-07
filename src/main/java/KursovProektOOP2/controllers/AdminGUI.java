package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class AdminGUI {
    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    @FXML
    GridPane GridPane;
    // Buttons
    @FXML
    Button SpravkiButton;
    @FXML
    Button logOutButton;
    @FXML
    Button SettingsButton;

    private static final Logger log = Logger.getLogger(Main.class);
    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminSpravki.fxml")); //LOAD VIEW
        Panes.clearAnchorPane(ap, ContentAnchorPane);

    }

    public void AccountsOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminAccountViewer.fxml")); //LOAD VIEW
        if (!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(sp);
        sp.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
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
