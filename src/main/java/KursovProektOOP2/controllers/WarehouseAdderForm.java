package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.hibernate.Session;

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

    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();
    public final MaintenanceRepository maintenanceRepository = MaintenanceRepository.getInstance();
    public final CityRepository cityRepository = CityRepository.getInstance();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();


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
        if (validate()) {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseId(0);
            warehouse.setWarehouseName(warehouseNameTF.getText());
            warehouse.setCityId((City) cityBox.getValue());
            warehouse.setStreet(streetTF.getText());
            warehouse.setNumberOfStorageRooms(Integer.parseInt(roomAmountTF.getText()));
            warehouse.setMaintenanceId((Maintenance) maintenanceBox.getValue());
            warehouse.setOwnerId((Owner) ownerBox.getValue());
            warehouse.setAgentsId(null);
            warehouseRepository.save(warehouse);
            /*Owner owner = (Owner) ownerBox.getValue();
            owner.getWarehouses().add(warehouse);
            owner.setWarehousesAmount(owner.getWarehousesAmount() + 1);
            ownerRepository.update(owner);*/
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
        if (maintenanceBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose maintenance! ");
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
        maintenance = maintenanceRepository.getAll();
    }

    public void getAllCities() {
        cities = cityRepository.getAll();
    }

    public void getAllOwners() {
        owners = ownerRepository.getAll();
    }


}
