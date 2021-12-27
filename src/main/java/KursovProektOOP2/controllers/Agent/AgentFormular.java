package KursovProektOOP2.controllers.Agent;

import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

    public final CityRepository cityRepository = CityRepository.getInstance();
    public final StorageRoomRepository storageRoomRepository = StorageRoomRepository.getInstance();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();
    public final AgentRepository agentRepository = AgentRepository.getInstance();
    public final RenterInformationRepository renterRepository = RenterInformationRepository.getInstance();
    public final FormularRepository formularRepository = FormularRepository.getInstance();
    public final UserNotificationRepository userNotificationRepository = UserNotificationRepository.getInstance();

    @FXML
    private void initialize(){
        cities = cityRepository.getAll();
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

            StorageRoom room = (StorageRoom) storageRoomRepository.getById(roomID).get();
            Owner owner = (Owner) ownerRepository.getById(ownerID).get();
            Agent agent = (Agent) agentRepository.getById(agentID).get();

            if(room.isRented()){
                stage.close();
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Room is already Rented");
                a.show();
            }else{
                RenterInformation renter = new RenterInformation();
                renter.setRenterId(0);
                renter.setName(namesTF.getText());
                renter.setPhone(phoneTF.getText());
                renter.setCityId(cityBox.getValue());
                renter.setStreet(addressTF.getText());
                int id = renterRepository.saveAndReturnID(renter);

                Formular formular = new Formular();
                formular.setRenterId((RenterInformation) renterRepository.getById(id).get());
                formular.setStorageRoom(room);
                formular.setPeriodBegin(Timestamp.valueOf(startDate.getValue().atStartOfDay()));
                formular.setPeriodEnd(Timestamp.valueOf(endDate.getValue().atStartOfDay()));
                formular.setPrice(Double.parseDouble(priceTF.getText()));
                formularRepository.save(formular);

                agent.setDealAmount(agent.getDealAmount() + 1);
                agentRepository.update(agent);

                room.setRented(true);
                storageRoomRepository.update(room);

                Usernotifications ownerNotif = new Usernotifications();
                ownerNotif.setIdFromUser(owner.getUserId());
                ownerNotif.setNotificationName("Стая от склад "
                        + room.getwarehouse().getWarehouseName()
                        + "\n беше отдадена под наем от "
                        + formular.getPeriodBegin().toString()
                        + "\n до "
                        + formular.getPeriodEnd().toString());
                ownerNotif.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
                owner.getUserId().getUsernotifications().add(ownerNotif);
                userNotificationRepository.save(ownerNotif);
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
