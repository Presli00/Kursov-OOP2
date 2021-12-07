package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

public class WarehouseAgentGUI {
    @FXML
    AnchorPane ContentAnchorPane;
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

    private static final Logger log = Logger.getLogger(Main.class);


    @FXML
    public void initialize() {
        Panes.loadNotifications(exclamationMark, true);
        Panes.setNameLabels(usernameLabel,firstNameLabel,lastNameLabel);
    }

    @FXML
    public void SpravkiOnAction() throws IOException{
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AgentViews/WarehouseAgentSpravki.fxml")); //LOAD VIEW
        Panes.setAndClearAnchorPane(ap, ContentAnchorPane);
    }

    @FXML
    public void SettingOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/Settings.fxml")); //LOAD VIEW
        Panes.setAndClearAnchorPane(ap, ContentAnchorPane);

    }

    @FXML
    public void logOutOnAction() throws IOException {
        closeWindow();
        UserSession.cleanUserSession();
        Panes.openWindow("/Views/LoginViews/LoginMenu.fxml", LoginMenu.class);
    }

    public void closeWindow() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void notificationsOnAction(){
        System.out.println("lol");
    }
}
