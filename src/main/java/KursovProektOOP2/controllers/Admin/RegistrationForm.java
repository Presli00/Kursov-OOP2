package KursovProektOOP2.controllers.Admin;

import KursovProektOOP2.data.entity.Climate;
import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.RoleRepository;
import KursovProektOOP2.data.repository.UserRepository;
import KursovProektOOP2.data.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    TextField emailTF;
    @FXML
    TextField phoneTF;
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

    public final UserService service = UserService.getInstance();
    public final RoleRepository roleRepository = RoleRepository.getInstance();
    List<Role> roles = roleRepository.getAll();

    public void Register(){
        Stage stage = (Stage) registerButton.getScene().getWindow();
        if(validate()){
            if(passwordCheck.isSelected()){
                service.addUser(usernameTF.getText(), firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
                        phoneTF.getText(), passwordVisibleTF.getText(), true, (Role) roleTF.getValue());
            }else{
                service.addUser(usernameTF.getText(), firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
                        phoneTF.getText(), passwordTF.getText(), false, (Role) roleTF.getValue());
            }
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
        if(emailTF.getText().length() < 10){
            valid = false;
            errorMessage.append("Invalid E-Mail! ");
        }
        if(phoneTF.getText().length() < 10 || phoneTF.getText().length() > 12){
            valid = false;
            errorMessage.append("Invalid Phone! ");
        }
        if(passwordTF.getText().length() < 8 && passwordVisibleTF.getText().length() < 8){
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
        List<Role> roleList = new ArrayList<>(roleRepository.getAll());
        for (int i = 0; i < roleList.size(); i++) {
            roleTF.getItems().add(roleList.get(i));
        }

        roleTF.setConverter(new StringConverter<Role>() {
            @Override
            public String toString(Role Role) {
                return Role.getRoleName();
            }

            @Override
            public Role fromString(final String string) {
                return (Role) roleTF.getItems().stream().filter(Role -> ((Role) Role).getRoleName().equals(string)).findFirst().orElse(null);
            }
        });
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
