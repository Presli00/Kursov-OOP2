package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import KursovProektOOP2.data.repository.UserNotificationRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;

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
    ImageView exclamationMark;

    private static final Logger log = Logger.getLogger(Main.class);
    public final UserNotificationRepository notifications = UserNotificationRepository.getInstance();


    @FXML
    public void initialize(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String LOGIN_QUERY = "SELECT u FROM UserNotification u WHERE idFromUser = '" + UserSession.getUserID() + "'"; // QUERY
        try {
            User result = (User) session.createQuery(LOGIN_QUERY).getSingleResult();


        } catch (Exception ex) {
            log.error("notifs retrieval unsuccessful" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @FXML
    public void SpravkiOnAction() throws IOException{
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AgentViews/WarehouseAgentSpravki.fxml")); //LOAD VIEW
        if(!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(ContentAnchorPane.getHeight());
        });
    }
    public void SettingOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/Settings.fxml")); //LOAD VIEW
        if (!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(ContentAnchorPane.getHeight());
        });

    }
    public void logOutOnAction() throws IOException {
        closeWindow();
        UserSession.cleanUserSession();
        openWindow("/Views/LoginViews/LoginMenu.fxml");
    }

    public void closeWindow() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void openWindow(String pathToView) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = getClass().getResource(pathToView);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }
}
