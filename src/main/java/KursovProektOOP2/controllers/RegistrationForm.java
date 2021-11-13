package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.RoleRepository;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    TextField roleTF;
    @FXML
    TextField passwordVisibleTF;
    @FXML
    Button registerButton;
    @FXML
    CheckBox passwordCheck;

    public final UserRepository repository = UserRepository.getInstance();
    public final RoleRepository roleRepository = RoleRepository.getInstance();
    List<Role> roles = roleRepository.getAll();

    public void Register(){
        Stage stage = (Stage) registerButton.getScene().getWindow();
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
        switch (roleTF.getText()){
            case "1":
            case "Admin":
            case "admin":
                user.setRoleId(roles.get(0));
                break;
            case "2":
            case "Owner":
            case "owner":
            case "Warehouse Owner":
            case "warehouse owner":
            case "Warehouse owner":
            case "warehouse Owner":
                user.setRoleId(roles.get(1));
                break;
            case "3":
            case "Agent":
            case "agent":
            case "Warehouse Agent":
            case "warehouse agent":
            case "Warehouse agent":
            case "warehouse Agent":
                user.setRoleId(roles.get(2));
                break;
        }
        Date date = new Date();
        user.setCreatedDate(new Timestamp(date.getTime()));
        user.setUpdatedDate(null);
        repository.save(user);

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

     public void passwordGenerator(){
        passwordTF.setText(RandomStringUtils.randomAscii(12));
    }
}
