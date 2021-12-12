package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.City;
import KursovProektOOP2.data.entity.Maintenance;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class WarehouseAdderForm {

    @FXML
    TextField warehouseNameTF;
    @FXML
    ComboBox cityBox;
    @FXML
    TextField streetTF;
    @FXML
    TextField roomAmountTF;
    @FXML
    ComboBox maintenanceBox;
    @FXML
    Button addWarehouseButton;
    @FXML
    Label errorLabel;

    List maintenance;
    List cities;

    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();
    public final MaintenanceRepository maintenanceRepository = MaintenanceRepository.getInstance();
    public final CityRepository cityRepository = CityRepository.getInstance();

    @FXML
    public void initialize(){
        getAllMaintenance();
        new Thread(() -> getAllCities()).start();
        for (int i = 0; i < cities.size(); i++){
            cityBox.getItems().add(cities.get(i));
        }
        for (int i = 0; i < maintenance.size(); i++){
            if(!((Maintenance)maintenance.get(i)).isEmployed()){
                maintenanceBox.getItems().add(maintenance.get(i));
            }
        }

        // allow only numeric values in this text field, comrade!
        roomAmountTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                roomAmountTF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cityBox.setConverter(new StringConverter<City>() {
            @Override
            public String toString(City city) {
                return city.getCity();
            }
            @Override
            public City fromString(final String string) {
                return (City) cityBox.getItems().stream().filter(city -> ((City)city).getCity().equals(string)).findFirst().orElse(null);
            }
        });

        maintenanceBox.setConverter(new StringConverter<Maintenance>() {
            @Override
            public String toString(Maintenance maintenance) {
                return maintenance.getName();
            }
            @Override
            public Maintenance fromString(final String string) {
                return (Maintenance) cityBox.getItems().stream().filter(maintenance -> ((Maintenance)maintenance).getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    public void addWarehouse(){
        Stage stage = (Stage) addWarehouseButton.getScene().getWindow();
        if(validate()){
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseId(0);
            warehouse.setWarehouseName(warehouseNameTF.getText());
            warehouse.setCityId((City) cityBox.getValue());
            warehouse.setStreet(streetTF.getText());
            warehouse.setNumberOfStorageRooms(Integer.parseInt(roomAmountTF.getText()));
            warehouse.setMaintenanceId((Maintenance) maintenanceBox.getValue());
            warehouse.setAgentsId(null);
            warehouseRepository.save(warehouse);
            stage.close();
        }
    }

    public boolean validate(){
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if(warehouseNameTF.getText().length() < 2){ // Check if name is shorter than 2 characters
            valid = false;
            errorMessage.append("Warehouse name too short! ");
        }
        if(streetTF.getText().length() < 2){
            valid = false;
            errorMessage.append("Street name too short! ");
        }
        if(Integer.parseInt(roomAmountTF.getText()) <= 0 ){
            valid = false;
            errorMessage.append("Room amount can't be less than 0! ");
        }
        if(cityBox.getSelectionModel().isEmpty()){
            valid = false;
            errorMessage.append("Choose a city! ");
        }
        if(maintenanceBox.getSelectionModel().isEmpty()){
            valid = false;
            errorMessage.append("Choose maintenance! ");
        }

        if(valid){
            return true;
        }else{
            errorLabel.setText(errorMessage.toString());
            return false;
        }
    }

    public void getAllMaintenance(){
        maintenance = maintenanceRepository.getAll();
    }
    public void getAllCities(){
        cities = cityRepository.getAll();
    }


}
