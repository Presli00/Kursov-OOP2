package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Usernotifications;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Panes {

    private static final Logger log = Logger.getLogger(Main.class);

    static void setAndClearAnchorPane(AnchorPane ap, AnchorPane contentAnchorPane) {
        if(!contentAnchorPane.getChildren().isEmpty()) {
            contentAnchorPane.getChildren().clear();
        }
        contentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(contentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(contentAnchorPane.getHeight());
        contentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(contentAnchorPane.getWidth());
        });

        contentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(contentAnchorPane.getHeight());
        });
    }

    static void openWindow(String pathToView, Class c) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = c.getResource(pathToView);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(c.getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            if(c == OwnerGUI.class || c == WarehouseAgentGUI.class || c == AdminGUI.class){
                //Stage is Resizable by default
                stage.setMinHeight(650);
                stage.setMinWidth(1200);
            }else {
                stage.setResizable(false);
            }
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }

    static boolean checkForNotifs(ImageView img){
        for (int i = 0; i < UserSession.getNotifications().size();i++){
            if(!UserSession.getNotifications().get(i).isRead()){
                img.setVisible(true);
                return true;
            }
        }
        return false;
    }

    static void loadNotifications(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String NOTIFICATION_QUERY = "SELECT u FROM Usernotifications u WHERE idFromUser.userId = :userID";
        try{
            List<Usernotifications> notifications = session.createQuery(NOTIFICATION_QUERY).setParameter("userID", UserSession.getUserID()).getResultList();
            UserSession.setNotifications(notifications); // Setting it here since we can't execute another query in the login session (i think lol)
        }catch (Exception ex){
            log.error("Notifications retrieval unsuccessful " + "\n" + ex.getMessage());
        }finally {
            transaction.commit();
        }
    }

    static void setNameLabels(Label usernameLabel, Label firstNameLabel, Label lastNameLabel){
        usernameLabel.setText(UserSession.getUserName());
        firstNameLabel.setText(UserSession.getFirst_name());
        lastNameLabel.setText(UserSession.getLast_name());
    }

    static void setAndClearScrollPane(ScrollPane sp, AnchorPane contentAnchorPane) {
        if (!contentAnchorPane.getChildren().isEmpty()) {
            contentAnchorPane.getChildren().clear();
        }
        contentAnchorPane.getChildren().add(sp);
        sp.setPrefWidth(contentAnchorPane.getWidth()); // SET SIZE OF VIEW
        sp.setPrefHeight(contentAnchorPane.getHeight());
        contentAnchorPane.widthProperty().addListener(event -> {
            sp.setPrefWidth(contentAnchorPane.getWidth());
        });

        contentAnchorPane.heightProperty().addListener(event -> {
            sp.setPrefHeight(contentAnchorPane.getHeight());
        });
    }

}
