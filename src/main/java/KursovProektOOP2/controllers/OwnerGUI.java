package KursovProektOOP2.controllers;

import KursovProektOOP2.util.Panes;
import KursovProektOOP2.util.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class OwnerGUI {

    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    @FXML
    Button logOutButton;
    @FXML
    Text usernameText;
    @FXML
    Button notificationsButton;
    @FXML
    Text firstNameText;
    @FXML
    Text lastNameText;
    @FXML
    ImageView exclamationMark;
    public Timer ownerTimer = new Timer();
    // Functions
    private static final Logger log = Logger.getLogger(Main.class);
    int currentUser = UserSession.getUserID();

    @FXML
    public void initialize() {

        ownerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Panes.loadNotifications();
                Panes.checkForNotifs(exclamationMark);
            }
        }, 0, 60000); // run check every minute
        Platform.runLater(()->Panes.setNameLabels(usernameText,firstNameText,lastNameText));
    }

    @FXML
    public void NotificationsOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/NotificationViews/NotificationViewer.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, ContentAnchorPane);
    }

    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/OwnerSpravki.fxml")); //LOAD VIEW
        Panes.setAndClearAnchorPane(ap, ContentAnchorPane);
    }

    @FXML
    public void WarehouseOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/OwnerViews/OwnerWarehousesViewer.fxml"));
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
