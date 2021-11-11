package KursovProektOOP2.controllers;

import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class AccountInfo {
    @FXML
    Label label;

    public final UserRepository repository = UserRepository.getInstance();

    @FXML
    public void initialize(){
        List list = repository.getAll();
        label.setText(list.get(0).toString());
    }
}
