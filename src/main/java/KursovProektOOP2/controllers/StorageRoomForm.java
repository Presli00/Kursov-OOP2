package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.ClimateRepository;
import KursovProektOOP2.data.repository.ProductTypeRepository;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.data.services.ClimateService;
import KursovProektOOP2.data.services.ProductTypeService;
import KursovProektOOP2.data.services.WarehouseService;
import KursovProektOOP2.util.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.List;

public class StorageRoomForm {

    @FXML
    TextField sizeTF;
    @FXML
    ComboBox climateBox;
    @FXML
    ComboBox productBox;
    @FXML
    Button addRoomButton;
    @FXML
    Label errorLabel;
    public int warehouseID;
    public WarehouseViewer inst;
    public WarehouseInfo winst;

    public final ClimateService climateService = ClimateService.getInstance();
    public final ProductTypeService productTypeService = ProductTypeService.getInstance();
    public final WarehouseService warehouseService = WarehouseService.getInstance();

    @FXML
    private void initialize(){
        List<Climate> climates = climateService.getAllClimates();
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        for (int i = 0; i < climates.size(); i++) {
            climateBox.getItems().add(climates.get(i));
        }
        for (int i = 0; i < productTypes.size(); i++) {
            productBox.getItems().add(productTypes.get(i));
        }

        climateBox.setConverter(new StringConverter<Climate>() {
            @Override
            public String toString(Climate climate) {
                return climate.getClimate();
            }

            @Override
            public Climate fromString(final String string) {
                return (Climate) climateBox.getItems().stream().filter(climate -> ((Climate) climate).getClimate().equals(string)).findFirst().orElse(null);
            }
        });

        productBox.setConverter(new StringConverter<ProductType>() {
            @Override
            public String toString(ProductType productType) {
                return productType.getType();
            }

            @Override
            public ProductType fromString(final String string) {
                return (ProductType) productBox.getItems().stream().filter(productType -> ((ProductType) productType).getType().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    public void addRoom(){
        Stage stage = (Stage) addRoomButton.getScene().getWindow();
        if (validate()) { // if input is valid
            warehouseService.createNewRoom(Double.parseDouble(sizeTF.getText()), (Climate) climateBox.getValue(),(ProductType) productBox.getValue(), warehouseService.getWarehouseByID(warehouseID));
            stage.close();
            Platform.runLater(()->{
                inst.reloadWarehouseRooms(winst);
            });
        }
    }

    public boolean validate() {
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if (climateBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose a climate! ");
        }
        if (productBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose a product type! ");
        }

        if (valid) {
            return true;
        } else {
            errorLabel.setText(errorMessage.toString());
            return false;
        }
    }
}
