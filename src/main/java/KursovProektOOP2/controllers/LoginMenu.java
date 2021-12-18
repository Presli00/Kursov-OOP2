package KursovProektOOP2.controllers;

import KursovProektOOP2.controllers.Admin.AdminGUI;
import KursovProektOOP2.controllers.Agent.WarehouseAgentGUI;
import KursovProektOOP2.controllers.Owner.OwnerGUI;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Usernotifications;
import KursovProektOOP2.util.Panes;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoginMenu {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;
    @FXML
    TextField passwordVisibleTF;
    @FXML
    CheckBox passwordCheck;
    @FXML
    Button loginButton;
    @FXML
    Label errorLabel;

    private static final Logger log = Logger.getLogger(Main.class);

    @FXML
    private void initialize(){
        AnchorPane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                loginButton.fire();
                ev.consume();
            }
        });
    }

    public void login() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String LOGIN_QUERY = "SELECT u FROM User u WHERE username = '" + usernameTF.getText() + "' AND password = '" + passwordTF.getText() + "'"; // QUERY
        try {
            User result = (User) session.createQuery(LOGIN_QUERY).getSingleResult();
            if(result.getRoleId().getRoleId() == 1){ // LOAD ADMIN VIEW
                closeLoginMenu();
                UserSession.getInstance(result);
                Panes.openWindow("/Views/AdminViews/AdminGUI.fxml", AdminGUI.class);
            }
            if(result.getRoleId().getRoleId() == 2){ // LOAD OWNER VIEW
                closeLoginMenu();
                UserSession.getInstance(result);
                Panes.loadOwner();
                Panes.openWindow("/Views/OwnerViews/OwnerGUI.fxml", OwnerGUI.class);
            }
            if(result.getRoleId().getRoleId() == 3){ // LOAD AGENT VIEW
                closeLoginMenu();
                UserSession.getInstance(result);
                Panes.loadAgent();
                Panes.openWindow("/Views/AgentViews/WarehouseAgentGUI.fxml", WarehouseAgentGUI.class);
            }

        } catch (Exception ex) {
            log.error("Login unsuccessful" + ex.getMessage());
            errorLabel.setText("You entered wrong information!");
        } finally {
            transaction.commit();
        }

    }

    public void closeLoginMenu(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void check() {
        if (passwordCheck.isSelected()) {
            passwordVisibleTF.setText(passwordTF.getText());
            passwordVisibleTF.setVisible(true);
            passwordTF.setVisible(false);
            return;
        }
        passwordTF.setText(passwordVisibleTF.getText());
        passwordTF.setVisible(true);
        passwordVisibleTF.setVisible(false);
    }

}
