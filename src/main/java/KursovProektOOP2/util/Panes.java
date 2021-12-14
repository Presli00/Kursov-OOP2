package KursovProektOOP2.util;

import KursovProektOOP2.controllers.*;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Usernotifications;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

    public static void setAndClearAnchorPane(AnchorPane ap, AnchorPane contentAnchorPane) {
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

    public static void openWindow(String pathToView, Class c) throws IOException {
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
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(1);
            });
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }

    public static boolean checkForNotifs(ImageView img){
        for (int i = 0; i < UserSession.getNotifications().size(); i++){
            if(!UserSession.getNotifications().get(i).isRead()){
                img.setVisible(true);
                return true;
            }
        }
        return false;
    }

    public static void loadNotifications(){
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









/*static void loadRooms(){ this shit is work in progress
    Session session = Connection.openSession();
    Transaction transaction = session.beginTransaction();
    String Warehouse_QUERY = "SELECT w FROM Warehouse w WHERE rooms.storageRoomId=:roomID";
    try{
        Warehouse result = (Warehouse) session.createQuery(Warehouse_QUERY).setParameter("roomID", UserSession.getUserID()).getSingleResult();
        UserSession.setOwnerObject(result);
    }catch (Exception ex){
        log.error("Notifications retrieval unsuccessful " + "\n" + ex.getMessage());
    }finally {
        transaction.commit();
    }
}*/

    public static void loadWarehouses(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String OWNER_QUERY = "SELECT o FROM Owner o WHERE userId.userId = :userID";
        try{
            Owner result = (Owner) session.createQuery(OWNER_QUERY).setParameter("userID", UserSession.getUserID()).getSingleResult();
            UserSession.setOwnerObject(result);
        }catch (Exception ex){
            log.error("Notifications retrieval unsuccessful " + "\n" + ex.getMessage());
        }finally {
            transaction.commit();
        }
    }

    public static void setNameLabels(Text usernameText, Text firstNameText, Text lastNameText){
        usernameText.setText(UserSession.getUserName());
        firstNameText.setText(UserSession.getFirst_name());
        lastNameText.setText(UserSession.getLast_name());
    }

    public static void setAndClearScrollPane(ScrollPane sp, AnchorPane contentAnchorPane) {
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
