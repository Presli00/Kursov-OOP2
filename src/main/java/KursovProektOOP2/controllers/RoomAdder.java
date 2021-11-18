package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Climate;
import KursovProektOOP2.data.entity.ProductType;
import KursovProektOOP2.data.entity.StorageRoom;
import KursovProektOOP2.data.repository.ClimateRepository;
import KursovProektOOP2.data.repository.ProductTypeRepository;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;

public class RoomAdder {
    @FXML
    TextField sizeTF;
    @FXML
    ComboBox climateTF;
    @FXML
    ComboBox productTF;
    @FXML
    Button addButton;
    @FXML
    Label validationLabel;

    public final StorageRoomRepository repository = StorageRoomRepository.getInstance();
    public final ClimateRepository climateRepository = ClimateRepository.getInstance();
    public final ProductTypeRepository productTypeRepository = ProductTypeRepository.getInstance();
    List<Climate> climates = climateRepository.getAll();
    List<ProductType> productTypes = productTypeRepository.getAll();

    public void Add() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        if (validate()) {
            StorageRoom storageRoom=new StorageRoom();
            storageRoom.setStorageRoomId(0);
            storageRoom.setSize(Double.parseDouble(sizeTF.getText()));
            /*
            for the combo boxes

             */
            repository.save(storageRoom);
            stage.close();
        }
    }

    public boolean validate() {
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if (!sizeTF.getText().matches(".*\\d.*") || sizeTF.getText().length() < 2) {
            valid = false;
            errorMessage.append("Invalid size! ");
        }
        if (climateTF.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose a climate for the room! ");
        }
        if (productTF.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose which product type to be stored in the room! ");
        }

        if (valid) {
            return true;
        } else {
            validationLabel.setText(errorMessage.toString());
            return false;
        }
    }
    @FXML
    public void initialize(){
        climateTF.getItems().add(climateRepository.getAll());
        productTF.getItems().add(productTypeRepository.getAll());
    }
}
