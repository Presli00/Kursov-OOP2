package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Usernotifications;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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


    @FXML
    public void initialize(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String NOTIFICATION_QUERY = "SELECT u FROM Usernotifications u WHERE idFromUser.userId = :userID AND idFromUser.roleId.id = :roleID";
        try{
            List<Usernotifications> notifications = session.createQuery(NOTIFICATION_QUERY).setParameter("userID", UserSession.getUserID()).setParameter("roleID",3).getResultList();
            for (int i = 0; i < notifications.size();i++){
                if(!notifications.get(i).isRead()){
                    exclamationMark.setVisible(true);
                }
            }
            UserSession.setNotifications(notifications); // Setting it here since we can't execute another query in the login session
        }catch (Exception ex){
            log.error("Notifications retrieval unsuccessful " + "\n" + ex.getMessage());
        }finally {
            transaction.commit();
        }


    }

    @FXML
    public void SpravkiOnAction() throws IOException{
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AgentViews/WarehouseAgentSpravki.fxml")); //LOAD VIEW
        Panes.clearAnchorPane(ap, ContentAnchorPane);
    }

    @FXML
    public void SettingOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/Settings.fxml")); //LOAD VIEW
        Panes.clearAnchorPane(ap, ContentAnchorPane);

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
