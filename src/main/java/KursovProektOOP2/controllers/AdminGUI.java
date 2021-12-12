package KursovProektOOP2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


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
    @FXML
    Label usernameLabel;
    @FXML
    Label firstNameLabel;
    @FXML
    Label lastNameLabel;
    @FXML
    ImageView exclamationMark;
    Timer timer = new Timer();
    private static final Logger log = Logger.getLogger(Main.class);

    @FXML
    public void initialize() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Panes.loadNotifications();
                Panes.checkForNotifs(exclamationMark);
            }
        }, 0, 60000); // run check every minute
        Platform.runLater(()->Panes.setNameLabels(usernameLabel,firstNameLabel,lastNameLabel));
    }

    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminSpravki.fxml")); //LOAD VIEW
        Panes.setAndClearAnchorPane(ap, ContentAnchorPane);

    }

    @FXML
    public void NotificationsOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/NotificationViews/NotificationViewer.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
    }

    @FXML
    public void warehousesOnAction(){

    }

    public void AccountsOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminAccountViewer.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
    }

    public void SettingOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/Settings.fxml")); //LOAD VIEW
        Panes.setAndClearAnchorPane(ap, ContentAnchorPane);

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
