package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.RoleRepository;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    ComboBox roleTF;
    @FXML
    TextField passwordVisibleTF;
    @FXML
    Button registerButton;
    @FXML
    CheckBox passwordCheck;
    @FXML
    Label validationLabel;

    public final UserRepository repository = UserRepository.getInstance();
    public final RoleRepository roleRepository = RoleRepository.getInstance();
    List<Role> roles = roleRepository.getAll();

    public void Register(){
        Stage stage = (Stage) registerButton.getScene().getWindow();
        if(validate()){
            User user = new User();
            user.setUserId(0);
            user.setUsername(usernameTF.getText());
            user.setFirstName(firstNameTF.getText());
            user.setLastName(lastNameTF.getText());
            if(passwordCheck.isSelected()){
                user.setPassword(passwordVisibleTF.getText());
            }else{
                user.setPassword(passwordTF.getText());
            }
            if(roleTF.getValue() == roleTF.getItems().get(0)){
                user.setRoleId(roles.get(0));
            }else if(roleTF.getValue() == roleTF.getItems().get(1)){
                user.setRoleId(roles.get(1));
            }else{
                user.setRoleId(roles.get(2));
            }
            Date date = new Date();
            user.setCreatedDate(new Timestamp(date.getTime()));
            user.setUpdatedDate(null);
            repository.save(user);
            stage.close();
        }
    }

    public boolean validate(){
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if(firstNameTF.getText().matches(".*\\d.*") || firstNameTF.getText().length() < 2){ // Check if name contains numbers or is shorter than 2 characters
            valid = false;
            errorMessage.append("Invalid First Name! ");
        }
        if(lastNameTF.getText().matches(".*\\d.*") || lastNameTF.getText().length() < 2){
            valid = false;
            errorMessage.append("Invalid Last Name! ");
        }
        if(usernameTF.getText().isEmpty()){
            valid = false;
            errorMessage.append("Invalid Username! ");
        }
        if(passwordTF.getText().length() < 8 || passwordVisibleTF.getText().length() < 8){
            valid = false;
            errorMessage.append("Password must be at least 8 characters! ");
        }
        if(roleTF.getSelectionModel().isEmpty()){
            valid = false;
            errorMessage.append("Choose a role! ");
        }

        if(valid){
            return true;
        }else{
            validationLabel.setText(errorMessage.toString());
            return false;
        }
    }

    @FXML
    public void initialize(){ //Add items to combobox
        roleTF.getItems().add("Admin");
        roleTF.getItems().add("Warehouse Owner");
        roleTF.getItems().add("Warehouse Agent");
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

     public void passwordGenerator(){
        passwordTF.setText(RandomStringUtils.randomAscii(12));
    }
}
