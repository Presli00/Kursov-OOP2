package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Climate;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.ClimateRepository;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;

public class RegistrationForm {

    @FXML
    TextField firstNameTF;
    @FXML
    TextField lastNameTF;
    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;
    @FXML
    TextField roleTF;
    @FXML
    Button registerButton;
    public final UserRepository repository = UserRepository.getInstance();

    public void Register(){
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }

     private String passwordGenerator(){
        return RandomStringUtils.randomAscii(12);
    }
}
