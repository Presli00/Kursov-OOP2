package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;

public class LoginMenu {
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


    public void login() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String LOGIN_QUERY = "SELECT u FROM User u WHERE username = '" + usernameTF.getText() + "' AND password = '" + passwordTF.getText() + "'"; // QUERY
        try {
            User result = (User) session.createQuery(LOGIN_QUERY).getSingleResult();
            if(result.getRoleId().getRoleId() == 1){ // LOAD ADMIN VIEW
                closeLoginMenu();
                openWindow("/Views/AdminViews/AdminGUI.fxml");
                UserSession.getInstace(result.getUserId(), result.getUsername(), result.getPassword());
            }
            if(result.getRoleId().getRoleId() == 2){ // LOAD OWNER VIEW
                closeLoginMenu();
                openWindow("/Views/OwnerViews/OwnerGUI.fxml");
                UserSession.getInstace(result.getUserId(), result.getUsername(), result.getPassword());
            }
            if(result.getRoleId().getRoleId() == 3){ // LOAD AGENT VIEW
                closeLoginMenu();
                openWindow("/Views/AgentViews/WarehouseAgentGUI.fxml");
                UserSession.getInstace(result.getUserId(), result.getUsername(), result.getPassword());
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

    public void openWindow(String pathToView) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = getClass().getResource(pathToView);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            stage.setMinHeight(650);
            stage.setMinWidth(1200);
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }
}
