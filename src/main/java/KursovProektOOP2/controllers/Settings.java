package KursovProektOOP2.controllers;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.services.UserService;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.Date;

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

    @FXML
    Text usernameText;
    @FXML
    Text emailText;
    @FXML
    Text phoneText;
    @FXML
    Text nameText;
    @FXML
    Text familyText;
    @FXML
    Text roleText;
    @FXML
    Text createdText;
    @FXML
    Text updatedText;

    public final UserService service = UserService.getInstance();

    public void initialize() {
        usernameText.setText(UserSession.getUserName());
        phoneText.setText(UserSession.getPhone());
        emailText.setText(UserSession.geteMail());
        nameText.setText(UserSession.getFirst_name());
        familyText.setText(UserSession.getLast_name());
        roleText.setText(UserSession.getRoleID().getRoleName());
        createdText.setText(UserSession.getCreatedDate());
        if(UserSession.getUpdatedDate()==null){
            updatedText.setText("Has not been updated yet");
        }
        else {
            updatedText.setText(UserSession.getUpdatedDate().toString());
        }
    }

    public void apply() {
        if(passwordCheck.isSelected()){
            service.updateUser(usernameTF.getText(), passwordVisibleTF.getText());
        }else{
            service.updateUser(usernameTF.getText(), passwordTF.getText());
        }
        cancel();
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
