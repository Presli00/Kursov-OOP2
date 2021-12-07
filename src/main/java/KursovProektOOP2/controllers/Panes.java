package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Usernotifications;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    static void clearAnchorPane(AnchorPane ap, AnchorPane contentAnchorPane) {
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
            stage.setResizable(false);
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }

    static void loadNotifications(ImageView imgView, int roleID){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String NOTIFICATION_QUERY = "SELECT u FROM Usernotifications u WHERE idFromUser.userId = :userID AND idFromUser.roleId.id = :roleID";
        try{
            List<Usernotifications> notifications = session.createQuery(NOTIFICATION_QUERY).setParameter("userID", UserSession.getUserID()).setParameter("roleID",roleID).getResultList();
            for (int i = 0; i < notifications.size();i++){
                if(!notifications.get(i).isRead()){
                    imgView.setVisible(true);
                }
            }
            UserSession.setNotifications(notifications); // Setting it here since we can't execute another query in the login session
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

}
