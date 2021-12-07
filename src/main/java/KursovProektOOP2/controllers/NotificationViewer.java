package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Usernotifications;
import KursovProektOOP2.data.repository.UserNotificationRepository;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NotificationViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;

    List<Usernotifications> notifs;
    List<NotificationInfo> selected = new ArrayList<>();
    public final UserNotificationRepository repository = UserNotificationRepository.getInstance();
    private static final Logger log = Logger.getLogger(Main.class);


    @FXML
    public void initialize() throws IOException {
        Panes.loadNotifications(null, false);
        notifs = UserSession.getNotifications();
        buildNotifs();

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }

    private void buildNotifs() throws IOException {
        for(int i = 0; i < notifs.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/NotificationViews/NotificationInfo.fxml"));
            AnchorPane notification = loader.load();
            NotificationInfo controller = loader.getController();
            controller.notificationText.setText(notifs.get(i).getNotificationName());
            if(notifs.get(i).isRead()){
                controller.isReadDot.setVisible(false);
            }
            controller.timestampLabel.setText(notifs.get(i).getNotifTimeStamp().toString());
            controller.id = notifs.get(i).getIdNotifications(); // ID OF NOTIFICATION FOR QUERY
            Vbox.getChildren().add(notification);
            controller.notificationCheck.setOnAction(e->{
                if(controller.notificationCheck.isSelected()){
                    selected.add(controller);
                }else{
                    selected.remove(controller);
                }
            });

        }
    }

    @FXML
    public void MarkAsRead(){
        for(int i = 0; i < selected.size(); i++){
            Session session = Connection.openSession();
            Transaction transaction = session.beginTransaction();
            String UPDATE_QUERY = "UPDATE Usernotifications SET isRead = true WHERE idNotifications = :idNotif";
            try{
                session.createQuery(UPDATE_QUERY).setParameter("idNotif", selected.get(i).id).executeUpdate();
                selected.get(i).isReadDot.setVisible(false);
                Panes.loadNotifications(null, false);
            }catch (Exception ex){
                log.error("Notifications marking unsuccessful " + "\n" + ex.getMessage());
            }finally {
                transaction.commit();
            }
        }
    }

    @FXML
    public void DeleteNotification(){
        for(int i = 0; i < selected.size(); i++){
            Session session = Connection.openSession();
            Transaction transaction = session.beginTransaction();
            String DELETE_QUERY = "DELETE FROM Usernotifications WHERE idNotifications = :idNotif";
            try{
                session.createQuery(DELETE_QUERY).setParameter("idNotif", selected.get(i).id).executeUpdate();
                Panes.loadNotifications(null, false);
                notifs = UserSession.getNotifications();// ??
                buildNotifs();
            }catch (Exception ex){
                log.error("Notifications marking unsuccessful " + "\n" + ex.getMessage());
            }finally {
                transaction.commit();
            }
        }
    }

}
