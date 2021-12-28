package KursovProektOOP2.util;

import KursovProektOOP2.controllers.*;
import KursovProektOOP2.controllers.Admin.AdminGUI;
import KursovProektOOP2.controllers.Agent.WarehouseAgentGUI;
import KursovProektOOP2.controllers.Owner.OwnerGUI;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Panes {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void setAndClearAnchorPane(AnchorPane ap, AnchorPane contentAnchorPane) {
        if (!contentAnchorPane.getChildren().isEmpty()) {
            contentAnchorPane.getChildren().clear();
        }
        contentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(contentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(contentAnchorPane.getHeight());
        contentAnchorPane.widthProperty().addListener(event -> ap.setPrefWidth(contentAnchorPane.getWidth()));

        contentAnchorPane.heightProperty().addListener(event -> ap.setPrefHeight(contentAnchorPane.getHeight()));
    }

    public static double getRating(List<Rating> list){
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getRating();
        }
        return sum/ list.size();
    }

    public static void openWindow(String pathToView, Class c) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = c.getResource(pathToView);

        if (path != null) {
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(c.getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            if (c == OwnerGUI.class || c == WarehouseAgentGUI.class || c == AdminGUI.class) {
                //Stage is Resizable by default
                stage.setMinHeight(650);
                stage.setMinWidth(1200);
            } else {
                stage.setResizable(false);
            }
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(1);
            });
            stage.show();
        } else {
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }

    public static boolean checkForNotifs(ImageView img) {
        for (int i = 0; i < UserSession.getNotifications().size(); i++) {
            if (!UserSession.getNotifications().get(i).isRead()) {
                img.setVisible(true);
                return true;
            }
        }
        return false;
    }

    public static void loadNotifications() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String NOTIFICATION_QUERY = "SELECT u FROM Usernotifications u WHERE idFromUser.userId = :userID";
        try {
            List<Usernotifications> notifications = session.createQuery(NOTIFICATION_QUERY).setParameter("userID", UserSession.getUserID()).getResultList();
            UserSession.setNotifications(notifications); // Setting it here since we can't execute another query in the login session (i think lol)
        } catch (Exception ex) {
            log.error("Notifications retrieval unsuccessful " + "\n" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    public static void loadOwner(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String OWNER_QUERY = "SELECT o FROM Owner o WHERE userId.userId = :userID";
        try {
            Owner result = (Owner) session.createQuery(OWNER_QUERY).setParameter("userID", UserSession.getUserID()).getSingleResult();
            UserSession.setOwner(result);
        } catch (Exception ex) {
            log.error("Owner load unsuccessful " + "\n" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    public static void loadAgent(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String AGENT_QUERY = "SELECT a FROM Agent a WHERE idFromUser.userId = :userID";
        try {
            Agent result = (Agent) session.createQuery(AGENT_QUERY).setParameter("userID", UserSession.getUserID()).getSingleResult();
            UserSession.setAgent(result);
        } catch (Exception ex) {
            log.error("Agent load unsuccessful " + "\n" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }
    public static void setNameLabels(Text usernameText, Text firstNameText, Text lastNameText) {
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
        contentAnchorPane.widthProperty().addListener(event -> sp.setPrefWidth(contentAnchorPane.getWidth()));

        contentAnchorPane.heightProperty().addListener(event -> sp.setPrefHeight(contentAnchorPane.getHeight()));
    }

    public static void checkForExpiringFormulars(boolean isOwner){
        Date date=new Date();
        if(isOwner){
            Owner owner = (Owner) OwnerRepository.getInstance().getById(UserSession.getOwner().getIdOwner()).get();
            List<Warehouse> warehouses = new ArrayList<>(owner.getWarehouses());
            for (Warehouse w : warehouses){ // go through warehouses
                for (StorageRoom room : w.getRooms()){ // go through rooms of warehouse
                    if(room.getFormulars().size() != 0){
                        if(room.isRented() && room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd().before(new Timestamp(date.getTime()))){ // if last formular's ending date is before the current time it has passed
                            room.setRented(false);
                            StorageRoomRepository.getInstance().update(room);
                        }
                        if(room.isRented() && ChronoUnit.DAYS.between(new Timestamp(date.getTime()).toLocalDateTime(), room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd().toLocalDateTime()) <= 3 && !room.getFormulars().get(room.getFormulars().size()-1).isNotifOwner()){ //if the days between today and formular are 3 or less AND notifSent is false send notification
                            Usernotifications notification = new Usernotifications();
                            notification.setIdFromUser(owner.getUserId());
                            notification.setNotificationName("Договор за стая от склад: " + w.getWarehouseName() + "\nИзтича скоро " + room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd());
                            notification.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
                            owner.getUserId().getUsernotifications().add(notification);
                            OwnerRepository.getInstance().update(owner);
                            UserNotificationRepository.getInstance().save(notification);
                            room.getFormulars().get(room.getFormulars().size()-1).setNotifOwner(true);
                            FormularRepository.getInstance().update(room.getFormulars().get(room.getFormulars().size()-1));
                        }
                    }
                }
            }
        }else{
            Agent agent = (Agent) AgentRepository.getInstance().getById(UserSession.getAgent().getIdAgent()).get();

            List<Warehouse> warehouses = new ArrayList<>(agent.getWarehouses());
            for (Warehouse w : warehouses){ // go through warehouses
                for (StorageRoom room : w.getRooms()){ // go through rooms of warehouse
                    if(room.getFormulars().size() != 0){ // if there are any formulars
                        if(room.isRented() && room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd().before(new Timestamp(date.getTime()))){ // if last formular's ending date is before the current time it has passed
                            room.setRented(false);
                            StorageRoomRepository.getInstance().update(room);
                        }
                        if(room.isRented() && ChronoUnit.DAYS.between(new Timestamp(date.getTime()).toLocalDateTime(), room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd().toLocalDateTime()) <= 3 && !room.getFormulars().get(room.getFormulars().size()-1).isNotifAgent()){ //if the days between today and formular are 3 or less AND notifSent is false send notification
                            Usernotifications notification = new Usernotifications();
                            notification.setIdFromUser(agent.getIdFromUser());
                            notification.setNotificationName("Договор за стая от склад: " + w.getWarehouseName() + "\nИзтича скоро " + room.getFormulars().get(room.getFormulars().size()-1).getPeriodEnd());
                            notification.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
                            agent.getIdFromUser().getUsernotifications().add(notification);
                            AgentRepository.getInstance().update(agent);
                            UserNotificationRepository.getInstance().save(notification);
                            room.getFormulars().get(room.getFormulars().size()-1).setNotifAgent(true);
                            FormularRepository.getInstance().update(room.getFormulars().get(room.getFormulars().size()-1));
                        }
                    }
                }
            }
        }
    }
}
