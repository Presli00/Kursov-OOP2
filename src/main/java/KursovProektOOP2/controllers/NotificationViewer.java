package KursovProektOOP2.controllers;

import KursovProektOOP2.controllers.Admin.AdminGUI;
import KursovProektOOP2.controllers.Agent.WarehouseAgentGUI;
import KursovProektOOP2.controllers.Owner.OwnerGUI;
import KursovProektOOP2.data.entity.Usernotifications;
import KursovProektOOP2.data.services.UserNotificationService;
import KursovProektOOP2.util.Panes;
import KursovProektOOP2.util.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;

    List<Usernotifications> notifs;
    List<NotificationInfo> selected = new ArrayList<>();
    public final UserNotificationService userNotificationService = UserNotificationService.getInstance();

    @FXML
    public void initialize() throws IOException {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SharedViews/NotificationInfo.fxml"));
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
        if(selected.size() > 0){ // if size is 0, do nothing
            for(int i = 0; i < selected.size(); i++){
                userNotificationService.markAsRead(selected.get(i).id);
                selected.get(i).notificationCheck.setSelected(false);
                selected.get(i).isReadDot.setVisible(false); // REMOVE DOT
            }
            reload();
            selected.clear();
        }


    }

    @FXML
    public void DeleteNotification(){
        if(selected.size() > 0){
            for(int i = 0; i < selected.size(); i++){
                userNotificationService.deleteNotif(selected.get(i).id);
            }

            reload();
            selected.clear();
        }


    }

    @FXML
    private void RefreshNotification(){
        reload();
    }

    private void reload() {
        Panes.loadNotifications(); // RELOAD NOTIFICATIONS
        notifs = UserSession.getNotifications(); // SET NOTIFICATIONS
        new Thread(() -> { // UI LOCKS IF NOT IN ANOTHER THREAD
            try {
                Thread.sleep(250); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> // run on fx thread
                    {
                        try {
                            Vbox.getChildren().clear();
                            buildNotifs();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }).start();

        allAreReadThreadCall();
    }

    private void allAreReadThreadCall() {
        Platform.runLater(() -> {
            try {
                allAreRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void allAreRead() throws IOException { // omega cringe
        if(UserSession.getRoleID().getRoleId() == 1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AdminViews/AdminGUI.fxml"));
            Parent root = loader.load();
            AdminGUI controller = loader.getController();
            if(Panes.checkForNotifs(controller.exclamationMark)) {
                ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(true); //double job
                return;
            }
            else ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(false);
        }
        if(UserSession.getRoleID().getRoleId() == 2){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OwnerViews/OwnerGUI.fxml"));
            Parent root = loader.load();
            OwnerGUI controller = loader.getController();
            if(Panes.checkForNotifs(controller.exclamationMark)) {
                ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(true);
                return;
            }
            else ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(false);
        }
        if(UserSession.getRoleID().getRoleId() == 3){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AgentViews/WarehouseAgentGUI.fxml"));
            Parent root = loader.load();
            WarehouseAgentGUI controller = loader.getController();
            if(Panes.checkForNotifs(controller.exclamationMark)) {
                ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(true);
                return;
            }
            else ScrollPane.getParent().getParent().lookup("#exclamationMark").setVisible(false);
        }

    }

}
