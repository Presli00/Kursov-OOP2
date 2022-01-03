package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.services.CityService;
import KursovProektOOP2.data.services.UserService;
import KursovProektOOP2.data.services.WarehouseService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    ComboBox ownerBox;
    @FXML
    Button addWarehouseButton;
    @FXML
    Label errorLabel;

    List maintenance;
    List cities;
    List owners;

    public final WarehouseService warehouseService = WarehouseService.getInstance();
    public final CityService cityService = CityService.getInstance();
    public final UserService userService = UserService.getInstance();


    @FXML
    public void initialize() {
        getAllMaintenance();
        getAllCities();
        getAllOwners();
        for (int i = 0; i < cities.size(); i++) {
            cityBox.getItems().add(cities.get(i));
        }
        for (int i = 0; i < maintenance.size(); i++) {
            if (!((Maintenance) maintenance.get(i)).isEmployed()) {
                maintenanceBox.getItems().add(maintenance.get(i));
            }
        }
        for (int i = 0; i < owners.size(); i++) {
            ownerBox.getItems().add(owners.get(i));
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
                return (City) cityBox.getItems().stream().filter(city -> ((City) city).getCity().equals(string)).findFirst().orElse(null);
            }
        });

        maintenanceBox.setConverter(new StringConverter<Maintenance>() {
            @Override
            public String toString(Maintenance maintenance) {
                return maintenance.getName();
            }

            @Override
            public Maintenance fromString(final String string) {
                return (Maintenance) maintenanceBox.getItems().stream().filter(maintenance -> ((Maintenance) maintenance).getName().equals(string)).findFirst().orElse(null);
            }
        });

        ownerBox.setConverter(new StringConverter<Owner>() {
            @Override
            public String toString(Owner owner) {
                return owner.getUserId().getUsername();
            }

            @Override
            public Owner fromString(final String string) {
                return (Owner) ownerBox.getItems().stream().filter(owner -> ((Owner) owner).getUserId().getUsername().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    public void addWarehouse() {
        Stage stage = (Stage) addWarehouseButton.getScene().getWindow();
        if (validate()) { // if input is valid
            Owner obj = (Owner) ownerBox.getValue();
            Owner owner = userService.getOwnerByID(obj.getIdOwner());
            if (maintenanceBox.getSelectionModel().isEmpty()){
                warehouseService.addWarehouse(warehouseNameTF.getText(), (City) cityBox.getValue(), streetTF.getText(), Integer.parseInt(roomAmountTF.getText()), 1, true, owner);
            }else{
                warehouseService.addWarehouse(warehouseNameTF.getText(), (City) cityBox.getValue(), streetTF.getText(), Integer.parseInt(roomAmountTF.getText()), ((Maintenance) maintenanceBox.getValue()).getMaintenanceId(), false, owner);
            }
            userService.increaseOwnerWarehouses(owner);
            stage.close();
        }
    }

    public boolean validate() {
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if (warehouseNameTF.getText().length() < 2) { // Check if name is shorter than 2 characters
            valid = false;
            errorMessage.append("Warehouse name too short! ");
        }
        if (streetTF.getText().length() < 2) {
            valid = false;
            errorMessage.append("Street name too short! ");
        }
        if (Integer.parseInt(roomAmountTF.getText()) <= 0) {
            valid = false;
            errorMessage.append("Room amount can't be less than 0! ");
        }
        if (cityBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose a city! ");
        }
        if (ownerBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose an owner! ");
        }

        if (valid) {
            return true;
        } else {
            errorLabel.setText(errorMessage.toString());
            return false;
        }
    }

    public void getAllMaintenance() {
        maintenance = warehouseService.getAllMaintenance();
    }

    public void getAllCities() {
        cities = cityService.getAllCities();
    }

    public void getAllOwners() {
        owners = userService.getAllOwners();
    }


}
