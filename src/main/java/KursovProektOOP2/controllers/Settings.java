package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Settings {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    Button applyButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;
    @FXML
    TextField passwordVisibleTF;
    @FXML
    PasswordField conPasswordTF;
    @FXML
    TextField conPasswordVisibleTF;
    @FXML
    CheckBox passwordCheck;
    @FXML
    Label errorLabel;
    private static final Logger log = Logger.getLogger(Main.class);
    int currentUser = UserSession.getUserID();

    public void apply() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String CHANGE_QUERY = "SELECT u FROM User u WHERE userId='" + currentUser + "'"; //query to change the username

        try {
            User result = (User) session.createQuery(CHANGE_QUERY).getSingleResult();
            result.setUsername(usernameTF.getText());
            result.setPassword(passwordTF.getText());
            cancel();
        } catch (Exception ex) {
            log.error("Password didn't match");
        } finally {
            transaction.commit();
        }
    }

    public void check() {
        if (passwordCheck.isSelected()) {
            passwordVisibleTF.setText(passwordTF.getText());
            passwordVisibleTF.setVisible(true);
            passwordTF.setVisible(false);

            conPasswordVisibleTF.setText(conPasswordTF.getText());
            conPasswordVisibleTF.setVisible(true);
            conPasswordTF.setVisible(false);
            return;
        }
        passwordTF.setText(passwordVisibleTF.getText());
        passwordTF.setVisible(true);
        passwordVisibleTF.setVisible(false);

        conPasswordTF.setText(conPasswordVisibleTF.getText());
        conPasswordTF.setVisible(true);
        conPasswordVisibleTF.setVisible(false);
    }

    public void cancel() {
        usernameTF.clear();
        passwordTF.clear();
        passwordVisibleTF.clear();
        conPasswordTF.clear();
        conPasswordVisibleTF.clear();
    }
}
