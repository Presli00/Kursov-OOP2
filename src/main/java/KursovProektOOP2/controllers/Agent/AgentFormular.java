package KursovProektOOP2.controllers.Agent;

import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.services.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class AgentFormular {
    @FXML
    TextField namesTF;
    @FXML
    TextField phoneTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField priceTF;
    @FXML
    ComboBox<City> cityBox;
    @FXML
    Text errorText;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    Button createFormularButton;
    List<City> cities;

    public int roomID;
    public int ownerID;
    public int agentID;

    public final CityService cityService = CityService.getInstance();
    public final WarehouseService warehouseService = WarehouseService.getInstance();
    public final UserService userService = UserService.getInstance();
    public final RenterService renterService = RenterService.getInstance();
    public final FormularService formularService = FormularService.getInstance();
    public final UserNotificationService userNotificationService = UserNotificationService.getInstance();

    @FXML
    private void initialize(){
        cities = cityService.getAllCities();
        for (City city : cities) {
            cityBox.getItems().add(city);
        }

        startDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        endDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        cityBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(City city) {
                return city.getCity();
            }

            @Override
            public City fromString(final String string) {
                return cityBox.getItems().stream().filter(city -> city.getCity().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void saveFormular(){
        Stage stage = (Stage) createFormularButton.getScene().getWindow();
        if (validate()) { // if input is valid

            StorageRoom room = warehouseService.getStorageRoomByID(roomID);
            Owner owner = userService.getOwnerByID(ownerID);
            Agent agent = userService.getAgentByID(agentID);

            if(room.isRented()){
                stage.close();
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Room is already Rented");
                a.show();
            }else{
                int id = renterService.addRenter(namesTF.getText(), phoneTF.getText(), cityBox.getValue(), addressTF.getText());

                Formular formular = formularService.createFormular(room, id, startDate.getValue(), endDate.getValue(), Double.parseDouble(priceTF.getText()));

                userService.increaseAgentDeals(agent);

                warehouseService.setRoomRented(room, true);

                userNotificationService.createOwnerNotif(owner, room, formular);
            }

            stage.close();
        }
    }

    public boolean validate() {
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        if (namesTF.getText().length() < 2) {
            valid = false;
            errorMessage.append("Names are too short! ");
        }
        if (phoneTF.getText().length() < 10) {
            valid = false;
            errorMessage.append("Mobile numbers are 10 characters long! ");
        }
        if (addressTF.getText().length() < 2) {
            valid = false;
            errorMessage.append("Address is too short! ");
        }
        if (cityBox.getSelectionModel().isEmpty()) {
            valid = false;
            errorMessage.append("Choose a city! ");
        }
        try{
            final boolean b = Double.parseDouble(priceTF.getText()) < 0.0;
        }catch (Exception e){
            valid = false;
            errorMessage.append("Invalid Price! ");
        }
        if (startDate.getValue() == null) {
            valid = false;
            errorMessage.append("Pick starting date! ");
        }else if(startDate.getValue().isAfter(endDate.getValue())){
            valid = false;
            errorMessage.append("Start date can't be after end date! ");
        }
        if (endDate.getValue() == null) {
            valid = false;
            errorMessage.append("Pick end date! ");
        }else if(endDate.getValue().isBefore(startDate.getValue())){
            valid = false;
            errorMessage.append("End date can't be before start date! ");
        }

        if (valid) {
            return true;
        } else {
            errorText.setText(errorMessage.toString());
            return false;
        }
    }
}
